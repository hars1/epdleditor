/*
 * EdgePropertyDialog.java
 *
 * Created on March 22, 2007, 2:23 PM
 * Copyright 2007 Grotto Networking
 */

package EPDLEditor.UI.Dialogs;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import EPDLEditor.MainEditor;
import EPDLEditor.Types.Attribute;
import EPDLEditor.Types.Event;

/**
 *
 * @author  Ronen Vaisenberg and Greg
 */
public class EventTypePropertyDialog extends javax.swing.JDialog {
    Event event;
    
    /** Creates new form EdgePropertyDialog */
    public EventTypePropertyDialog(java.awt.Frame parent, Event event) {
        super(parent, true);
        initComponents();
        this.event = event;
        setTitle("Event Type Definition");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTemporalGranularityComboBox = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEventTypeIDTextPane = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPayloadTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDoneButton = new javax.swing.JButton();
        jEventCompIndicComboBox = new javax.swing.JComboBox();
        jReferencedEventScrollPane = new javax.swing.JScrollPane();
        jReferencedEvent = new javax.swing.JList();
        jReferenceRelationship = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Event Type Properties");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTemporalGranularityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Second", "Minute", "Hour", "Day", "Millisecond" }));

        jScrollPane2.setViewportView(jEventTypeIDTextPane);

        jLabel7.setText("Event Temporal Granularity:");

        jLabel3.setText("Event to Event Relations");

        jLabel6.setText("Event Composition Indicator:");

        jPayloadTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPayloadTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Attribute Name", "Data Type", "Semantic Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jPayloadTable.setColumnSelectionAllowed(true);
        jPayloadTable.setDragEnabled(true);
        jPayloadTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jPayloadTable);
        jPayloadTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel1.setText("Header");

        jLabel5.setText("Event Type Identifier:");

        jLabel2.setText("Payload");

        jDoneButton.setText("Done");
        jDoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoneButtonokButtonHandler(evt);
            }
        });

        jEventCompIndicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "False", "True" }));
        jEventCompIndicComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEventCompIndicComboBoxActionPerformed(evt);
            }
        });

        jReferencedEvent.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Event 1", "Event 2", "Event 3", "Derived Event 1", "Derived Event 2", "Derived Event 3" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jReferencedEvent.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jReferencedEventScrollPane.setViewportView(jReferencedEvent);

        jReferenceRelationship.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "Generalization", "Specialization", "Retraction" }));
        jReferenceRelationship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReferenceRelationshipActionPerformed(evt);
            }
        });
        jReferenceRelationship.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jReferenceRelationshipPropertyChange(evt);
            }
        });

        jLabel4.setText("Event Type");

        jLabel8.setText("Relationship");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel4)
                            .add(jLabel1)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel7)
                                    .add(jLabel5)
                                    .add(jLabel6))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jEventCompIndicComboBox, 0, 322, Short.MAX_VALUE)
                                    .add(jTemporalGranularityComboBox, 0, 322, Short.MAX_VALUE)
                                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)))
                            .add(jLabel3)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 434, Short.MAX_VALUE)
                                .add(jDoneButton))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(jReferencedEventScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 267, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel8)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 160, Short.MAX_VALUE))
                                    .add(jReferenceRelationship, 0, 238, Short.MAX_VALUE)))
                            .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .add(jLabel2)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel5)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jEventCompIndicComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jTemporalGranularityComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(4, 4, 4)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(8, 8, 8)
                .add(jLabel3)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jLabel8))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(layout.createSequentialGroup()
                        .add(jReferenceRelationship, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jDoneButton))
                    .add(jReferencedEventScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDoneButtonokButtonHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoneButtonokButtonHandler
        store();
        setTitle(event.toString());
        MainEditor.vv.repaint();
        dispose();
    }//GEN-LAST:event_jDoneButtonokButtonHandler

    private void jEventCompIndicComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEventCompIndicComboBoxActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jEventCompIndicComboBoxActionPerformed

    public void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        /**
        *
        * The line below is due to a bug in JAVA release, see
        * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4503845
        */
    	jPayloadTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    	
    	TableColumn semanticRole = jPayloadTable.getColumnModel().getColumn(2);

    	JComboBox comboBox = new JComboBox();
    	comboBox.addItem("");
    	comboBox.addItem("Common Attribute");
    	comboBox.addItem("Entity Reference");
    	semanticRole.setCellEditor(new DefaultCellEditor(comboBox));
    	
    	TableColumn payloadTypes = jPayloadTable.getColumnModel().getColumn(1);

    	JComboBox typeComboBox = new JComboBox();
    	typeComboBox.addItem("");
    	typeComboBox.addItem("String");
    	typeComboBox.addItem("Integer");
    	typeComboBox.addItem("Floating Point Number");
    	typeComboBox.addItem("Fixed Precision Decimal Number");
    	typeComboBox.addItem("Binary Data");
    	typeComboBox.addItem("Boolean");
    	typeComboBox.addItem("Time Stamp");
    	typeComboBox.addItem("Location");
    	typeComboBox.addItem("Reference to Another Event");
    	payloadTypes.setCellEditor(new DefaultCellEditor(typeComboBox));
    	
    	
    	
        jReferencedEvent.setModel(new javax.swing.AbstractListModel() {             
            public int getSize() { return MainEditor.events.size(); }
            public Object getElementAt(int i) { return MainEditor.events.get(i).eventTypeIdentifier; }
        });
        
    	populate(event);
    }//GEN-LAST:event_formComponentShown

    private void jReferenceRelationshipPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jReferenceRelationshipPropertyChange
    }//GEN-LAST:event_jReferenceRelationshipPropertyChange

    private void jReferenceRelationshipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReferenceRelationshipActionPerformed
		boolean event2event = jReferenceRelationship.getSelectedIndex()!=0;
		this.jReferencedEventScrollPane.setVisible(event2event);
		this.jLabel4.setVisible(event2event);
        SwingUtilities.invokeLater(new Runnable(){public void run(){
    		validate();
    		repaint();
    	}
    	});
    }//GEN-LAST:event_jReferenceRelationshipActionPerformed
    
    
    private void populate(Event event){  	
    	
       	//header
    	this.jEventCompIndicComboBox.setSelectedIndex(event.eventCompositionIndicator?1:0);
    	this.jTemporalGranularityComboBox.setSelectedItem(event.eventTemporalGranularity);
    	this.jEventTypeIDTextPane.setText(event.eventTypeIdentifier);
    	
    	//payload	    	
    	TableModel tm = jPayloadTable.getModel();
    	event.populateTableModelwithPayload(tm);
    	//event 2 event relations
    	this.jReferencedEvent.setSelectedValue(event.referencedEvent, true);
    	this.jReferenceRelationship.setSelectedItem(event.referenceRelationship);
    	
    }
    private void store(){
    
       	//header
    	event.eventCompositionIndicator = this.jEventCompIndicComboBox.getSelectedIndex()==1;
    	event.eventTemporalGranularity = (String)this.jTemporalGranularityComboBox.getSelectedItem();
    	event.eventTypeIdentifier=this.jEventTypeIDTextPane.getText();
    	
    	//payload
    	int payloadCount = this.jPayloadTable.getRowCount();
    	TableModel jm = this.jPayloadTable.getModel();
    	int i;
    	for (i=0; i<payloadCount;i++){
    		if (((String)jm.getValueAt(i, 0)).compareTo("")==0)
    			break;
    	}
    	payloadCount = i;
    	event.payload = new Attribute[payloadCount];
    	
    	for (i=0; i<payloadCount;i++){
    		event.payload[i] = new Attribute();
    		event.payload[i].attributeName = (String) jm.getValueAt(i, 0);
    		event.payload[i].dataType = (String) jm.getValueAt(i, 1);
    		event.payload[i].semanticRole = (String) jm.getValueAt(i, 2);
    	
    	
    	}
    	//event 2 event relations
    	event.referencedEvent = (String)this.jReferencedEvent.getSelectedValue();
    	event.referenceRelationship = (String)this.jReferenceRelationship.getSelectedItem();
    	
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jDoneButton;
    private javax.swing.JComboBox jEventCompIndicComboBox;
    private javax.swing.JTextPane jEventTypeIDTextPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTable jPayloadTable;
    private javax.swing.JComboBox jReferenceRelationship;
    private javax.swing.JList jReferencedEvent;
    private javax.swing.JScrollPane jReferencedEventScrollPane;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox jTemporalGranularityComboBox;
    // End of variables declaration//GEN-END:variables
    
}
