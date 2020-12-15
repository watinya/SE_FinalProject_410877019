import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Frame_login extends JFrame implements ActionListener{
	private JLabel Jlb_ID = new JLabel("帳號：");
    private JLabel Jlb_PW = new JLabel("密碼：");
    private JPasswordField jpw = new JPasswordField(20);
    private JTextField jid = new JTextField(20);
    private JButton Jbtn_Login = new JButton("登入");
	public static Account userAccount;
    
    public Frame_login() {
        super("高燕大課程平台 登入");
        Container c = getContentPane();
        c.setLayout(null);
       
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
        //jpw.setToolTipText("密碼長度8個字元");
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
        //setLocation(300,200);
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
    /*
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
				}
			}
			if(!isLoginSuccess) {
	        	JOptionPane.showMessageDialog(null, "帳號或密碼錯誤");
	        	jid.setText("");
				jpw.setText("");
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isLoginSuccess;
    }
    */
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	try {
			Account login = Account.login(jid.getText(), new String(jpw.getPassword()));
			switch (login.character) {
				case 'm':
					this.dispose();
					new Frame_administratorFunction();
					userAccount = (Account) login;
					break;
				case 't':
					this.dispose();
					new Frame_teacherFunction();
					userAccount = (Account) login;
					break;
				case 's':
					this.dispose();
					new Frame_studentFunction();
					userAccount = (Account) login;
					break;
				default:
					JOptionPane.showMessageDialog(null, "帳號或密碼錯誤");
					//jid.setText("");
					jpw.setText("");
					break;
			}
		} catch (IOException e1) {
		}
    }
    
    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_Login) {
	        switch(jid.getText().length()) {
				case 9:
					if(checkData("data\\studentAccount.txt")) {
						this.dispose();
						new studentFunctionFrame();
					}
					break;
				case 5:
					if(checkData("data\\teacherAccount.txt")) {
						this.dispose();
						new teacherFunctionFrame();
					}
					break;
				case 4:
					if(checkData("data\\administratorAccount.txt")) {
						this.dispose();
						new administratorFunctionFrame();
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
	*/
}
