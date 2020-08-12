package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import container.ConversionEvent;

// TablePanel is responsible for the table's GUI
public class TablePanel extends JPanel {

	public JTable table;
	private TableModel tableModel;

	public TablePanel() {
		tableModel = new TableModel();
		table = new JTable(tableModel);

		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public void SetData(List<ConversionEvent> db) {
		tableModel.SetData(db);
	}

	// When user runs the program again, this function updates the values needed to be displayed in the table
	public void Refresh() {
		tableModel.fireTableDataChanged();
	}

}
