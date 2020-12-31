import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Member.Administrator;

@SuppressWarnings("serial")
public class addCourseFrame extends JFrame implements ActionListener{
	private JLabel Jlb_time = new JLabel("學期：");
    private JLabel Jlb_id = new JLabel("代碼：");
    private JLabel Jlb_subject = new JLabel("名稱：");
    private JLabel Jlb_credit = new JLabel("學分數：");
    private JLabel Jlb_teacher = new JLabel("教授：");
    private JLabel Jlb_type = new JLabel("類型：");
    private JTextField jTime = new JTextField(5);
    private JTextField jId = new JTextField(5);
    private JTextField jSubject = new JTextField(20);
    private JTextField jCredit = new JTextField(3);
    private JTextField jTeacher = new JTextField(15);
    private ButtonGroup bg = new ButtonGroup();
    private JButton Jbtn_confirm = new JButton("新增");
    private String type;
	public Administrator user;;
    
    public addCourseFrame(Administrator user) {
        super("新增課程");
        Container c = getContentPane();
        this.user = user;
        c.setLayout(null);
        
        //設定學期標籤大小位置及顯示字型
        Jlb_time.setLocation(289,62);
        Jlb_time.setSize(100,47);
        Jlb_time.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_time);
        //設定學期輸入框大小位置及顯示字型
        jTime.setLocation(403,69);
        jTime.setSize(220,40);
        jTime.setFont(new Font("微軟正黑體", Font.BOLD,24));
        c.add(jTime);
        
        //設定代碼標籤大小位置及顯示字型
        Jlb_id.setLocation(289,122);
        Jlb_id.setSize(100,47);
        Jlb_id.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_id);
        //設定代碼輸入框大小位置及顯示字型
        jId.setLocation(403,129);
        jId.setSize(220,40);
        jId.setFont(new Font("微軟正黑體", Font.BOLD,24));
        c.add(jId);
        
        //設定課程名稱標籤大小位置及顯示字型
        Jlb_subject.setLocation(289,182);
        Jlb_subject.setSize(100,47);
        Jlb_subject.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_subject);
        //設定課程名稱輸入框大小位置及顯示字型
        jSubject.setLocation(403,189);
        jSubject.setSize(220,40);
        jSubject.setFont(new Font("微軟正黑體", Font.BOLD,24));
        c.add(jSubject);
        
        //設定學分數標籤大小位置及顯示字型
        Jlb_credit.setLocation(260,238);
        Jlb_credit.setSize(123,47);
        Jlb_credit.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_credit);
        //設定學分數輸入框大小位置及顯示字型
        jCredit.setLocation(403,245);
        jCredit.setSize(220,40);
        jCredit.setFont(new Font("微軟正黑體", Font.BOLD,24));
        c.add(jCredit);
        
        //設定教授標籤大小位置及顯示字型
        Jlb_teacher.setLocation(289,298);
        Jlb_teacher.setSize(100,47);
        Jlb_teacher.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_teacher);
        //設定教授輸入框大小位置及顯示字型
        jTeacher.setLocation(403,302);
        jTeacher.setSize(220,40);
        jTeacher.setFont(new Font("微軟正黑體", Font.BOLD,24));
        c.add(jTeacher);
        
        //設定類型標籤大小位置及顯示字型
        Jlb_type.setLocation(289,355);
        Jlb_type.setSize(100,47);
        Jlb_type.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_type);
        
        //設定類型按鈕大小位置及顯示字型
        //必修按鈕
        JRadioButton rbRequire = new JRadioButton("必修");
        rbRequire.setLocation(403,355);
        rbRequire.setSize(100,47);
        rbRequire.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        rbRequire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = rbRequire.getText();
            }
        });
        bg.add(rbRequire);
        c.add(rbRequire);
        //選修按鈕
        JRadioButton rbSelect = new JRadioButton("選修");
        rbSelect.setLocation(523,355);
        rbSelect.setSize(100,47);
        rbSelect.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        rbSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = rbSelect.getText();
            }
        });
        bg.add(rbSelect);
        c.add(rbSelect);
        
        //設定新增按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(376,431);
        Jbtn_confirm.setSize(142,47);
        Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
       
        //設定視窗
        setSize(900, 600);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == Jbtn_confirm) {
    		String year = jTime.getText();
    		String id = jId.getText();
    		String subject = jSubject.getText();
    		String credit = jCredit.getText();
    		String teacher = jTeacher.getText();
    		if(year.equals("") || id.equals("") || subject.equals("") || credit.equals("") || teacher.equals("") || type.equals(null)) {
    			JOptionPane.showMessageDialog(null, "請輸入課程資訊");
    		}
    	    else {
    		    if(user.addSubject(year, id, subject, credit, type, teacher)){
                    this.dispose();                
                }
    		}
    	}
    }
}