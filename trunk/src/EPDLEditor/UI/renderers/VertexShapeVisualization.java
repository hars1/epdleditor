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
		

		Area result = new Area();
				
		if (v.getAgentType().equalsIgnoreCase(v.consumer)){
			Ellipse2D.Double circle = new Ellipse2D.Double();
			circle.setFrame(-25, -25, 50.0, 50.0);

			Rectangle rec = new Rectangle();
			rec.setFrame(0, -25, 50.0, 50.0);

			Area recArea = new Area(rec);
			Area circleArea = new Area(circle);

			result.add(circleArea);
			result.add(recArea);
			
		}
		if (v.getAgentType().equalsIgnoreCase(v.epa)){
			Rectangle rec = new Rectangle();
			rec.setFrame(-25, 0, 50.0, 50.0);
			result.add(new Area(rec));
		}
		if (v.getAgentType().equalsIgnoreCase(v.gsa)){
			Rectangle rec = new Rectangle();
			rec.setFrame(-25, 0, 50.0, 50.0);
			result.add(new Area(rec));
		}
		if (v.getAgentType().equalsIgnoreCase(v.patternDetectionEPA)){
			Rectangle rec = new Rectangle();
			rec.setFrame(-25, 0, 50.0, 50.0);
			result.add(new Area(rec));
		}
		if (v.getAgentType().equalsIgnoreCase(v.producer)){
			Ellipse2D.Double circle = new Ellipse2D.Double();
			Rectangle rec = new Rectangle();
			circle.setFrame(0, 0, 50.0, 50.0);
			rec.setFrame(-25, 0, 50.0, 50.0);
			result.add(new Area(circle));
			result.add(new Area(rec));
		}
		if (v.getAgentType().equalsIgnoreCase(v.bra)){
			Ellipse2D.Double circle = new Ellipse2D.Double();
			circle.setFrame(-25, -25, 60.0, 50.0);
			result.add(new Area(circle));
		}
		
		double shapeMaxX = result.getBounds().getMaxX();
		// add a notation for the usage of context in the processing
		if (v.getAgentPattern()!=null && v.getAgentPattern().context!=null && !v.getAgentPattern().context.isEmpty()){
			Ellipse2D.Double outsideC = new Ellipse2D.Double(shapeMaxX, result.getBounds().getMinY(), 18.0, 18.0);
			Ellipse2D.Double insideC  = new Ellipse2D.Double(shapeMaxX+4, result.getBounds().getMinY()+4, 10.0, 10.0);
			Area C = new Area(outsideC);
			C.exclusiveOr(new Area(insideC));
			Rectangle line = new Rectangle();
			line.setFrame(insideC.getMaxX()-4,insideC.getCenterY()-2,8,4);
			C.subtract(new Area(line));
			result.add(C);	
		}
		
		// add a database icon for enrichement
		if (v.getAgentSubType()!=null && v.getAgentSubType().compareTo(MyVertex.subType_enrich)==0){
			Rectangle base = new Rectangle();
			base.setFrame(shapeMaxX,result.getBounds().getMaxY()-2,18,10);
			Area DB = new Area(base);
			Ellipse2D.Double top = new Ellipse2D.Double(base.getBounds().getMinX(), base.getBounds().getMinY()-5, 18.0, 10.0);
			DB.subtract(new Area(top));
			Ellipse2D.Double bottom = new Ellipse2D.Double(base.getBounds().getMinX(), base.getBounds().getMaxY()-5, 18.0, 10.0);
			DB.add(new Area(bottom));
			top.setFrame(base.getBounds().getMinX(), base.getBounds().getMinY()-5.5, 18.0, 10.0);
			result.add(new Area(top));	
			result.add(DB);
		}

		
		return result;

		
	}
}
