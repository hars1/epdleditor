package EPDLEditor.Export;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JWindow;

import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

import EPDLEditor.Types.Context;
import EPDLEditor.Types.Event;
import EPDLEditor.Types.GraphElements;
import EPDLEditor.Types.GraphElements.MyEdge;
import EPDLEditor.Types.GraphElements.MyVertex;
import EPDLEditor.UI.Dialogs.ContextPropertyDialog;
import EPDLEditor.UI.Dialogs.EdgePropertyDialog;
import EPDLEditor.UI.Dialogs.EventTypePropertyDialog;
import EPDLEditor.UI.Dialogs.VertexPropertyDialog;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class Component2ImageUtil {

	private static int maxWidth=600;
	private static int maxHight=400;
	public static BufferedImage getImage(JComponent component, String format) {
		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = image.createGraphics();
		component.printAll(g);

		if(g != null) {
			g.dispose();
               }
		return image;
	}
	public static BufferedImage getImage(JDialog dialog,String format){
		// Render dialog content to a buffered image
		return convertToImage(dialog, new Dimension(dialog.getWidth(),dialog.getHeight()));
	}
	
	
    /**Paints the current visual state of the component at the time of this
     * call to a BufferedImage (which can then be written to a file through
     * the  <code>javax.imageio.ImageIO.write()</code> methods).  
     * 
     * Note that if the component extends Window and is not not visible
     * the window decorations can not be painted.  Only the root pane
     * (if it is a Swing window) can be painted.  Passing a non-visible
     * AWT window as the parameter will only produce a blank image.
     * Furthermore if the component passed is a non-displayable window,
     * it will be made displayable by calling pack().
     *
     * @param c The component to be painted
     * @param size In the event that c is an invalid component, it will be
                   validated at the given size or if null then at its
     *             preferred size.
     */
    public static BufferedImage convertToImage(java.awt.Component c,
            java.awt.Dimension size)
    {
          if(!c.isValid()) {
            if (c instanceof java.awt.Window) {
                if (!c.isDisplayable()) {
                    ((java.awt.Window) c).pack();
                }
                if(size != null) {
                    c.setSize(size);
                    c.validate();
                }
            }else {
                c.setSize(size==null?c.getPreferredSize():size);
                c.doLayout();
            }
        }
        if (!c.isVisible() && c instanceof java.awt.Window) {
            if (c instanceof JFrame) {
                c = ((JFrame) c).getRootPane();
            } else if (c instanceof JDialog) {
                c = ((JDialog) c).getRootPane();
            } else if (c instanceof JApplet) {
                c = ((JApplet) c).getRootPane();
            } else if (c instanceof JWindow) {
                c = ((JWindow) c).getRootPane();
            }
        }
       
        int type;
        if(c instanceof java.awt.Window) {
            type = BufferedImage.TYPE_4BYTE_ABGR;
        }else {
            type = c.isOpaque()?BufferedImage.TYPE_3BYTE_BGR:
                                BufferedImage.TYPE_4BYTE_ABGR;
        }
 
        BufferedImage componentView =
                new BufferedImage(c.getWidth(),c.getHeight(),type);
        java.awt.Graphics g = componentView.getGraphics();
 
        if(c instanceof java.awt.Window)
            c.printAll(g);
        else
            c.paint(g);
        
        g.dispose();
        return componentView;
    }
    
	public static void writePPTSlide(SlideShow slideShow, String titleTxt, BufferedImage slideImg) throws IOException{    
	    Slide slide = slideShow.createSlide();
	    TextBox title = slide.addTitle();
	    title.setText(titleTxt);
	    
	    RichTextRun rt = title.getTextRun().getRichTextRuns()[0];
	    rt.setFontSize(36); 
	     
        
	    // add a new picture to this slideshow and insert it in a  new slide
	    int idx = slideShow.addPicture(translateBuff2Byte(slideImg), Picture.PNG);

	    Picture pict = new Picture(idx);

	    //set image position in the slide
	    java.awt.Rectangle titleLoc = title.getAnchor();

	    int imgWidth = slideImg.getWidth();
	    int imgHight = slideImg.getHeight();
	    maxWidth = (int)titleLoc.getWidth();
	    

    	double factor = (double)maxWidth/(double)imgWidth;
    	imgHight = (int)Math.round(factor*((double)slideImg.getHeight()));
    	imgWidth = (int)Math.round(factor*((double)slideImg.getWidth()));
    	
    	if (imgHight>maxHight){
        	factor = (double)maxHight/(double)imgHight;
        	imgHight = (int)Math.round(factor*((double)imgHight));
        	imgWidth = (int)Math.round(factor*((double)imgWidth));    	    		
    	}
	    
	    pict.setAnchor(new java.awt.Rectangle(titleLoc.x, titleLoc.y+titleLoc.height, imgWidth,imgHight ));

	    slide.addShape(pict);
    	
    }
	    


	
	
	public static void translateDataStructure2PPT(File file,Frame frame,VisualizationViewer vv, DirectedSparseMultigraph<MyVertex, MyEdge> myGraph, ArrayList<Event> events, ArrayList<Context> contexts) throws IOException{
		//ArrayList<BufferedImage> albf = new ArrayList<BufferedImage>();
		//ArrayList<String> titles = new ArrayList<String>();
		SlideShow slideShow = new SlideShow();
		String sTitle = "EPDL - Event Processing Description Meta Language";
		BufferedImage sImg = Component2ImageUtil.getImage(vv, "PNG");
		Component2ImageUtil.writePPTSlide(slideShow, sTitle, sImg);
		
		for (Event e:events){
			EventTypePropertyDialog epd = new EventTypePropertyDialog(frame,e);
			epd.formComponentShown(null);

			String title = "Event Type: "+e.toString();
			BufferedImage slideImg = Component2ImageUtil.getImage(epd,"PNG");
			Component2ImageUtil.writePPTSlide(slideShow, title, slideImg);
		}
		
		for (Context c:contexts){
			ContextPropertyDialog cpd = new ContextPropertyDialog(frame,c);
			cpd.formComponentShown(null);
			
			String title = "Context: "+c.toString();
			BufferedImage slideImg = Component2ImageUtil.getImage(cpd,"PNG");
			Component2ImageUtil.writePPTSlide(slideShow, title, slideImg);
		}
		
		for (GraphElements.MyVertex v:myGraph.getVertices()){
			VertexPropertyDialog vpd = new VertexPropertyDialog(frame,v);
			vpd.formComponentShown(null);

			String title = "Agent: "+v.toString();
			BufferedImage slideImg = Component2ImageUtil.getImage(vpd,"PNG");
			Component2ImageUtil.writePPTSlide(slideShow, title, slideImg);
		}
		
		for (GraphElements.MyEdge link:myGraph.getEdges()){
			EdgePropertyDialog epd = new EdgePropertyDialog(frame,link);
			epd.formComponentShown(null);

			String title = "Edge: "+link.toString();
			BufferedImage slideImg = Component2ImageUtil.getImage(epd,"PNG");
			Component2ImageUtil.writePPTSlide(slideShow, title, slideImg);

		}
			
	    FileOutputStream out = new FileOutputStream(file);
	    slideShow.write(out);
	    out.close();
	    
	}
	private static byte[] translateBuff2Byte(BufferedImage buffered){
		byte[] imgByte = null;

		ByteArrayOutputStream baot = new ByteArrayOutputStream();
		try {
			ImageIO.write(buffered, "PNG", baot);
			baot.flush();
			imgByte = baot.toByteArray();
			baot.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return imgByte;
		}
	}
}
