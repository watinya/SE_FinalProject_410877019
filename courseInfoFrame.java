import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Member.Administrator;

import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;

@SuppressWarnings("serial")
public class courseInfoFrame extends JFrame implements ActionListener {
	private JLabel Jlb_semester = new JLabel("學期：");
	private JComboBox<String> jcb_time;
	private JButton btn_re = new JButton("重整");
	private JButton btn_new = new JButton("新增");
	private JButton btn_modify = new JButton("修改");
	private JButton btn_delete = new JButton("刪除");
	private JTable courseTable;
	private DefaultTableModel tableM;
	private Administrator user;
	
    public courseInfoFrame(Administrator user)
    {
        super("課程資訊管理");
        Container c = getContentPane();
        this.user = user;
        c.setLayout(null);
                
        //設定學期標籤大小位置及顯示字型
        Jlb_semester.setLocation(70,12);
        Jlb_semester.setSize(94,46);
        Jlb_semester.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_semester);
        
        //設定重整按鈕大小位置及顯示字型
        btn_re.setLocation(386,21);
        btn_re.setSize(88,36);
        btn_re.setFont(new Font("微軟正黑體",Font.BOLD,22));
        btn_re.addActionListener(this);
        c.add(btn_re);
        
        //設定新增按鈕大小位置及顯示字型
        btn_new.setLocation(488,22);
        btn_new.setSize(88,36);
        btn_new.setFont(new Font("微軟正黑體",Font.BOLD,22));
        btn_new.addActionListener(this);
        c.add(btn_new);
        
        //設定修改按鈕大小位置及顯示字型
        btn_modify.setLocation(590,22);
        btn_modify.setSize(88,36);
        btn_modify.setFont(new Font("微軟正黑體",Font.BOLD,22));
        btn_modify.addActionListener(this);
        c.add(btn_modify);
        
        //設定刪除按鈕大小位置及顯示字型
        btn_delete.setLocation(692,22);
        btn_delete.setSize(88,36);
        btn_delete.setFont(new Font("微軟正黑體",Font.BOLD,22));
        btn_delete.addActionListener(this);
        c.add(btn_delete);
        
        //設定下拉式選單大小位置及顯示字型
        File file = new File("data\\course");
    	String[] directories = file.list(new FilenameFilter() {
    	    @Override
    	    public boolean accept(File current, String name) {
    	        return new File(current, name).isDirectory();
    	    }
    	});
		jcb_time = new JComboBox<String>();
		jcb_time.addItem("請選擇");
		for(int i = directories.length - 1; i >= 0 ; i--) {
    		jcb_time.addItem(directories[i]);
    	}
        jcb_time.setLocation(161, 21);
        jcb_time.setSize(130, 36);
        jcb_time.setFont(new Font("微軟正黑體",Font.BOLD,22));
        jcb_time.addActionListener(this);
        c.add(jcb_time);
        
        //表格內容
    	String[] columns = { "開課代號", "課程名稱", "學分數", "科目型態", "授課教師"};
		tableM = new DefaultTableModel(null, columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		if (column < 5)
					return false;
				else
					return true;
			}// 表格不允許被編輯
	    };
	    courseTable = new JTable(tableM) ;
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
			if (i == 1) {
				column.setPreferredWidth(200); //second column is bigger
			} else {
				column.setPreferredWidth(100);
			}
		}
		coursePane.setLocation(14, 72);
		coursePane.setSize(766, 480);
		c.add(coursePane);
		
        //設定視窗
        setSize(800, 600);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jcb_time) {
			String selectedSemester = (String) jcb_time.getSelectedItem();
			Object[][] courseList = user.getYearSubjectInformationList(selectedSemester);
			cleanTable(tableM);
			for(int i = 0; i < courseList.length; i++) {
				tableM.addRow(courseList[i]);
			}
        }
        else if(e.getSource() == btn_re) {
        	this.dispose();
        	new courseInfoFrame(user);
        }
        else if(e.getSource() == btn_new) {
        	new addCourseFrame(user);
        }
        else if(e.getSource() == btn_modify) {
        	new setChangeCourseInfoFrame(user);
        }
        else if(e.getSource() == btn_delete) {
        	new setRemoveCourseInfoFrame(user);
        }
	}
	
    // 清空表單method
	public static void cleanTable(DefaultTableModel table) {
		while (table.getRowCount() > 0)
			table.removeRow(0);
	}
}