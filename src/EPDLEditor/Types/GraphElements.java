/*
 * GraphElements.java
 *
 * Created on March 21, 2007, 9:57 AM
 *
 * Copyright March 21, 2007 Grotto Networking
 *
 */

package EPDLEditor.Types;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.commons.collections15.Factory;

import EPDLEditor.MainEditor;
import EPDLEditor.exceptions.UniqueNameException;

/**
 * 
 * @author Ronen Vaisenberg
 */
public class GraphElements {

	/** Creates a new instance of GraphElements */
	public GraphElements() {
	}

	public static class MyVertex extends ViewableGraphElement implements
			Identifiable {
		private String agentId;
		private String agentType;
		private String agentSubType;

		private EPAFiltering agentFiltering;
		private EPAPattern agentPattern;
		private EPADerivation agentDerivation;

		public static String producer = "Producer";
		public static String consumer = "Consumer";
		public static String epa = "Event Processing Agent";
		public static String gsa = "Global State Agent";
		public static String patternDetectionEPA = "Detect Pattern";
		public static String[] policies = { "Synonym", "Order",
				"Matching Cardinality", "Consumption" };
		public static int synonymPid = 0;
		public static int orderPid = 1;
		public static int matchingPid = 2;
		public static int consumptionPid = 3;
		
		public static String[][] policyTypes = {
				{ "", "Override", "Every", "First", "Last",
						"With Maximal Value", "With Minimal Value" },
				{ "", "By Occurance Time", "By Detection Time",
						"By User Defined Attribute" },
				{ "", "Single", "Single Deferred", "Unrestricted", "Bounded" },
				{ "", "Consume", "Reuse", "Bounded Reuse"} };

		public static String[] agentTypeOptions = { producer, consumer, epa,
				gsa };


		public static String[] getSubTypeOptions(int selectedIndex) {
			String[] rslt = null;
			switch (selectedIndex) {
			case 0: // producer
				rslt = new String[3];
				rslt[0] = new String("Hardware");
				rslt[1] = new String("Human");
				rslt[2] = new String("Software");
				break;
			case 1: // consumer
				rslt = new String[3];
				rslt[0] = new String("Hardware");
				rslt[1] = new String("Human");
				rslt[2] = new String("Software");
				break;
			case 2: // epa
				rslt = new String[8];
				rslt[0] = new String("Filter");
				rslt[1] = new String("Translate");
				rslt[2] = new String("Aggregate");
				rslt[3] = new String("Split");
				rslt[4] = new String("Compose");
				rslt[5] = new String("Enrich");
				rslt[6] = new String("Project");
				rslt[7] = new String(patternDetectionEPA);
				break;
			case 3: // gsa
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

		public MyVertex(String name) {
			this.agentId = name;
		}

		public String getAgentId() {
			return agentId;
		}

		/**
		 * sets the agent identifier, checks that the id is unique as it is used
		 * to build the edge references and cannot repeat between two different
		 * agents.
		 * 
		 * @param agentId
		 */
		public void setAgentId(String agentId) throws UniqueNameException {
			for (MyVertex v : MainEditor.g.getVertices()) {
				if (v.equals(this))
					continue;
				if (v.getAgentId().compareTo(agentId) == 0){
					throw new UniqueNameException("AgentName must be unique. " + agentId
							+ " was already used.");	
				}
				
			}
			this.agentId = agentId;
		}

		public String getAgentType() {
			return agentType;
		}

		public void setAgentType(String agentType) {
			this.agentType = agentType;
		}

		public String getAgentSubType() {
			return agentSubType;
		}

		public void setAgentSubType(String agentSubType) {
			this.agentSubType = agentSubType;
		}

		public EPAFiltering getAgentFiltering() {
			return agentFiltering;
		}

		public void setAgentFilter(EPAFiltering agentFiltering) {
			this.agentFiltering = agentFiltering;
		}

		public EPAPattern getAgentPattern() {
			return agentPattern;
		}

		public void setAgentPattern(EPAPattern agentPattern) {
			this.agentPattern = agentPattern;
		}

		public EPADerivation getAgentDerivation() {
			return agentDerivation;
		}

		public void setAgentDerivation(EPADerivation agentDerivation) {
			this.agentDerivation = agentDerivation;
		}

		public String toString() {
			return this.agentId + ":"
					+ (this.agentType == null ? "" : agentType);
		}

		@Override
		public String getName() {
			return this.agentId;
		}

		public String getID() {
			return this.agentId;
		}

	}

	public static class MyEdge extends ViewableGraphElement {
		private String name;

		private String source;
		private String destination;

		private int[] eventIds;
		private String[] events;

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public MyEdge(String name) {
			this.name = name;

		}

		public int[] getEventIds() {
			return eventIds;
		}

		public void setEvents(int[] eventIds) {
			this.eventIds = eventIds;
			this.events = new String[eventIds.length];
			for (int i = 0; i < events.length; i++)
				events[i] = new String(
						MainEditor.events.get(eventIds[i]).eventTypeIdentifier);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void reloadEventIds(){
			eventIds = null;
			restoreEventIds();
		}
		private void restoreEventIds() {
			if (eventIds == null && events != null) {
				eventIds = new int[events.length];
				for (int i = 0; i < eventIds.length; i++) {
					int k;
					for (k = 0; MainEditor.events.get(k).eventTypeIdentifier
							.compareTo(events[i]) != 0; k++)
						;
					eventIds[i] = k;
				}
			}			
		}

		public String toString() {
			// if loaded from XML the description will be there but the ids not.
			restoreEventIds();

			String rslt;
			if (eventIds == null)
				rslt = name;
			else {
				rslt = "";
				for (int i = 0; i < eventIds.length; i++) {

					rslt += MainEditor.events.get(eventIds[i]).eventTypeIdentifier
							+ (i != eventIds.length - 1 ? "," : "");
				}
			}

			return rslt;
		}

	}

	// Single factory for creating Vertices...
	public static class MyVertexFactory implements Factory<MyVertex> {
		private static int nodeCount = 0;
		private static MyVertexFactory instance = new MyVertexFactory();

		private MyVertexFactory() {
		}

		public static MyVertexFactory getInstance() {
			return instance;
		}

		public GraphElements.MyVertex create() {
			String name = "Agent" + nodeCount++;
			MyVertex v = new MyVertex(name);
			return v;
		}

	}

	// Singleton factory for creating Edges...
	public static class MyEdgeFactory implements Factory<MyEdge> {
		private static int linkCount = 0;
		private static double defaultWeight;
		private static double defaultCapacity;

		private static MyEdgeFactory instance = new MyEdgeFactory();

		private MyEdgeFactory() {
		}

		public static MyEdgeFactory getInstance() {
			return instance;
		}

		public GraphElements.MyEdge create() {
			String name = "Link" + linkCount++;
			MyEdge link = new MyEdge(name);

			return link;
		}

		public static double getDefaultWeight() {
			return defaultWeight;
		}

		public static void setDefaultWeight(double aDefaultWeight) {
			defaultWeight = aDefaultWeight;
		}

		public static double getDefaultCapacity() {
			return defaultCapacity;
		}

		public static void setDefaultCapacity(double aDefaultCapacity) {
			defaultCapacity = aDefaultCapacity;
		}

	}

}
