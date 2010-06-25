package EPDLEditor.UI.renderers;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import org.apache.commons.collections15.Transformer;

import EPDLEditor.Types.GraphElements.MyVertex;

public class VertexShapeVisualization implements Transformer<MyVertex, Shape>{

	@Override
	public Shape transform(MyVertex v) {
		

		java.awt.geom.Ellipse2D.Double r1 = new java.awt.geom.Ellipse2D.Double(-25, -25, 50, 50);
		Rectangle r2 = new Rectangle();
		r2.setSize(50, 50);
		Rectangle r3 = new Rectangle();
		r3.setSize(50, 50);
		Rectangle r4 = new Rectangle();
		r4.setSize(50, 50);
		Rectangle r5 = new Rectangle();
		r5.setSize(50, 50);
		
		
		
		if (v.getAgentType().equalsIgnoreCase(v.consumer)){
			Ellipse2D.Double circle = new Ellipse2D.Double();
			Rectangle rec = new Rectangle();
			Area consumerArea = new Area(circle);
			Area recArea = new Area(rec);
			circle.setFrame(-25, -25, 50.0, 50.0);
			rec.setFrame(0, -25, 50.0, 50.0);
			consumerArea = new Area(circle);
			recArea = new Area(rec);
			consumerArea.add(recArea);
			
			return consumerArea;
		}
		if (v.getAgentType().equalsIgnoreCase(v.epa)) return r2;
		if (v.getAgentType().equalsIgnoreCase(v.gsa)) return r3;
		if (v.getAgentType().equalsIgnoreCase(v.patternDetectionEPA)) return r4;
		if (v.getAgentType().equalsIgnoreCase(v.producer)){
			Ellipse2D.Double circle = new Ellipse2D.Double();
			Rectangle rec = new Rectangle();
			Area producerArea = new Area(circle);
			Area recArea = new Area(rec);
			circle.setFrame(0, 0, 50.0, 50.0);
			rec.setFrame(-25, 0, 50.0, 50.0);
			producerArea = new Area(circle);
			recArea = new Area(rec);
			producerArea.add(recArea);
			
			return producerArea;
			
		}
		
		return new Rectangle();

		
	}
}
