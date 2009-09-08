/*
 * EditorMouseMenu.java
 *
 * Created on March 21, 2007, 10:34 AM; Updated May 29, 2007
 *
 * Copyright 2007 Grotto Networking
 */

package EPDLEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.apache.commons.io.FileUtils;

import EPDLEditor.Export.Component2ImageUtil;
import EPDLEditor.Types.Context;
import EPDLEditor.Types.Event;
import EPDLEditor.Types.GraphElements;
import EPDLEditor.UI.GraphLayoutUtils;
import EPDLEditor.UI.Dialogs.ContextManagementPropertyDialog;
import EPDLEditor.UI.Dialogs.EventManagementPropertyDialog;
import EPDLEditor.UI.Menus.MyMouseMenus;
import EPDLEditor.UI.Menus.PopupVertexEdgeMenuMousePlugin;
import EPDLEditor.XML.DataStructure2XMLify;

import com.thoughtworks.xstream.XStream;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

/**
 * Illustrates the use of custom edge and vertex classes in a graph editing application.
 * Demonstrates a new graph mouse plugin for bringing up popup menus for vertices and
 * edges.
 * @author Ronen Vaisenberg and Dr. Greg M. Bernstein
 */
public class MainEditor {
	private static String version = "3.0";
	public static VisualizationViewer<GraphElements.MyVertex,GraphElements.MyEdge> vv;
	public static DirectedSparseMultigraph<GraphElements.MyVertex, GraphElements.MyEdge> g;
	public static ArrayList<Event> events;
	public static ArrayList<Context> contexts;
	public static Layout<GraphElements.MyVertex, GraphElements.MyEdge> layout;
	public static JFrame frame;
	private static int visualizerWidth = 800;
	private static int visualizerHight = 500;
	private static int layoutGridStep = 100;
	private static int layoutPointZeroX = 80;
	private static int layoutPointZeroY = 80;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        frame = new JFrame("EPDL - Event Processing Description Meta Language");
        try {
        	     
	        g= new DirectedSparseMultigraph<GraphElements.MyVertex, GraphElements.MyEdge>();
	        events = new ArrayList<Event>();
	        contexts = new ArrayList<Context>();
	        layout = new StaticLayout(g);
	        // Layout<V, E>, VisualizationViewer<V,E>
	//        Map<GraphElements.MyVertex,Point2D> vertexLocations = new HashMap<GraphElements.MyVertex, Point2D>();
	        
	        layout.setSize(new Dimension(300,300));
	        vv = new VisualizationViewer<GraphElements.MyVertex,GraphElements.MyEdge>(layout);
	        vv.setPreferredSize(new Dimension(visualizerWidth,visualizerHight));
	        
	        // Show vertex and edge labels
	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	        // Create a graph mouse and add it to the visualization viewer
	        EditingModalGraphMouse gm = new EditingModalGraphMouse(vv.getRenderContext(), 
	                 GraphElements.MyVertexFactory.getInstance(),
	                GraphElements.MyEdgeFactory.getInstance()); 
	        // Set some defaults for the Edges...
	        GraphElements.MyEdgeFactory.setDefaultCapacity(192.0);
	        GraphElements.MyEdgeFactory.setDefaultWeight(5.0);
	        // Trying out our new popup menu mouse plugin...
	        PopupVertexEdgeMenuMousePlugin myPlugin = new PopupVertexEdgeMenuMousePlugin();
	        // Add some popup menus for the edges and vertices to our mouse plugin.
	        JPopupMenu edgeMenu = new MyMouseMenus.EdgeMenu(frame);
	        JPopupMenu vertexMenu = new MyMouseMenus.VertexMenu(frame);
	        myPlugin.setEdgePopup(edgeMenu);
	        myPlugin.setVertexPopup(vertexMenu);
	        gm.remove(gm.getPopupEditingPlugin());  // Removes the existing popup editing plugin
	        
	        gm.add(myPlugin);   // Add our new plugin to the mouse
	        
	        vv.setGraphMouse(gm);
	
	        
	        //JFrame frame = new JFrame("Editing and Mouse Menu Demo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(vv);
	        
	        // Let's add a menu for changing mouse modes
	        JMenuBar menuBar = new JMenuBar();
	        JMenu modeMenu = gm.getModeMenu();
	        modeMenu.setText("Mouse Mode");
	        modeMenu.setIcon(null); // I'm using this in a main menu
	        modeMenu.setPreferredSize(new Dimension(120,20)); // Change the size so I can see the text
	        
	        
	        gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
	        
	        
	        // Let's add a menu to save and load XML
	        //JMenuBar XMLmenuBar = new JMenuBar();
	        JMenu file = new JMenu("File");
	        JMenuItem XMLSave = new JMenuItem("Save to XML");
	        ActionListener XMLSaveAction = new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		XStream xstream = DataStructure2XMLify.getXStreamInstance();
	        		
	        		String xml = null;
        			try{
        				xml = xstream.toXML(new DataStructure2XMLify(g,events,contexts));	
        			} catch (Exception ex){
        				dispayError("Failed to generate XML file.",ex);
        			}
	        		
	        		System.out.print(xml);
	        		FileDialog fd = new FileDialog(frame,"Please select where to save the EPDL xml file",FileDialog.SAVE);
	        		fd.setVisible(true);
	        		if (fd.getFile()!=null){
	            		File fir = new File(fd.getDirectory()+fd.getFile());
	        			try {
	    					FileUtils.writeStringToFile(fir, xml);
	    				} catch (Exception e1) {
	    					dispayError("Failed to store File in location: +"+fd.getFile()+".",e1);
	    				}
	        			
	        		}
	        	}
	        	};
	        XMLSave.addActionListener(XMLSaveAction);
	        file.add(XMLSave);
	        JMenuItem XMLLoad = new JMenuItem("Load from XML");
	        
	        ActionListener XMLLoadAction = new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		XStream xstream = DataStructure2XMLify.getXStreamInstance();
	
	        		FileDialog fd = new FileDialog(frame,"Please select the EPDL xml file to load",FileDialog.LOAD);
	        		fd.setVisible(true);
	        		if (fd.getFile()!=null){
	        			File fir = new File(fd.getDirectory()+fd.getFile());
	        			String content = "";
	        			try {
	        				content = FileUtils.readFileToString(fir);
	        			} catch (Exception e1) {
	        				dispayError("Failed to read File in location: +"+fd.getFile(),e1);
	        			}
	
	
	        			System.out.print(content);
	        			// remove the previous event types from the frame
	        			vv.removeAll();
	        			DataStructure2XMLify ds = null;
	        			try{
	        				ds = (DataStructure2XMLify)xstream.fromXML(content);	
	        			} catch (Exception ex){
	        				dispayError("Failed to parse XML file in location: "+fd.getFile(),ex);
	        			}
	        			
	        			MainEditor.g = new DirectedSparseMultigraph<GraphElements.MyVertex, GraphElements.MyEdge>();
	        			MainEditor.events = new ArrayList<Event>();
	        			MainEditor.contexts = new ArrayList<Context>();
	        			ds.populateGraphAndEvents(g,events,contexts);
	        			MainEditor.layout.setGraph(g);
	        			layout.initialize();
	            		
	                	
	            		vv.validate();
	            		vv.repaint();
	            	
	
	
	            		GraphLayoutUtils.graphBeautifier(g,vv,layout,layoutPointZeroX,layoutPointZeroY,layoutGridStep,vv.getWidth(),vv.getHeight());
	
	        			
	        			MainEditor.vv.repaint();       			
	        		}
	
	
	        	}
	
	        };
			XMLLoad.addActionListener(XMLLoadAction);
	        file.add(XMLLoad);
	        
	        // add the image export button
	        JMenuItem imgExp = new JMenuItem("Export to Power Point");
	        
	        ActionListener ImgExpAction = new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		FileDialog fd = new FileDialog(frame,"Please select where to save the image",FileDialog.SAVE);
	        		fd.setVisible(true);
	        		if (fd.getFile()!=null){
	            		File fir = new File(fd.getDirectory()+fd.getFile());
	        			try {
	        				Component2ImageUtil.translateDataStructure2PPT(fir, frame, vv, g, events, contexts);
	    				} catch (Exception e1) {
	    					dispayError("Failed to generate PPT.",e1);
	    				}
	        			
	        		}
	        	}
	        	};
	        imgExp.addActionListener(ImgExpAction);
	        file.add(imgExp);
	        
	        
	        // Let's add a menu option to manage the event types and contexts
	        JMenu manage = new JMenu("Manage");
	        JMenuItem manageEventTypes = new JMenuItem("Manage Event Types...");
	        manageEventTypes.setBackground(Color.orange);
	        manageEventTypes.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		EventManagementPropertyDialog cmpd = new EventManagementPropertyDialog(frame);
	        		cmpd.setLocation((int) frame.getX(), (int) frame.getY());
	        		cmpd.setVisible(true);
	        	}
	        	});
	
	        manage.add(manageEventTypes);
	        // Let's add a menu option to create the contexts
	        
	        JMenuItem manageContexts = new JMenuItem("Manage Contexts...");
	        manageContexts.setBackground(Color.GREEN);
	        manageContexts.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		ContextManagementPropertyDialog cmpd = new ContextManagementPropertyDialog(frame);
	        		cmpd.setLocation((int) frame.getX(), (int) frame.getY());
	        		cmpd.setVisible(true);
	        	}
	        	});
	
	        manage.add(manageContexts);
	        
	        JMenuItem about = new JMenuItem("About EPDL Editor...");
	        about.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(frame,"EPDL Editor Version: "+version+" Maintained by Ronen Vaisenberg and Opher Etzion "+
	        				'\n'+"Please go to http://code.google.com/p/epdleditor/","About EPDL Editor",JOptionPane.INFORMATION_MESSAGE);
	        	}
	        	});
	        file.add(about);

	        
	        menuBar.add(file);
	        menuBar.add(modeMenu);
	        menuBar.add(manage);
	        frame.setJMenuBar(menuBar);
	        
	        
	        
	        frame.pack();
	        frame.setVisible(true);    
	    }
	    catch (Exception e){
	    	dispayError("Internal Error: ",e);
	    }
}
	protected static void dispayError(String errorDesc,Exception ex) {
		String error = "Version"+version+'\n';
		for (StackTraceElement er:ex.getStackTrace()){
			error+=er.toString()+'\n';
		}
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		//Custom button text
		Object[] options = {"Dismiss",
		                    "Copy Detailed Error to Clipboard"};
		int n = JOptionPane.showOptionDialog(frame,
				errorDesc,
		    "Error",
		    JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    options,
		    options[0]);
		
		if (n==1) clipboard.setContents(new StringSelection(error), null);

		
	}
}
