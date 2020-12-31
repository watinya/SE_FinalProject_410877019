import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.*;

import Member.Administrator;

@SuppressWarnings("serial")
public class removeStuCourseFrame extends JFrame implements ActionListener {
	private JLabel Jlb_title = new JLabel("請選擇要新增的課程");
	private JLabel Jlb_time = new JLabel("學期：");
	private JComboBox<String> jcb_semester;
    private JLabel Jlb_subject = new JLabel("名稱：");
    private JComboBox<Object> jcb_course;
    private JButton Jbtn_confirm = new JButton("確認");
	public Administrator user;
	public String id;

	public removeStuCourseFrame(Administrator user, String id) {
		super("刪除學生選修課程");
        Container c = getContentPane();
        this.user = user;
        this.id = id;
        c.setLayout(null);
        
        //設定標題標籤大小位置及顯示字型
        Jlb_title.setLocation(86,13);
        Jlb_title.setSize(287,47);
        Jlb_title.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_title);
        
        //設定學期標籤大小位置及顯示字型
        Jlb_time.setLocation(73,62);
        Jlb_time.setSize(100,40);
        Jlb_time.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        c.add(Jlb_time);
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
		for(int i = directoriesSemester.length - 1; i >= 0 ; i--) {
			jcb_semester.addItem(directoriesSemester[i]);
    	}
    	jcb_semester.setLocation(157, 65);
    	jcb_semester.setSize(134, 40);
    	jcb_semester.setFont(new Font("微軟正黑體",Font.BOLD,22));
    	jcb_semester.addItemListener(new ItemListener() {
    		@Override
    		public void itemStateChanged(ItemEvent e) {
				String selectedSemester = (String) jcb_semester.getSelectedItem();
				jcb_course.removeAllItems();
				Object[][] data = user.getStudentCourseList(selectedSemester, id);
				for(int i = 0; i < data.length; i++) {
					jcb_course.addItem(data[i][1]);
				}
    		}
    	});
    	c.add(jcb_semester);
        
        //設定課程名稱標籤大小位置及顯示字型
        Jlb_subject.setLocation(73,113);
        Jlb_subject.setSize(100,40);
        Jlb_subject.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        c.add(Jlb_subject);
        //設定課程下拉式選單大小位置及顯示字型
        jcb_course = new JComboBox<Object>();
        jcb_course.setBounds(157, 115, 238, 37);
        jcb_course.setFont(new Font("微軟正黑體",Font.BOLD,22));
		c.add(jcb_course);
        
        //設定新增按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(157,166);
        Jbtn_confirm.setSize(105,36);
        Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
       
        //設定視窗
        setSize(450, 250);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Jbtn_confirm) {
			String selectedSemester = (String) jcb_semester.getSelectedItem();
			String selectedCourse = (String) jcb_course.getSelectedItem();
			if(selectedSemester.equals("") || selectedCourse.equals("")) {
				JOptionPane.showMessageDialog(null, "請選擇課程");
			}
			else {
				user.removeStudentCourse(selectedSemester, selectedCourse, id);
				this.dispose();
			}
		}
	}
}