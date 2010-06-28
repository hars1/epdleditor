package EPDLEditor.UI.renderers;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Hashtable;

import EPDLEditor.Types.GraphElements.MyVertex;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;

public class GraphStaticLayoutSaveLocation extends StaticLayout{

	Hashtable<MyVertex, Point2D> v2loc = new Hashtable<MyVertex, Point2D>();
	public GraphStaticLayoutSaveLocation(Graph graph) {
		super(graph);
	}
	
	void setLocation(MyVertex v, Point2D location){
		((StaticLayout)this).setLocation(v, location);
		v2loc.put(v, location);
	}
	
	public void setLocation(MyVertex v,double x,double y){
		((StaticLayout)this).setLocation(v, x,y);
		Point2D p = new Point((int)x,(int)y);
		v2loc.put(v, p);
	}
	
	Point2D getLocation(MyVertex v){
		return v2loc.get(v);
	}

}
