package EPDLEditor.UI.renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class TextAreaRenderer extends JTextArea
implements TableCellRenderer {

	public TextAreaRenderer() {
	setLineWrap(true);
	setWrapStyleWord(true);
	this.setRows(5);
	this.setAlignmentX(25);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setText((String)value);
		return this;
	}
}