package EPDLEditor.UI.renderers;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.commons.collections15.Transformer;

import EPDLEditor.Types.GraphElements.MyVertex;

public class GraphVertexIconManager implements Transformer<MyVertex, Icon>{

	@Override
	public Icon transform(MyVertex v) {
		
		try {
		if (v.getAgentType().equalsIgnoreCase(v.consumer)) return new ImageIcon(ImageIO.read(new File("icons/iconPPT/Slide1.png")));
		if (v.getAgentType().equalsIgnoreCase(v.epa)) return new ImageIcon(ImageIO.read(new File("icons/iconPPT/Slide2.png")));
		if (v.getAgentType().equalsIgnoreCase(v.gsa)) return new ImageIcon(ImageIO.read(new File("icons/iconPPT/Slide3.png")));
		if (v.getAgentType().equalsIgnoreCase(v.patternDetectionEPA)) return new ImageIcon(ImageIO.read(new File("icons/iconPPT/Slide4.png")));
		if (v.getAgentType().equalsIgnoreCase(v.producer)) return new ImageIcon(ImageIO.read(new File("icons/iconPPT/Slide5.png")));
		if (v.getAgentType().equalsIgnoreCase(v.producer)) return new ImageIcon(ImageIO.read(new File("icons/iconPPT/Slide5.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

		
	}
}
