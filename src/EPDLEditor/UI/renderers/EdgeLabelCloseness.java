package EPDLEditor.UI.renderers;

import java.awt.geom.Point2D;

import org.apache.commons.collections15.Transformer;

import EPDLEditor.MainEditor;
import EPDLEditor.Types.GraphElements.MyEdge;
import EPDLEditor.Types.GraphElements.MyVertex;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.Pair;

public class EdgeLabelCloseness implements
		Transformer<Context<Graph<MyVertex, MyEdge>, MyEdge>, Number> {

	@Override
	public Number transform(Context<Graph<MyVertex, MyEdge>, MyEdge> arg0) {
		return 0.5;		
	}

}
