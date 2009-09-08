/*
 * MyMouseMenus.java
 *
 * Created on March 21, 2007, 3:34 PM; Updated May 29, 2007
 *
 * Copyright March 21, 2007 Grotto Networking
 *
 */

package EPDLEditor.UI.Menus;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import EPDLEditor.Types.ViewableGraphElement;

/**
 * A collection of classes used to assemble popup mouse menus for the custom
 * edges and vertices developed in this example.
 * @author Dr. Greg M. Bernstein and Ronen Vaisenberg
 */
public class GeneralMouseMenus {
    public static class EdgeMenu extends JPopupMenu {        
        // private JFrame frame; 
        public EdgeMenu(final JFrame frame) {
            super("Edge Menu");
            // this.frame = frame;
            this.add(new DeleteEdgeMenuItem<ViewableGraphElement>());           
        }
        
    }
    
    public static class VertexMenu extends JPopupMenu {
        public VertexMenu(final JFrame frame) {
            super("Vertex Menu");
            this.add(new DeleteVertexMenuItem<ViewableGraphElement>());

        }
    }
   
 }
    
