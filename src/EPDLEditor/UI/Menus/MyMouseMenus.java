/*
 * MyMouseMenus.java
 *
 * Created on March 21, 2007, 3:34 PM; Updated May 29, 2007
 *
 * Copyright March 21, 2007 Grotto Networking
 *
 */

package EPDLEditor.UI.Menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import EPDLEditor.Types.Context;
import EPDLEditor.Types.Event;
import EPDLEditor.Types.GraphElements;
import EPDLEditor.UI.Dialogs.ContextPropertyDialog;
import EPDLEditor.UI.Dialogs.EdgePropertyDialog;
import EPDLEditor.UI.Dialogs.EventTypePropertyDialog;
import EPDLEditor.UI.Dialogs.VertexPropertyDialog;
import EPDLEditor.UI.Listeners.ContextButtonListener;
import EPDLEditor.UI.Listeners.EdgeMenuListener;
import EPDLEditor.UI.Listeners.EventTypeButtonListener;
import EPDLEditor.UI.Listeners.MenuPointListener;
import EPDLEditor.UI.Listeners.VertexMenuListener;

import edu.uci.ics.jung.visualization.VisualizationViewer;

/**
 * A collection of classes used to assemble popup mouse menus for the custom
 * edges and vertices developed in this example.
 * @author Dr. Greg M. Bernstein and Ronen Vaisenberg
 */
public class MyMouseMenus {
    
    public static class EdgeMenu extends JPopupMenu {        
        // private JFrame frame; 
        public EdgeMenu(final JFrame frame) {
            super("Edge Menu");
            this.add(new EdgePropItem(frame));           
            this.addSeparator();
            this.add(new DeleteEdgeMenuItem<GraphElements.MyEdge>());
            
        }
        
    }
    
    public static class EventTypePropItem extends JButton implements EventTypeButtonListener<Event>,
            MenuPointListener {
        Event event;
        VisualizationViewer visComp;
        Point2D point;
        
        public void setEventTypeAndView(Event event, VisualizationViewer visComp) {
            this.event = event;
            this.visComp = visComp;
        }
        
        public String toString() {
            return event.eventTypeIdentifier==null?"Edit Event Type Properties...":"Edit "+event.eventTypeIdentifier+" Type Properties...";
        }

        public void setPoint(Point2D point) {
            this.point = point;
        }
        
        public  EventTypePropItem(final JFrame frame) {            
            super("Edit Event Type Properties...");
            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    EventTypePropertyDialog dialog = new EventTypePropertyDialog(frame, event);
                    dialog.setLocation((int) frame.getX(), (int) frame.getY());
                    dialog.setVisible(true);
                }
                
            });
            this.setVisible(true);
            this.setBackground(Color.orange);
        }
        
        public void paint(Graphics g){
        	this.setText(toString());
        	//this.setName(toString());
        	super.paint(g);
        	
        }
        
    }
    


    public static class ContextPropItem extends JButton implements ContextButtonListener<Context>,
    MenuPointListener {
	Context context;
	VisualizationViewer visComp;
	Point2D point;
	
	public void setContextAndView(Context context, VisualizationViewer visComp) {
	    this.context = context;
	    this.visComp = visComp;
	}
	
	public String toString() {
	    return "Edit Context: "+context.contextIdentifier+" Properties...";
	}
	
	public void setPoint(Point2D point) {
	    this.point = point;
	}
	
	public  ContextPropItem(final JFrame frame) {            
	    super("Edit Context Properties...");
	    this.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            ContextPropertyDialog dialog = new ContextPropertyDialog(frame, context);
	            dialog.setLocation((int) frame.getX(), (int) frame.getY());
	            dialog.setVisible(true);
	        }
	        
	    });
	    this.setVisible(true);
	    this.setBackground(Color.GREEN);
	}
	
	public void paint(Graphics g){
		this.setText(toString());
		//this.setName(toString());
		super.paint(g);
		
	}

	
	}
    
    public static class EdgePropItem extends JMenuItem implements EdgeMenuListener<GraphElements.MyEdge>,
            MenuPointListener {
        GraphElements.MyEdge edge;
        VisualizationViewer visComp;
        Point2D point;
        
        public void setEdgeAndView(GraphElements.MyEdge edge, VisualizationViewer visComp) {
            this.edge = edge;
            this.visComp = visComp;
        }

        public void setPoint(Point2D point) {
            this.point = point;
        }
        
        public  EdgePropItem(final JFrame frame) {            
            super("Edit Edge Properties...");
            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    EdgePropertyDialog dialog = new EdgePropertyDialog(frame, edge);
                    dialog.setLocation((int)point.getX()+ frame.getX(), (int)point.getY()+ frame.getY());
                    dialog.setVisible(true);
                }
                
            });
        }
        
        
        
    }


    public static class VertexPropItem extends JMenuItem implements VertexMenuListener<GraphElements.MyVertex>,
    MenuPointListener {
	GraphElements.MyVertex vertex;
	VisualizationViewer visComp;
	Point2D point;
	
	public void setVertexAndView(GraphElements.MyVertex vertex, VisualizationViewer visComp) {
	    this.vertex = vertex;
	    this.visComp = visComp;
	}
	
	public void setPoint(Point2D point) {
	    this.point = point;
	}
	
	public  VertexPropItem(final JFrame frame) {            
	    super("Edit Vertex Properties...");
	    this.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            VertexPropertyDialog dialog = new VertexPropertyDialog(frame, vertex);
        		// open the component at the center of the parent component.
        		Dimension componentSize = dialog.getSize();
        		Dimension panelSize = frame.getSize();
        		int componentX = (panelSize.width<componentSize.width?0:frame.getX()+(panelSize.width-componentSize.width)/2);
        		int componentY = (panelSize.height<componentSize.height?0:frame.getY()+(panelSize.height-componentSize.height)/2);
        		dialog.setLocation(componentX, componentY);
        		dialog.setVisible(true);
	        }
	        
	    });
	}
	
	}
    
       
    
    public static class VertexMenu extends JPopupMenu {
        public VertexMenu(final JFrame frame) {
            super("Vertex Menu");
            this.add(new VertexPropItem(frame));
            this.addSeparator();
            this.add(new DeleteVertexMenuItem<GraphElements.MyVertex>());
            
        }
    }
    
 
        
    }
    
