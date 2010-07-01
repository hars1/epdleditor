package EPDLEditor.UI.renderers;

import java.awt.Color;
import java.awt.Paint;

import org.apache.commons.collections15.Transformer;

import EPDLEditor.MainEditor;
import EPDLEditor.Types.GraphElements.MyEdge;
import EPDLEditor.Types.GraphElements.MyVertex;

public class EdgeColorVisualization implements Transformer<MyEdge, Paint>{

	@Override
	public Paint transform(MyEdge e) {
		

		MyVertex v1 = MainEditor.g.getEndpoints(e).getFirst();
		MyVertex v2 = MainEditor.g.getEndpoints(e).getSecond();

		// regular -> regular
		if (!v1.getAgentType().equalsIgnoreCase(v1.bra) && !v2.getAgentType().equalsIgnoreCase(v2.bra)) return Color.BLACK;
		
		// regular -> bra
		if (!v1.getAgentType().equalsIgnoreCase(v1.bra)&& v2.getAgentType().equalsIgnoreCase(v2.bra)) return Color.RED;
		
		// bra -> regular
		if (v1.getAgentType().equalsIgnoreCase(v1.bra)&& !v2.getAgentType().equalsIgnoreCase(v2.bra)) return Color.GREEN;
		
		// bra -> bra
		if (v1.getAgentType().equalsIgnoreCase(v1.bra)&& v2.getAgentType().equalsIgnoreCase(v2.bra)) return Color.ORANGE;
		
		return null;
		
	}
}
