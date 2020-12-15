import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Frame_teacherFunction extends JFrame implements ActionListener {
	private JButton Jbtn_Course = new JButton("開課查詢");
    private JButton Jbtn_Score = new JButton("輸入成績");
    private JButton Jbtn_generateScore = new JButton("產生授課課程成績");
    private JButton Jbtn_ChangePW = new JButton("修改密碼");
   
    public Frame_teacherFunction()
    {
        super("高燕大課程平台 教授功能");
        Container c = getContentPane();
        c.setLayout(null);
                 
        //設定開課查詢(清單、內容、學生清單)按鈕大小位置及顯示字型
        Jbtn_Course.setLocation(63,148);
        Jbtn_Course.setSize(218,127);
        Jbtn_Course.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_Course.addActionListener(this);
        c.add(Jbtn_Course);
       
        //設定成績管理按鈕大小位置及顯示字型
        Jbtn_Score.setLocation(627,148);
        Jbtn_Score.setSize(218,127);
        Jbtn_Score.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_Score.addActionListener(this);
        c.add(Jbtn_Score);
        
        //設定產生授課課程成績按鈕大小位置及顯示字型
        Jbtn_generateScore.setLocation(345,148);
        Jbtn_generateScore.setSize(218,127);
        Jbtn_generateScore.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_generateScore.addActionListener(this);
        c.add(Jbtn_generateScore);
        
        //設定修改密碼按鈕大小位置及顯示字型
        Jbtn_ChangePW.setLocation(910,148);
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
