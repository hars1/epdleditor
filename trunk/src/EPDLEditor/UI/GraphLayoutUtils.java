package EPDLEditor.UI;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;

import EPDLEditor.Types.GraphElements;
import EPDLEditor.Types.ViewableGraphElement;
import EPDLEditor.Types.GraphElements.MyEdge;
import EPDLEditor.Types.GraphElements.MyVertex;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;


public class GraphLayoutUtils {

	
	public static void graphBeautifier(DirectedSparseMultigraph<GraphElements.MyVertex,MyEdge> g, 
			VisualizationViewer<GraphElements.MyVertex,MyEdge> vv, 
			StaticLayout<GraphElements.MyVertex, MyEdge> layout, int layoutPointZeroX, int layoutPointZeroY, int layoutGridStep, int visualizerWidth, int visualizerHight) {
    	

		// draw the graph on the visualizer after
		HashMap<String,GraphElements.MyVertex> currLevel = new HashMap<String,GraphElements.MyVertex>(); 
		HashMap<String,GraphElements.MyVertex> prevLevel = new HashMap<String,GraphElements.MyVertex>();
		HashMap<String,GraphElements.MyVertex> probed = new HashMap<String,GraphElements.MyVertex>();
		
		HashMap<GraphElements.MyVertex,Point2D> consumerLevelLocations = new HashMap<GraphElements.MyVertex,Point2D>();
		
		//Get the depth of the graph
		int graphNumLevels = getGraphDiameter(g);
		//Get the first level of vertices
		currLevel = GraphLayoutUtils.GetFirstLevel(g,probed);
		
		int x=layoutPointZeroX,y=layoutPointZeroY, prevX=0;
		while (currLevel.size()!=0){
			//Draw the previous Level
			int currLevelStep = (int)(((double)visualizerHight-layoutGridStep)/(double)currLevel.size());
			for (GraphElements.MyVertex v: currLevel.values()){
				Point2D p = new Point(x,y);
				y=y+currLevelStep;
    			layout.setLocation(v, p);
    			if (g.getSuccessors(v).size()==0)
    				consumerLevelLocations.put(v, p);
			}			
			//Advance to the next Level
			prevX = x;
			x=x+(int)(((double)visualizerWidth-layoutGridStep)/(double)graphNumLevels);
			y=layoutPointZeroY;
			prevLevel = (HashMap<String,GraphElements.MyVertex>)currLevel.clone();
			currLevel = getNextLevel(g,prevLevel,probed); 
		}
		
		// move all consumers to the same drawing level (same X position)
		for (GraphElements.MyVertex v: consumerLevelLocations.keySet())
			layout.setLocation(v, new Point(prevX,(int)consumerLevelLocations.get(v).getY()));
		
		// reduce the number of times the edges cross each other
		reduceEdgeCrossings(g,layout);
		
		
		
	}

	
	private static void reduceEdgeCrossings(
			DirectedSparseMultigraph<MyVertex, MyEdge> g,
			StaticLayout<MyVertex, MyEdge> layout) {
		
		// go over all pairs of vertices
		// if they are in the same level (have the same X value, see if a swap reduces the number of crossings, if so, perform it.
		int bestNumberOfCrossings = getNumberOfCrossings(g,layout);
		boolean improved = true;
		while (improved){
			improved = false;
			for (MyVertex v1:g.getVertices())
			for (MyVertex v2:g.getVertices()){
				if (layout.getX(v1)==layout.getX(v2)){
					Point2D v1Loc = new Point((int)layout.getX(v1),(int)layout.getY(v1));
					Point2D v2Loc = new Point((int)layout.getX(v2),(int)layout.getY(v2));

					layout.setLocation(v1, v2Loc);
					layout.setLocation(v2, v1Loc);

					int newNumberOfCrossings = getNumberOfCrossings(g,layout);
					if (newNumberOfCrossings<bestNumberOfCrossings){
						bestNumberOfCrossings=newNumberOfCrossings;
						improved = true;
					}
					else {
						layout.setLocation(v1, v1Loc);
						layout.setLocation(v2, v2Loc);
					}
						
				}
					
			}
		}
	}


	private static int getNumberOfCrossings(
			DirectedSparseMultigraph<MyVertex, MyEdge> g,
			StaticLayout<MyVertex, MyEdge> layout) {
		int cnt = 0;
		for (MyEdge e1:g.getEdges())
			for (MyEdge e2:g.getEdges()){
				int e1x1 = (int)layout.getX(g.getEndpoints(e1).getFirst());
				int e1y1 = (int)layout.getY(g.getEndpoints(e1).getFirst());
				int e1x2 = (int)layout.getX(g.getEndpoints(e1).getSecond());
				int e1y2 = (int)layout.getY(g.getEndpoints(e1).getSecond());
				
				int e2x1 = (int)layout.getX(g.getEndpoints(e2).getFirst());
				int e2y1 = (int)layout.getY(g.getEndpoints(e2).getFirst());
				int e2x2 = (int)layout.getX(g.getEndpoints(e2).getSecond());
				int e2y2 = (int)layout.getY(g.getEndpoints(e2).getSecond());
				
				if (new Line2D.Float(e1x1,e1y1,e1x2,e1y2).intersectsLine(e2x1,e2y1,e2x2,e2y2))
					cnt++;
			}
			
		
		return cnt;
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
