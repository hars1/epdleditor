/*
 * EdgeMenuListener.java
 *
 * Created on March 21, 2007, 2:41 PM; Updated May 29, 2007
 * Copyright March 21, 2007 Grotto Networking
 */

package EPDLEditor.UI.Listeners;

import edu.uci.ics.jung.visualization.VisualizationViewer;

/**
 * An interface for menu items that are interested in knowning the currently selected edge and
 * its visualization component context.  Used with PopupVertexEdgeMenuMousePlugin.
 * @author Ronen Vaisenberg and Dr. Greg M. Bernstein
 */
public interface EventTypeButtonListener<E> {
    /**
     * Used to set the edge and visulization component.
     * @param e 
     * @param visComp 
     */
     void setEventTypeAndView(E e, VisualizationViewer visView); 
    
}