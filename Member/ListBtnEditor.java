package Member;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ListBtnEditor extends DefaultCellEditor {
	private ListBtn button;
	
	public ListBtnEditor() {
		super(new JTextField());
		button = new ListBtn("查看");
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			button.setRow(row);
			button.setColumn(column);
			return button;
	}
	
}