import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Member.Student;

import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;

@SuppressWarnings("serial")
public class searchScoreFrame extends JFrame {
	private JLabel Jlb_semester = new JLabel("學期：");
	private JComboBox<String> jcb_time = new JComboBox<String>();
	
	Student user;
	private DefaultTableModel tableM;
	
    public searchScoreFrame(Student user)
    {
        super("高燕大課程平台 成績查詢");
        this.user = user;
        Container c = getContentPane();
        c.setLayout(null);
                
        //設定學期標籤大小位置及顯示字型
        Jlb_semester.setLocation(183,13);
        Jlb_semester.setSize(94,46);
        Jlb_semester.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_semester);
        
        //設定下拉式選單大小位置及顯示字型
        File file = new File("data\\course");
    	String[] directories = file.list(new FilenameFilter() {
    	  	@Override
    	  	public boolean accept(File current, String name) {
    	    	return new File(current, name).isDirectory();
    	  	}
    	});
        jcb_time.addItem("請選擇");
    	for(int i = directories.length - 1; i >= 0 ; i--) {
    		jcb_time.addItem(directories[i]);
    	}
        jcb_time.setLocation(274, 22);
        jcb_time.setSize(130, 36);
        jcb_time.setFont(new Font("微軟正黑體",Font.BOLD,22));
        jcb_time.addItemListener(new ItemListener() {
    		@Override
    		public void itemStateChanged(ItemEvent e) {
    	        String selectedSemester = (String) jcb_time.getSelectedItem();
    	        Object[][] myCourse = user.getScoreList(selectedSemester);
    	        cleanTable(tableM);
    	        for(int i = 0; i < myCourse.length; i++) {
    	        	tableM.addRow(myCourse[i]);
    	        }
    		}
    	});
        c.add(jcb_time);
        
        //表格內容
    	String[] columns = {"課程名稱", "成績"};
		tableM = new DefaultTableModel(null, columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		if (column < 2)
					return false;
				else
					return true;
			}// 表格不允許被編輯
	    };
	    JTable courseTable = new JTable(tableM) ;
	    //表格標題大小
	    JTableHeader head = courseTable.getTableHeader();
	    head.setFont(new Font("微軟正黑體", Font.BOLD, 26));
	    head.setPreferredSize(new Dimension(head.getWidth(), 32));
	    //表格大小
	    courseTable.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
	    courseTable.setRowHeight(28);
	    
	    JScrollPane coursePane = new JScrollPane(courseTable);
		TableColumn column;
		for (int i = 0; i < columns.length; i++) {
			column = courseTable.getColumnModel().getColumn(i);
			column.setPreferredWidth(200);
		}
		coursePane.setLocation(14, 66);
		coursePane.setSize(566, 686);
		c.add(coursePane);
		
        //設定視窗
        setSize(600, 800);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
	}
	
	// 清空表單method
	public static void cleanTable(DefaultTableModel table) {
		while (table.getRowCount() > 0)
			table.removeRow(0);
	}
}