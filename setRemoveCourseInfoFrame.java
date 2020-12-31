import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.*;

import Member.Administrator;

@SuppressWarnings("serial")
public class setRemoveCourseInfoFrame extends JFrame implements ActionListener {
	private JLabel Jlb_title = new JLabel("請選擇要刪除的課程資訊");
	private JLabel Jlb_time = new JLabel("學期：");
	private JComboBox<String> jcb_semester;
    private JLabel Jlb_subject = new JLabel("名稱：");
    private JComboBox<String> jcb_course;
    private JButton Jbtn_confirm = new JButton("確認");
	public Administrator user;

	public setRemoveCourseInfoFrame(Administrator user) {
		super("刪除課程資訊");
        Container c = getContentPane();
        this.user = user;
        c.setLayout(null);
        
        //設定標題標籤大小位置及顯示字型
        Jlb_title.setLocation(51,13);
        Jlb_title.setSize(335,47);
        Jlb_title.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_title);
        
        //設定學期標籤大小位置及顯示字型
        Jlb_time.setLocation(73,62);
        Jlb_time.setSize(100,40);
        Jlb_time.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        c.add(Jlb_time);
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
    	jcb_semester = new JComboBox<>(new String[] {"請選擇"});
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
        
        //設定課程名稱標籤大小位置及顯示字型
        Jlb_subject.setLocation(73,113);
        Jlb_subject.setSize(100,40);
        Jlb_subject.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        c.add(Jlb_subject);
        //設定課程下拉式選單大小位置及顯示字型
        jcb_course = new JComboBox<String>();
        jcb_course.setBounds(157, 120, 220, 32);
        jcb_course.setFont(new Font("微軟正黑體",Font.BOLD,22));
		c.add(jcb_course);
        
        //設定新增按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(171,170);
        Jbtn_confirm.setSize(100,32);
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
				JOptionPane.showMessageDialog(null, "請輸入課程資訊");
			}
			else {
				user.removeSubject(selectedSemester, selectedCourse);
				this.dispose();
			}
		}
	}
}