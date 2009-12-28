package EPDLEditor.Types;

import javax.swing.table.TableModel;

import EPDLEditor.MainEditor;
import EPDLEditor.Types.GraphElements.MyEdge;


public class Event implements Identifiable{

	public static final String[] EventCompositionOptions = new String[] { "False", "True" };
	public String eventTypeIdentifier;
	public boolean eventCompositionIndicator;
	public String eventTemporalGranularity;
	public Attribute[] payload;
	public String referencedEvent;
	public String referenceRelationship;
	public Event(){
		this.eventTypeIdentifier="";
		this.eventCompositionIndicator = false;
		this.eventTemporalGranularity = "Second";
		//this.payload = event. new Payload();
		this.referencedEvent = "";
		this.referenceRelationship = "None";
	}
    public String toString() {
        return eventTypeIdentifier;
    }
	public void populateTableModelwithPayload(TableModel tm) {
    	int payloadCount = this.payload==null?0:this.payload.length;
		int i;
    	for (i=0; i<payloadCount;i++){
    		tm.setValueAt(this.payload[i].attributeName, i, 0);
    		tm.setValueAt(this.payload[i].dataType, i, 1);
    		tm.setValueAt(this.payload[i].semanticRole, i, 2);    		
    	}
    	// initialize the rest of the table fields
    	while(i<tm.getRowCount()-1){
      		tm.setValueAt("", i, 0);
    		tm.setValueAt("", i, 1);
    		tm.setValueAt("", i, 2); 
    		i++; 	
    	}
		
	}
	public String getID() {
		return this.eventTypeIdentifier;
	}
	/** Checks if the event is being used, if so returns the edge that uses it,
	 * otherwise (not used) it is deleted and null is returned.
	 * @param ev
	 * @return
	 */
	public static String deleteEvent(Event ev) {
		// check if there is a reference, if so abort
		for (MyEdge v:MainEditor.g.getEdges())
			for (int i:v.getEventIds())
				if (MainEditor.events.get(i).getID().compareTo(ev.getID())==0)
					return v.getName();
		
		// check if there is a reference in another event type
		for (Event e:MainEditor.events)
			if (e.eventCompositionIndicator && e.referencedEvent.compareTo(ev.getID())==0)
					return "Event "+e.getID();
		
		// delete the event type
		for (int i=0; i<MainEditor.events.size();i++){
			if (MainEditor.events.get(i).getID().compareTo(ev.getID())==0){
				MainEditor.events.remove(i);
				break;
			}
		}
		
		// reload the event ids for all edges.
		for (MyEdge v:MainEditor.g.getEdges())
			v.reloadEventIds();
		
		return null;		
	}
}
