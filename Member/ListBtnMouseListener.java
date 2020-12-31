package Member;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;

public class ListBtnMouseListener extends MouseAdapter {
	private JTable table;
	static int Row;
	
	public ListBtnMouseListener(JTable table) {
	    this.table = table;
	  }

	public static int getRow() {
		return Row;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int column = table.getColumnModel().getColumnIndexAtX(e.getX());
		int row = e.getY() / table.getRowHeight();
		ListBtnMouseListener.Row = row;
		
		if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
			Object value = table.getValueAt(row, column);
			if (value instanceof JButton) {
				((JButton) value).doClick();
			}
		}
	}
}
