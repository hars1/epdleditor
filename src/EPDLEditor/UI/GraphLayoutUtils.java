package EPDLEditor.UI;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashMap;

import EPDLEditor.Types.GraphElements;
import EPDLEditor.Types.ViewableGraphElement;
import EPDLEditor.Types.GraphElements.MyVertex;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;


public class GraphLayoutUtils {

	
	public static void graphBeautifier(DirectedSparseMultigraph<GraphElements.MyVertex,? extends ViewableGraphElement> g, 
			VisualizationViewer<GraphElements.MyVertex, ? extends ViewableGraphElement> vv, 
			Layout<GraphElements.MyVertex, ? extends ViewableGraphElement> layout, int layoutPointZeroX, int layoutPointZeroY, int layoutGridStep, int visualizerWidth, int visualizerHight) {
    	

		// draw the graph on the visualizer after
		HashMap<String,GraphElements.MyVertex> currLevel = new HashMap<String,GraphElements.MyVertex>(); 
		HashMap<String,GraphElements.MyVertex> prevLevel = new HashMap<String,GraphElements.MyVertex>();
		HashMap<String,GraphElements.MyVertex> probed = new HashMap<String,GraphElements.MyVertex>();
		
		//Get the depth of the graph
		int graphNumLevels = getGraphDiameter(g);
		//Get the first level of vertices
		currLevel = GraphLayoutUtils.GetFirstLevel(g,probed);
		
		int x=layoutPointZeroX,y=layoutPointZeroY;
		while (currLevel.size()!=0){
			//Draw the previous Level
			int currLevelStep = (int)(((double)visualizerHight-layoutGridStep)/(double)currLevel.size());
			for (GraphElements.MyVertex v: currLevel.values()){
				Point2D p = new Point(x,y);
				y=y+currLevelStep;
    			layout.setLocation(v, p);	
			}			
			//Advance to the next Level
			x=x+(int)(((double)visualizerWidth-layoutGridStep)/(double)graphNumLevels);
			y=layoutPointZeroY;
			prevLevel = (HashMap<String,GraphElements.MyVertex>)currLevel.clone();
			currLevel = getNextLevel(g,prevLevel,probed); 
		}
		
	}

	
	private static HashMap<String, MyVertex> getNextLevel(
			DirectedSparseMultigraph<MyVertex, ? extends ViewableGraphElement> g,
			HashMap<String, MyVertex> prevLevel,
			HashMap<String, MyVertex> probed) {
		HashMap<String,GraphElements.MyVertex> currLevel = new HashMap<String,GraphElements.MyVertex>();
		for (GraphElements.MyVertex v: prevLevel.values()){
			for (GraphElements.MyVertex s: g.getSuccessors(v)){
				if (!probed.containsKey(s.getName())){
					currLevel.put(s.getName(), s);
					probed.put(s.getName(), s);
				}		
			}	
		}
		return currLevel;
	}


	private static int getGraphDiameter(
			DirectedSparseMultigraph<MyVertex, ? extends ViewableGraphElement> g) {
		HashMap<String,GraphElements.MyVertex> currLevel = new HashMap<String,GraphElements.MyVertex>(); 
		HashMap<String,GraphElements.MyVertex> probed = new HashMap<String,GraphElements.MyVertex>();
		
		int i=0;
		//Get the first level of vertices
		currLevel = GraphLayoutUtils.GetFirstLevel(g,probed);
		while (currLevel.size()!=0){
			i++;
			currLevel = getNextLevel(g,currLevel,probed); 
		}
		return i;
	}


	public static HashMap<String, GraphElements.MyVertex> GetFirstLevel(
			DirectedSparseMultigraph<GraphElements.MyVertex, ? extends ViewableGraphElement> g,
			HashMap<String, GraphElements.MyVertex> probed) {
		HashMap<String, GraphElements.MyVertex> currLevel = new HashMap<String, GraphElements.MyVertex>();
		for (GraphElements.MyVertex v:g.getVertices()){
			if (g.getPredecessorCount(v)==0){
				currLevel.put(v.getName(), v);
				probed.put(v.getName(), v);
			}		
		}
		
		return currLevel;
	}
	


}
