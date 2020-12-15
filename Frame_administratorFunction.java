import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Frame_administratorFunction extends JFrame implements ActionListener {
	private JButton Jbtn_Course = new JButton("開課查詢");
	private JButton Jbtn_accountManage = new JButton("帳戶管理");
	private JButton Jbtn_studentManage = new JButton("學生資訊管理");
	private JButton Jbtn_courseManage = new JButton("課程資訊管理");
    private JButton Jbtn_electiveCourseMange = new JButton("學生選修課程管理");
    private JButton Jbtn_electiveCourseScore = new JButton("選修課程成績管理");
    private JButton Jbtn_generateTranscripts = new JButton("產生成績單");
    private JButton Jbtn_ChangePW = new JButton("修改密碼");
   
    public Frame_administratorFunction()
    {
        super("高燕大課程平台 管理員功能");
        Container c = getContentPane();
        c.setLayout(null);
                 
        //設定開課查詢(清單、內容、學生清單)按鈕大小位置及顯示字型
        Jbtn_Course.setLocation(909,150);
        Jbtn_Course.setSize(218,127);
        Jbtn_Course.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_Course.addActionListener(this);
        c.add(Jbtn_Course);
       
        //設定帳號管理(新增、刪除、修改)按鈕大小位置及顯示字型
        Jbtn_accountManage.setLocation(63,150);
        Jbtn_accountManage.setSize(218,127);
        Jbtn_accountManage.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_accountManage.addActionListener(this);
        c.add(Jbtn_accountManage);
        
        //設定學生資訊(姓名、學號、入學年分)管理按鈕大小位置及顯示字型
        Jbtn_studentManage.setLocation(345,150);
        Jbtn_studentManage.setSize(218,127);
        Jbtn_studentManage.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_studentManage.addActionListener(this);
        c.add(Jbtn_studentManage);
        
        //設定課程資訊管理按鈕(代碼、名稱、學分、教授、類型)大小位置及顯示字型
        Jbtn_courseManage.setLocation(627,150);
        Jbtn_courseManage.setSize(218,127);
        Jbtn_courseManage.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_courseManage.addActionListener(this);
        c.add(Jbtn_courseManage);
        
        //設定學生選修課程管理(新增、刪除、修改)按鈕大小位置及顯示字型
        Jbtn_electiveCourseMange.setLocation(345,329);
        Jbtn_electiveCourseMange.setSize(218,127);
        Jbtn_electiveCourseMange.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_electiveCourseMange.addActionListener(this);
        c.add(Jbtn_electiveCourseMange);
        
        //設定選修課程成績管理(輸入、更新)按鈕大小位置及顯示字型
        Jbtn_electiveCourseScore.setLocation(63,329);
        Jbtn_electiveCourseScore.setSize(218,127);
        Jbtn_electiveCourseScore.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_electiveCourseScore.addActionListener(this);
        c.add(Jbtn_electiveCourseScore);
        
        //設定產生成績單按鈕大小位置及顯示字型
        Jbtn_generateTranscripts.setLocation(627,329);
        Jbtn_generateTranscripts.setSize(218,127);
        Jbtn_generateTranscripts.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_generateTranscripts.addActionListener(this);
        c.add(Jbtn_generateTranscripts);
        
        //設定修改密碼按鈕大小位置及顯示字型
        Jbtn_ChangePW.setLocation(909,329);
        Jbtn_ChangePW.setSize(218,127);
        Jbtn_ChangePW.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_ChangePW.addActionListener(this);
        c.add(Jbtn_ChangePW);
       
        //設定視窗
        setSize(1200, 800);
        setLocationRelativeTo(null);//視窗置中
        //setLocation(300,200);
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_ChangePW) {
        	
			new Frame_changePassword();
        }
        else if(e.getSource() == Jbtn_Course) {
        	new Frame_course();
        }
    }
}