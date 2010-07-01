package EPDLEditor.UI.renderers;

import java.awt.BasicStroke;
import java.awt.Stroke;

import org.apache.commons.collections15.Transformer;

import EPDLEditor.MainEditor;
import EPDLEditor.Types.GraphElements.MyEdge;
import EPDLEditor.Types.GraphElements.MyVertex;

public class VertexStrokeVisualization implements Transformer<MyVertex, Stroke>{

	@Override
	public Stroke transform(MyVertex v) {
		
		// iff bra and all predecessors are request response
		boolean allRequestResponse = true;
		if (!v.getAgentType().equalsIgnoreCase(v.bra))
			allRequestResponse = false;
		else
			for (MyVertex lbn: MainEditor.g.getPredecessors(v)){
				if (!(MainEditor.g.isPredecessor(v, lbn) && MainEditor.g.isPredecessor(lbn, v))){
					allRequestResponse = false;
					break;
				}
			}
		if (allRequestResponse){
			float dash[] = { 10.0f };				
			return new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
			        BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);			
		}

		return new BasicStroke();
		

		
	}
}
