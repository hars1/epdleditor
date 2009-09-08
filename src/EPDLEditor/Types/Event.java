package EPDLEditor.Types;

import javax.swing.table.TableModel;


public class Event implements Identifiable{

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
}
