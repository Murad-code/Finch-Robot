package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import container.ConversionEvent;

// TableModel stores the data which TablePanel uses when displaying the table in the GUI
public class TableModel extends AbstractTableModel { // AbstractTableModel is used to implements the following functions

	private List<ConversionEvent> dbList;

	private String[] colNames = { "ID", "Hex", "Decimal", "Binary", "Octal", "Speed", "Red", "Green", "Blue" };

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dbList.size();
	}

	// This is called by the TablePanel whenever it needs to update the ConversionEvent that is being used in the table
	public void SetData(List<ConversionEvent> db) {
		this.dbList = db;
	}

	@Override
	public Object getValueAt(int row, int col) {
		ConversionEvent results = dbList.get(row);

		switch (col) {
		case 0:
			return results.getId();
		case 1:
			return results.getHex();
		case 2:
			return results.getDecimal();
		case 3:
			return results.getBinary();
		case 4:
			return results.getOctal();
		case 5:
			return results.getSpeed();
		case 6:
			return results.getRed();
		case 7:
			return results.getGreen();
		case 8:
			return results.getBlue();
		}
		return null;
	}
}
