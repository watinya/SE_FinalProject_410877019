import java.awt.*;
import javax.swing.*;

import Member.Student;
import Member.Teacher;
import Member.Administrator;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class loginFrame extends JFrame implements ActionListener{
    private JLabel Jlb_title = new JLabel("高燕大課程平台");
	private JLabel Jlb_ID = new JLabel("帳號：");
    private JLabel Jlb_PW = new JLabel("密碼：");
    private JPasswordField jpw = new JPasswordField(20);
    private JTextField jid = new JTextField(20);
    private JButton Jbtn_Login = new JButton("登入");
    private String name;
    
    public loginFrame()
    {
        super("高燕大課程平台 登入");
        Container c = getContentPane();
        c.setLayout(null);

        //設定Jlb_title大小位置及顯示字型
        Jlb_title.setLocation(406,13);
        Jlb_title.setSize(395,106);
        Jlb_title.setFont(new Font("微軟正黑體", Font.BOLD, 54));
        c.add(Jlb_title);
       
        //設定Jlb_ID大小位置及顯示字型
        Jlb_ID.setLocation(433,125);
        Jlb_ID.setSize(100,47);
        Jlb_ID.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_ID);
       
        //設定帳號輸入框大小位置及顯示字型
        jid.setLocation(532,130);
        jid.setSize(220,40);
        jid.setFont(new Font("微軟正黑體", Font.BOLD,24));
        c.add(jid);
       
        //設定Jlb_PW大小位置及顯示字型
        Jlb_PW.setLocation(433,176);
        Jlb_PW.setSize(100,47);
        Jlb_PW.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_PW);
       
        //設定密碼輸入框大小位置及顯示字型
        jpw.setLocation(532,180);
        jpw.setSize(220,40);
        jpw.setFont(new Font("微軟正黑體", Font.BOLD,16));
        jpw.setEchoChar('●');
        c.add(jpw);
        
        //設定登入按鈕大小位置及顯示字型
        Jbtn_Login.setLocation(513,236);
        Jbtn_Login.setSize(142,47);
        Jbtn_Login.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        Jbtn_Login.addActionListener(this);
        c.add(Jbtn_Login);
       
        //設定視窗
        setSize(1200, 800);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
    boolean checkData(String file) {
    	String ID = jid.getText();
		char[] password = jpw.getPassword();
        String ps = new String(password);
        String line, user, pass;
    	boolean isLoginSuccess = false;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				user = line.split(" ")[0];
				pass = line.split(" ")[1];
				if (user.equals(ID) && pass.equals(ps)) {
                    isLoginSuccess = true;
                    this.name = line.split(" ")[2];
                    break;
				}
			}
			if(!isLoginSuccess) {
	        	JOptionPane.showMessageDialog(null, "帳號或密碼錯誤");
	        	jid.setText("");
				jpw.setText("");
            }
            br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isLoginSuccess;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_Login) {
	        switch(jid.getText().length()) {
				case 9:
					if(checkData("data\\account\\studentAccount.txt")) {
						this.dispose();
                        Student user = new Student(jid.getText(), jpw.getPassword(), name);
						new studentFunctionFrame(user);
					}
					break;
				case 5:
					if(checkData("data\\account\\teacherAccount.txt")) {
						this.dispose();
						Teacher user = new Teacher(jid.getText(), jpw.getPassword(), name);
						new teacherFunctionFrame(user);
					}
					break;
				case 4:
					if(checkData("data\\account\\administratorAccount.txt")) {
                        this.dispose();
                        Administrator user = new Administrator(jid.getText(), jpw.getPassword(), name);
						new administratorFunctionFrame(user);
					}
					break;
				default:
					JOptionPane.showMessageDialog(null, "帳號或密碼錯誤");
					jid.setText("");
					jpw.setText("");
					break;
			}	
        }
    }

}
