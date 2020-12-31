import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Member.Teacher;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

@SuppressWarnings("serial")
public class generateScoreFrame extends JFrame {
	private JLabel Jlb_semester = new JLabel("學期：");
	private JComboBox<String> jcb_semester;
	private JLabel Jlb_course = new JLabel("課程：");
	private JComboBox<String> jcb_course;
	private DefaultTableModel tableM;
	
    public generateScoreFrame(Teacher user)
    {
        super("查看成績");
        Container c = getContentPane();
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
    			File fileCourse = new File("data\\teachers\\" + user.id + "\\指導課程.txt");
    	        String selectedSemester = (String) jcb_semester.getSelectedItem();
    	        jcb_course.removeAllItems();
    			try {
    				InputStreamReader read = new InputStreamReader(new FileInputStream(fileCourse), "utf-8");
    				BufferedReader reader = new BufferedReader(read);
    				String line, semester, name= "";
    				//抓出每個課程學期與名稱
    				while((line = reader.readLine()) != null) {
    					semester = line.split(" ")[0];
    					name = line.split(" ")[1];
    					if(semester.equals(selectedSemester)) {
    						jcb_course.addItem(name);
    					}
    				}
    				reader.close();
    			} catch (UnsupportedEncodingException | FileNotFoundException ee) {ee.printStackTrace();
    			} catch (IOException ee) {ee.printStackTrace();
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
	    		if (column < 3)
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