package EPDLEditor.UI.renderers;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;

import org.apache.commons.collections15.Transformer;

import EPDLEditor.Types.GraphElements.MyVertex;

public class GraphElementsVisualization implements Transformer<MyVertex, Paint>{

	@Override
	public Paint transform(MyVertex v) {
		

		if (v.getAgentType().equalsIgnoreCase(v.consumer)) return Color.BLUE;
		if (v.getAgentType().equalsIgnoreCase(v.epa)) return Color.CYAN;
		if (v.getAgentType().equalsIgnoreCase(v.gsa)) return Color.GRAY;
		if (v.getAgentType().equalsIgnoreCase(v.patternDetectionEPA)) return Color.MAGENTA;
		if (v.getAgentType().equalsIgnoreCase(v.producer)) return Color.ORANGE;
		
		return Color.RED;

		
	}
}
