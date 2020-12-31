import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Member.Student;

@SuppressWarnings("serial")
public class studentFunctionFrame extends JFrame implements ActionListener {
    private JLabel userID;
    private JButton Jbtn_logout = new JButton("登出");
    private JButton Jbtn_Course = new JButton("開課查詢");
    private JButton Jbtn_Score = new JButton("成績查詢");
    private JButton Jbtn_printTranscript = new JButton("列印成績單");
    private JButton Jbtn_ChangePW = new JButton("修改密碼");
    private Student user;
    
    public studentFunctionFrame(Student user)
    {
        super("高燕大課程平台 學生功能");
        this.user = user;
        Container c = getContentPane();
        c.setLayout(null);
        
        //設定用戶ID大小位置及顯示字型
        userID= new JLabel(user.id, SwingConstants.RIGHT);
        userID.setLocation(893,13);
        userID.setSize(199,39);
        userID.setFont(new Font("微軟正黑體",Font.BOLD,22));
        c.add(userID);
        
        //設定登出按鈕大小位置及顯示字型
        Jbtn_logout.setLocation(1098,13);
        Jbtn_logout.setSize(82,39);
        Jbtn_logout.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_logout.addActionListener(this);
        c.add(Jbtn_logout);

        //設定開課查詢(清單、內容、學生清單)按鈕大小位置及顯示字型
        Jbtn_Course.setLocation(64,148);
        Jbtn_Course.setSize(218,127);
        Jbtn_Course.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_Course.addActionListener(this);
        c.add(Jbtn_Course);
       
        //設定成績查詢按鈕大小位置及顯示字型
        Jbtn_Score.setLocation(346,148);
        Jbtn_Score.setSize(218,127);
        Jbtn_Score.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_Score.addActionListener(this);
        c.add(Jbtn_Score);
        
        //設定列印成績單按鈕大小位置及顯示字型
        Jbtn_printTranscript.setLocation(628,148);
        Jbtn_printTranscript.setSize(218,127);
        Jbtn_printTranscript.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_printTranscript.addActionListener(this);
        c.add(Jbtn_printTranscript);
        
        //設定修改密碼按鈕大小位置及顯示字型
        Jbtn_ChangePW.setLocation(910,148);
        Jbtn_ChangePW.setSize(218,127);
        Jbtn_ChangePW.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_ChangePW.addActionListener(this);
        c.add(Jbtn_ChangePW);
       
        //設定視窗
        setSize(1200, 800);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_Course) {
        	new searchCourseFrame();
        }
        else if(e.getSource() == Jbtn_Score){
        	new searchScoreFrame(user);
        }
        else if(e.getSource() == Jbtn_printTranscript) {
        	user.printScore(user);
        }
        else if(e.getSource() == Jbtn_ChangePW) {
			new changePasswordFrame(user);
        }
        else if(e.getSource() == Jbtn_logout) {
        	this.dispose();
        	new loginFrame();
        }
    }
}