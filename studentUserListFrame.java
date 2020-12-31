import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Member.Administrator;

@SuppressWarnings("serial")
public class studentUserListFrame extends JFrame implements ActionListener{
	private JFrame f;
	private JTable jt;
	private Administrator user;

	public studentUserListFrame(Administrator user) {
		this.user = user;

		//設定框架
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		f = new JFrame("學生資訊");
		f.setSize(800, 600);
		f.setLocationRelativeTo(null);
		Container cp = f.getContentPane();

		//建立表格內容
		String[] columns = {"學號", "姓名", "入學年分"};
		jt = new JTable(user.getAllStudentsInformationList(), columns) {
			@Override
	    	public boolean isCellEditable(int row, int column) {
	    		if (column < columns.length)
					return false;
				else
					return true;
			}// 表格不允許被編輯
		};
		//表格寬度
		TableColumn column=jt.getColumnModel().getColumn(0);
	    column.setPreferredWidth(80);
	    column=jt.getColumnModel().getColumn(1);
	    column.setPreferredWidth(80);
	    column=jt.getColumnModel().getColumn(2);
	    column.setPreferredWidth(50);
		cp.add(new JScrollPane(jt), BorderLayout.CENTER);

		//表格標題大小
	    JTableHeader head = jt.getTableHeader();
	    head.setFont(new Font("微軟正黑體", Font.BOLD, 26));
	    head.setPreferredSize(new Dimension(head.getWidth(), 32));
	    //表格大小
	    jt.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
	    jt.setRowHeight(28);

		//增加按鈕
		JPanel panel = new JPanel(new GridLayout(4,1));
		JButton btnReset = new JButton("重新整理");
		btnReset.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		btnReset.addActionListener(this);
		panel.add(btnReset);

		JButton btnNew = new JButton("新增學生資訊");
		btnNew.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		btnNew.addActionListener(this);
		panel.add(btnNew);

		JButton btnChange = new JButton("修改學生資訊");
		btnChange.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		btnChange.addActionListener(this);
		panel.add(btnChange);

		JButton btnDelete = new JButton("刪除學生資訊");
		btnDelete.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		btnDelete.addActionListener(this);
		panel.add(btnDelete);

		cp.add(panel,BorderLayout.SOUTH);
		f.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("重新整理")) {
			f.dispose();
			new studentUserListFrame(user);
		}
		if(cmd.equals("新增學生資訊")) {
			new addStudentInfoFrame(user);
		}
		if(cmd.equals("修改學生資訊")) {
			new setChangeStuInfoFrame(user);
		}
		if(cmd.equals("刪除學生資訊")) {
			new setRemoveStuInfoFrame(user);
		}
	}
}//end class 