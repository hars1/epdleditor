# Introduction #

EPDL Editor.
A graphical tool used to define event processing networks at the conceptual level.
Maintained by Ronen Vaisenberg and Opher Etzion


# Details #

Running:
Double click the jar file, classes are compiled to JRE1.6 which can be downloaded from here (if you don't have it already): https://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/ViewProductDetail-Start?ProductRef=jre-6u14-oth-JPR@CDS-CDS_Developer

Using:

1) Define Types:
> a) click on the button: "add new event type".
> b) click on the button that appeared named: "edit type properties"
> c) make your edits and click OK.
2) Define Contexts:
> a) click on the button: "add new context".
> b) click on the button that appeared named: "edit context properties"
> c) make your edits and click OK. (Try the different lists to see the different options).
3) Define Agents:
> a) click anywhere on the gray panel, a red node will appear.
> b) right click the node, and select "edit vertex properties".
> c) make your changes, you can choose from the list of contexts that you defined in step 2, and click OK.
4) Define Channels:
> a) click on a node and drag the edge to the connected node.
> b) right click the edge and select the types that go on that edge from the list of types defined in step 1.
> c) you can choose multiple types, click OK to save.
5) Save your EPN with the type and context definitions to a readable XML:
> a) click on the XML menu item.
> b) choose save to XML.
6) Load you EPN from an already created XML file.
> a) click on the XML menu item.
> b) choose load from XML.
7) Mouse modes:
> a) click on Mouse menu item:
    1. Editing is for creating new Agents and Channels.
> > 2) Picking is for changing the layout of nodes on the panel. Layout is not stored in the EPDL XML.
> > 3) Transforming is for moving the whole graph on the panel.