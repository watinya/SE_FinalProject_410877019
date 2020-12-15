import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Frame_changePassword extends JFrame implements ActionListener {
	private JButton Jbtn_Confirm = new JButton("�T�{���");
	private JButton Jbtn_Cancel = new JButton("����");
	private JLabel Jlb_oldPW = new JLabel("�±K�X�G");
    private JLabel Jlb_newPW = new JLabel("�s�K�X�G");
    private JLabel Jlb_confirmNewPW = new JLabel("�T�{�s�K�X�G");
    private JPasswordField joldPw = new JPasswordField(20);
    private JPasswordField jnewPW = new JPasswordField(20);
    private JPasswordField jcheckedPW = new JPasswordField(20);
    Account userAccount = Frame_login.userAccount;
    
    public Frame_changePassword()
    {
        super("���P�j�ҵ{���x ���K�X");
        Container c = getContentPane();
        c.setLayout(null);
        
        //�]�wJlb_ID�j�p��m����ܦr��
        Jlb_oldPW.setLocation(313,105);
        Jlb_oldPW.setSize(120,47);
        Jlb_oldPW.setFont(new Font("�L�n������", Font.BOLD, 30));
        c.add(Jlb_oldPW);
        
        //�]�w�±K�X��J�ؤj�p��m����ܦr��
        joldPw.setLocation(436,116);
        joldPw.setSize(220,40);
        joldPw.setFont(new Font("�L�n������", Font.BOLD,16));
        joldPw.setEchoChar('��');
        //jpw.setToolTipText("�K�X����8�Ӧr��");
        c.add(joldPw);
        
        //�]�wJlb_newPW�j�p��m����ܦr��
        Jlb_newPW.setLocation(313,165);
        Jlb_newPW.setSize(120,47);
        Jlb_newPW.setFont(new Font("�L�n������", Font.BOLD, 30));
        c.add(Jlb_newPW);
        
        //�]�w�s�K�X��J�ؤj�p��m����ܦr��
        jnewPW.setLocation(436,169);
        jnewPW.setSize(220,40);
        jnewPW.setFont(new Font("�L�n������", Font.BOLD,16));
        jnewPW.setEchoChar('��');
        //jpw.setToolTipText("�K�X����8�Ӧr��");
        c.add(jnewPW);
        
        
        //�]�wJlb_confirmNewPW�j�p��m����ܦr��
        Jlb_confirmNewPW.setLocation(254,225);
        Jlb_confirmNewPW.setSize(185,47);
        Jlb_confirmNewPW.setFont(new Font("�L�n������", Font.BOLD, 30));
        c.add(Jlb_confirmNewPW);
        
        
        //�]�w�T�{�s�K�X��J�ؤj�p��m����ܦr��
        jcheckedPW.setLocation(436,229);
        jcheckedPW.setSize(220,40);
        jcheckedPW.setFont(new Font("�L�n������", Font.BOLD,16));
        jcheckedPW.setEchoChar('��');
        //jpw.setToolTipText("�K�X����8�Ӧr��");
        c.add(jcheckedPW);
        
        //�]�w�T�{�����s�j�p��m����ܦr��
        Jbtn_Confirm.setLocation(354,290);
        Jbtn_Confirm.setSize(137,58);
        Jbtn_Confirm.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_Confirm.addActionListener(this);
        c.add(Jbtn_Confirm);
        
        //�]�w�T�{�����s�j�p��m����ܦr��
        Jbtn_Cancel.setLocation(505,290);
        Jbtn_Cancel.setSize(137,58);
        Jbtn_Cancel.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_Cancel.addActionListener(this);
        c.add(Jbtn_Cancel);
        
        //�]�w����
        setSize(1000, 600);
        setLocationRelativeTo(null);//�����m��
        setResizable(false);//������j���s�L��
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_Confirm) {
        	String oldPw = new String(joldPw.getPassword());
        	String newPw = new String(jnewPW.getPassword());
			String checkedPw = new String(jcheckedPW.getPassword());
			if (oldPw.equals(userAccount.password) && newPw.equals(checkedPw)) {
				try {
					Account.changePassword(userAccount.character, userAccount.account, oldPw, newPw);
					userAccount.password = newPw;
				} catch (IOException e1) {e1.printStackTrace();}
				JOptionPane.showMessageDialog(new JFrame(), "�K�X�ܧ󦨥\", "�ܧ�K�X", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "�K�X���šA�Э��s��J", "�ܧ�K�X", JOptionPane.ERROR_MESSAGE);
				joldPw.setText("");
				jnewPW.setText("");
				jcheckedPW.setText("");
			}
        }
        else if(e.getSource() == Jbtn_Cancel){
        	this.dispose();
        }
    }
}
