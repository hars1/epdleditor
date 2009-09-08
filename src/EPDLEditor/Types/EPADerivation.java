package EPDLEditor.Types;

import EPDLEditor.MainEditor;
import EPDLEditor.UI.PopulateListsUtils;

/**
 * @author ronenv
 *
 */
public class EPADerivation {
	public EPADerivation(){
		
	}
	public DerivedEvent[] derivationSet;
	private String derivedEvent;
	private String[] derivedAttrs;
	public String getDerivedEvent() {
		return derivedEvent;
	}
	public void setDerivedEvent(String derivedEvent) {
		this.derivedEvent = derivedEvent;
		if (derivedEvent!=null){
			derivedAttrs = new String[getEventPayloadCnt(derivedEvent)];	
			for (int i=0; i<derivedAttrs.length;i++)
				derivedAttrs[i] = new String("");
		}
	}
	
	/** This method is being called to set the derived attributes.
	 * There is the subtle issue that the attributes in the array defined about might get out of sync with
	 * the actual attributes in the event type. 
	 * @return
	 */
	public int getPayloadLength() {
		if (derivedEvent==null)
			return 0;
		int eventPayloadCnt = getEventPayloadCnt(derivedEvent);
		// the event was updated, propogate changes to the derived attributes.
		if (derivedAttrs!=null && derivedAttrs.length!=eventPayloadCnt){
			String[] oldDerivedAttrs = new String[derivedAttrs.length];
			for (int i=0; i<derivedAttrs.length;i++)
				oldDerivedAttrs[i] = derivedAttrs[i];
				
			setDerivedEvent(derivedEvent);
			for (int i=0; i<derivedAttrs.length&&i<oldDerivedAttrs.length;i++)
				derivedAttrs[i]=oldDerivedAttrs[i];
		}
		return derivedAttrs.length;
	}
	
	private int getEventPayloadCnt(String eventName){
		String[] eventNameArray = new String[1];
		eventNameArray[0] = eventName;
		
		Event e = MainEditor.events.get(PopulateListsUtils.getIDs4Strings(eventNameArray, MainEditor.events)[0]);
		return e.payload.length;
	}
	
	public String getDerivedAttr(int index) {
		return this.derivedAttrs[index];
	}
	public void setDerivedAttr(int index, String expression) {
		this.derivedAttrs[index] = expression;
	}
	
	
	
}
