import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Frame_login extends JFrame implements ActionListener{
	private JLabel Jlb_ID = new JLabel("�b���G");
    private JLabel Jlb_PW = new JLabel("�K�X�G");
    private JPasswordField jpw = new JPasswordField(20);
    private JTextField jid = new JTextField(20);
    private JButton Jbtn_Login = new JButton("�n�J");
	public static Account userAccount;
    
    public Frame_login() {
        super("���P�j�ҵ{���x �n�J");
        Container c = getContentPane();
        c.setLayout(null);
       
        //�]�wJlb_ID�j�p��m����ܦr��
        Jlb_ID.setLocation(433,125);
        Jlb_ID.setSize(100,47);
        Jlb_ID.setFont(new Font("�L�n������", Font.BOLD, 30));
        c.add(Jlb_ID);
       
        //�]�w�b����J�ؤj�p��m����ܦr��
        jid.setLocation(532,130);
        jid.setSize(220,40);
        jid.setFont(new Font("�L�n������", Font.BOLD,24));
        c.add(jid);
       
        //�]�wJlb_PW�j�p��m����ܦr��
        Jlb_PW.setLocation(433,176);
        Jlb_PW.setSize(100,47);
        Jlb_PW.setFont(new Font("�L�n������", Font.BOLD, 30));
        c.add(Jlb_PW);
       
        //�]�w�K�X��J�ؤj�p��m����ܦr��
        jpw.setLocation(532,180);
        jpw.setSize(220,40);
        jpw.setFont(new Font("�L�n������", Font.BOLD,16));
        jpw.setEchoChar('��');
        //jpw.setToolTipText("�K�X����8�Ӧr��");
        c.add(jpw);
        
        //�]�w�n�J���s�j�p��m����ܦr��
        Jbtn_Login.setLocation(513,236);
        Jbtn_Login.setSize(142,47);
        Jbtn_Login.setFont(new Font("�L�n������", Font.BOLD, 22));
        Jbtn_Login.addActionListener(this);
        c.add(Jbtn_Login);
       
        //�]�w����
        setSize(1200, 800);
        setLocationRelativeTo(null);//�����m��
        //setLocation(300,200);
        setResizable(false);//������j���s�L��
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
	        	JOptionPane.showMessageDialog(null, "�b���αK�X���~");
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
					JOptionPane.showMessageDialog(null, "�b���αK�X���~");
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
					JOptionPane.showMessageDialog(null, "�b���αK�X���~");
					jid.setText("");
					jpw.setText("");
					break;
			}	
        }
    }
	*/
}
