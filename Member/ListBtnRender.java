package Member;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ListBtnRender implements TableCellRenderer {
	private ListBtn button;

	public ListBtnRender() {
		button = new ListBtn("查看");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return button;
	}

}
