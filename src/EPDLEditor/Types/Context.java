package EPDLEditor.Types;

import javax.swing.table.TableModel;

import EPDLEditor.MainEditor;
import EPDLEditor.Types.GraphElements.MyVertex;



public class Context implements Identifiable{

	public String contextIdentifier;
	public String contextType;
	public String contextSubtype;
	public PartitionParameter[] partitionParameters;
	public String[] compositionContexts;
	public String compositionOperator;
	public static int compositeContextId = 4;
	public static final String[] ContextTypes = new String[] { "Temporal", "Spatial", "State Oriented", "Segmentation Oriented", "Composite" };

	public Context(){
		this.contextIdentifier="";
		this.contextType = "Temporal";
		this.contextSubtype = "Fixed Interval";
		//this.payload = event. new Payload();
		this.compositionOperator = "";
		this.compositionContexts = new String[0];
		
	}
    public String toString() {
        return "Context: "+contextIdentifier;
    }
	public static String[] getSubTypeOptions(int selectedIndex) {
		String[] rslt = null;
		switch (selectedIndex) {
		case 0: 
			rslt = new String[4];
			rslt[0] = new String("Fixed Interval");
			rslt[1] = new String("Event Interval");
			rslt[2] = new String("Sliding Fixed Interval");
			rslt[3] = new String("Sliding Event Interval");
			break;
		case 1: 
			rslt = new String[3];
			rslt[0] = new String("Fixed Location");
			rslt[1] = new String("Entity Distance Location");
			rslt[2] = new String("Event Distance Location");
			break;
		case 2: 
			rslt = new String[1];
			rslt[0] = new String("");
			break;
		case 3: 
			rslt = new String[1];
			rslt[0] = new String("");
			break;
		case 4: 
			rslt = new String[1];
			rslt[0] = new String("");
			break;			
		}
		return rslt;
	}
	public static TableModel getTableModelForTypeAndSubType(int contextTypeIndex,
			int contextSubtypeIndex) {
		Object [][] tableContexts = null;
		switch (contextTypeIndex){
		case 0:
			switch (contextSubtypeIndex){
			case 0:
				tableContexts = new Object [][] {
		                {"Interval Start", null},
		                {"Interval End", null},
		                {"Period", null},
		                {"Temporal Ordering", null}
		            };
				break;
			case 1:
				tableContexts = new Object [][] {
		                {"Initiators", null},
		                {"Terminators", null},
		                {"Expiration Time Offset", null},
		                {"Expiration Event Count", null},
		                {"Context Initiator Policy", null},
		                {"Temporal Ordering", null}
		            };
				break;
			case 2:
				tableContexts = new Object [][] {
		                {"Interval Period", null},
		                {"Interval Duration", null},
		                {"Interval Size", null},
		                {"Temporal Ordering", null}
		            };
				break;
			case 3:
				tableContexts = new Object [][] {
		                {"Event Types", null},
		                {"Interval Size", null},
		                {"Event Period", null},
		                {"Temporal Ordering", null}
		            };
				break;
			}
			break;
		case 1:
			switch (contextSubtypeIndex){
			case 0:
				tableContexts = new Object [][] {
		                {"Location Attribute", null},
		                {"Spatial Entity", null}
		            };
				break;
			case 1:
				tableContexts = new Object [][] {
		                {"Reference Entity", null},
		                {"Fixed Entity", null},
		                {"Distance Expressions", null}
		            };
				break;
			case 2:
				tableContexts = new Object [][] {
		                {"Event Type", null},
		                {"Matching Expression", null},
		                {"Distance Expression", null},
		                {"Context Initiator Policy", null}
		            };
				break;
			}
			break;
		case 2:
			tableContexts = new Object [][] {
	                {"Entity", null},
	                {"Relevant States", null},
	                {"Temporal Ordering", null}
	            };
			break;
		case 3:
			tableContexts = new Object [][] {
	                {"Attributes", null},
	                {"Grouping Expressions", null}
	            };
			break;
		case 4:
			tableContexts = new Object [0][0];
			break;			
		}

		TableModel tm = new javax.swing.table.DefaultTableModel(
				tableContexts ,
	            new String [] {
	                "Parameter Name", "Parameter Value"
	            }
	        ) {
	            Class[] types = new Class [] {
	                java.lang.String.class, java.lang.String.class
	            };
	            boolean[] canEdit = new boolean [] {
	                    false, true
	                };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        };
		return tm;
	}
	public String getID() {
		return this.contextIdentifier;
	}
	public static String[] getDefinedContexts() {
        String[] contextNames = new String[MainEditor.contexts.size()+1];
        int i=0;
        contextNames[i++] = "";
        for (Context c:MainEditor.contexts)
        	contextNames[i++]=c.getID();
       
        
        return contextNames;
	}
	
	/** Checks if the context is being used, if so returns the agent that uses it,
	 * otherwise (not used) it is deleted and null is returned.
	 * @param ev
	 * @return
	 */
	public static String deleteContext(Context ev) {
		for (MyVertex v:MainEditor.g.getVertices()){
			if (v.getAgentPattern()!=null && v.getAgentPattern().context.compareTo(ev.getID())==0)
				return v.getAgentId();
		}
		for (Context c:MainEditor.contexts){
			if (c.contextType==Context.ContextTypes[compositeContextId])
				for (String s:c.compositionContexts)
					if (s.compareTo(c.getID())==0)
						return "Context "+s;
		}
		
		for (int i=0; i<MainEditor.contexts.size();i++){
			if (MainEditor.contexts.get(i).contextIdentifier.compareTo(ev.contextIdentifier)==0){
				MainEditor.contexts.remove(i);
				break;
			}		
		}
		return null;
	}

}
