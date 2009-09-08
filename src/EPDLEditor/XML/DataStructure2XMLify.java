package EPDLEditor.XML;


import java.util.ArrayList;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;

import EPDLEditor.MainEditor;
import EPDLEditor.Types.Context;
import EPDLEditor.Types.Event;
import EPDLEditor.Types.GraphElements;
import EPDLEditor.Types.GraphElements.MyEdge;
import EPDLEditor.Types.GraphElements.MyVertex;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;

public class DataStructure2XMLify {

	private GraphElements.MyVertex[] agents;
	private GraphElements.MyEdge[] channels;
	private Event[] eventTypes;
	private Context[] contexts;
	
	public DataStructure2XMLify(DirectedSparseMultigraph<MyVertex, MyEdge> myGraph, ArrayList<Event> events, ArrayList<Context> contexts){
		int i=0;
		this.contexts = new Context[contexts.size()];
		for (Iterator iter=contexts.iterator(); iter.hasNext();i++){
			this.contexts[i]=(Context)iter.next();
		}
		i=0;
		agents = new GraphElements.MyVertex[myGraph.getVertexCount()];
		for (Iterator iter=myGraph.getVertices().iterator(); iter.hasNext();i++){
			agents[i]=(GraphElements.MyVertex)iter.next();
		}
		i=0;
		this.eventTypes = new Event[events.size()];
		for (Iterator iter=events.iterator(); iter.hasNext();i++){
			this.eventTypes[i]=(Event)iter.next();
		}
		i=0;
		channels = new GraphElements.MyEdge[myGraph.getEdgeCount()];
		for (Iterator iter=myGraph.getEdges().iterator(); iter.hasNext();i++){
            GraphElements.MyEdge link = (GraphElements.MyEdge)iter.next();
            link.setSource(MainEditor.g.getSource(link).getName());
            link.setDestination(MainEditor.g.getDest(link).getName());
			channels[i]=link;
		}
	}

	public void populateGraphAndEvents(DirectedSparseMultigraph<MyVertex, MyEdge> g, ArrayList<Event> events, ArrayList<Context> contexts) {
		// event types has to be populated first as the edges assume their existance.
		for (int i=0; i<this.eventTypes.length;i++){
			events.add(this.eventTypes[i]);
		}
		// next we need to populate the contexts.
		for (int i=0; i<this.contexts.length;i++){
			contexts.add(this.contexts[i]);
		}	
		for (int i=0; i<agents.length;i++){
			g.addVertex(agents[i]);
		}
		for (int j=0; j<channels.length;j++){
			int i;
			for (i=0; agents[i].getName().compareTo(channels[j].getSource())!=0;i++);
			GraphElements.MyVertex sourceVertex = agents[i];
			
			for (i=0; agents[i].getName().compareTo(channels[j].getDestination())!=0;i++);
			GraphElements.MyVertex destVertex = agents[i];

			g.addEdge(channels[j], sourceVertex, destVertex);
		}
		
	}

	public static XStream getXStreamInstance() {
		XStream xstream = new XStream();
		xstream.omitField(MyEdge.class, "eventIds");
		xstream.omitField(MyVertex.class, "contextIds");
		xstream.alias("agent", GraphElements.MyVertex.class);
		xstream.alias("channel", GraphElements.MyEdge.class);
		xstream.alias("EPDL", DataStructure2XMLify.class);
		xstream.alias("referenceIdentifier", String.class);
		xstream.alias("type", EPDLEditor.Types.Event.class);
		xstream.alias("attribute", EPDLEditor.Types.Attribute.class);
		xstream.alias("context", EPDLEditor.Types.Context.class);
		xstream.alias("PartitionParameter", EPDLEditor.Types.PartitionParameter.class);
		return xstream;
	}
}
