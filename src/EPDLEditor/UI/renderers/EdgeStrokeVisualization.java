package EPDLEditor.UI.renderers;

import java.awt.BasicStroke;
import java.awt.Stroke;

import org.apache.commons.collections15.Transformer;

import EPDLEditor.MainEditor;
import EPDLEditor.Types.GraphElements.MyEdge;
import EPDLEditor.Types.GraphElements.MyVertex;

public class EdgeStrokeVisualization implements Transformer<MyEdge, Stroke>{

	@Override
	public Stroke transform(MyEdge e) {
		
		// make the edge dashed iff there is also an edge the other way from something that is not bra to something that is bra
		MyVertex v1 = MainEditor.g.getEndpoints(e).getFirst();
		MyVertex v2 = MainEditor.g.getEndpoints(e).getSecond();

		if (MainEditor.g.isPredecessor(v2, v1) && MainEditor.g.isPredecessor(v1, v2) && 
				(v1.getAgentType().equalsIgnoreCase(v1.bra)&& !v2.getAgentType().equalsIgnoreCase(v2.bra)	||
						!v1.getAgentType().equalsIgnoreCase(v1.bra) && v2.getAgentType().equalsIgnoreCase(v2.bra)
						)){
			float dash[] = { 10.0f };				
			return new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
			        BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);			
		}

		
		return new BasicStroke();
		

		
	}
}
