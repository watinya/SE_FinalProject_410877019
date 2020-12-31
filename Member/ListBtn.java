package Member;
import javax.swing.JButton;

public class ListBtn extends JButton {
	private String str;
	private int row;
	private int column;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setStr(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public ListBtn(String name) {
		super(name);
	}

	public ListBtn() {
		
	}
}