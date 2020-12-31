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
public class AdministratorWriteScoreFrame extends JFrame implements ActionListener {
	private JLabel Jlb_semester = new JLabel("學期：");
	private JComboBox<String> jcb_semester;
	private JLabel Jlb_course = new JLabel("課程：");
	private JComboBox<String> jcb_course;
	private JButton Jbtn_confirm = new JButton("確認");
	private Administrator user;
	private DefaultTableModel tableM;
    
	public AdministratorWriteScoreFrame(Administrator user)
    {
        super("高燕大課程平台 輸入成績");
		Container c = getContentPane();
		this.user = user;
        c.setLayout(null);
                
        //設定學期標籤大小位置及顯示字型
        Jlb_semester.setLocation(28,13);
        Jlb_semester.setSize(77,40);
        Jlb_semester.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_semester);
        
        //設定學期下拉式選單大小位置及顯示字型
        File fileSemester = new File("data\\course");
    	String[] directoriesSemester = fileSemester.list(new FilenameFilter() {
    	  	@Override
    	  	public boolean accept(File current, String name) {
    	    	return new File(current, name).isDirectory();
    	  	}
    	});
		jcb_semester = new JComboBox<String>();
		jcb_semester.addItem("請選擇");
    	for(int i = directoriesSemester.length - 1; i >= 0; i--) {
    		jcb_semester.addItem(directoriesSemester[i]);
    	}
    	jcb_semester.setLocation(102, 19);
    	jcb_semester.setSize(134, 31);
    	jcb_semester.setFont(new Font("微軟正黑體",Font.BOLD,22));
    	jcb_semester.addItemListener(new ItemListener() {
    		@Override
    		public void itemStateChanged(ItemEvent e) {
				String selectedSemester = (String) jcb_semester.getSelectedItem();
    			File fileCourse = new File("data\\course\\"+selectedSemester);
				jcb_course.removeAllItems();
				String[] directories = fileCourse.list(new FilenameFilter() {
					@Override
					public boolean accept(File current, String name) {
						 	if(name.lastIndexOf('.')>0) {
			                  	int lastIndex = name.lastIndexOf('.');
			                  	String str = name.substring(lastIndex);
			                  	if(str.equals(".txt")) {
			                	  	return true;
			                  	}
						 	}
			             	return false;
						}
				  	});
				for(int i = 0; i < directories.length; i++) {
					directories[i] = directories[i].replace(".txt", "");
					jcb_course.addItem(directories[i]);
				}
    		}
    	});
    	c.add(jcb_semester);
        
        //設定課程標籤大小位置及顯示字型
        Jlb_course.setLocation(250,13);
        Jlb_course.setSize(77,38);
        Jlb_course.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_course);
        
        //設定課程下拉式選單大小位置及顯示字型
        jcb_course = new JComboBox<String>();
        jcb_course.setBounds(324, 19, 242, 31);
        jcb_course.setFont(new Font("微軟正黑體",Font.BOLD,22));
        jcb_course.addItemListener(new ItemListener() {
    		@Override
    		public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					Object[][] data = user.getCourseStudentScore((String) jcb_semester.getSelectedItem(), (String) jcb_course.getSelectedItem());
					cleanTable(tableM);
					for(int i = 0; i < data.length; i++){
						tableM.addRow(data[i]);
					}
				}
			}
    	});
		getContentPane().add(jcb_course);
		        
        //表格內容
    	String[] columns = { "學號", "姓名", "成績"};
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
			column.setPreferredWidth(100);
		}
		coursePane.setLocation(14, 66);
		coursePane.setSize(566, 631);
		c.add(coursePane);
		
		//設定確認按鈕大小位置及顯示字型
		Jbtn_confirm.setBounds(216, 710, 127, 42);
		getContentPane().add(Jbtn_confirm);
		Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 22));
		Jbtn_confirm.addActionListener(this);
		
        //設定視窗
        setSize(600, 800);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
    }

	@Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Jbtn_confirm) {
			boolean flag = true;
			for (int i = 0; i < tableM.getColumnCount() - 1; i++) {
				String score = (String) tableM.getValueAt(i, 2);
				if (!Numornot(score)) {
					JOptionPane.showMessageDialog(new JFrame(), "成績必須為數字", "成績輸入", JOptionPane.INFORMATION_MESSAGE);
					flag = false;
				}
			}

			if(flag){
			String selectedSemester = (String) jcb_semester.getSelectedItem();
			String selectedCourse = (String) jcb_course.getSelectedItem();
			Object[][] scoreData = new Object[tableM.getRowCount()][2];
			for(int i = 0; i < scoreData.length; i++){
				scoreData[i][0] = tableM.getValueAt(i, 0);
				scoreData[i][1] = tableM.getValueAt(i, 2);
			}
			user.writeScore(selectedSemester, selectedCourse, scoreData);
			JOptionPane.showMessageDialog(new JFrame(), "成績已變更", "成績輸入", JOptionPane.INFORMATION_MESSAGE);
			}
        }
	}
	//判斷輸入成績是否為數字
	public boolean Numornot(String msg){
        try{
            Integer.parseInt(msg);
			return true;
		}catch(Exception e){return false;}
	}
	// 清空表單method
	public static void cleanTable(DefaultTableModel table) {
		while (table.getRowCount() > 0)
			table.removeRow(0);
	}
}