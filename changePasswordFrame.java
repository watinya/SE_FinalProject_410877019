import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Member.Member;

@SuppressWarnings("serial")
public class changePasswordFrame extends JFrame implements ActionListener {
	private JButton Jbtn_Confirm = new JButton("確認更改");
	private JButton Jbtn_Cancel = new JButton("取消");
	private JLabel Jlb_oldPW = new JLabel("舊密碼：");
    private JLabel Jlb_newPW = new JLabel("新密碼：");
    private JLabel Jlb_confirmNewPW = new JLabel("確認新密碼：");
    private JPasswordField joldPw = new JPasswordField(20);
    private JPasswordField jnewPW = new JPasswordField(20);
    private JPasswordField jcheckedPW = new JPasswordField(20);
    private Member user;
    
    public changePasswordFrame(Member user)
    {
        super("高燕大課程平台 更改密碼");
        this.user = user;
        Container c = getContentPane();
        c.setLayout(null);
        
        //設定Jlb_ID大小位置及顯示字型
        Jlb_oldPW.setLocation(273,105);
        Jlb_oldPW.setSize(123,47);
        Jlb_oldPW.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_oldPW);
        
        //設定舊密碼輸入框大小位置及顯示字型
        joldPw.setLocation(396,116);
        joldPw.setSize(220,40);
        joldPw.setFont(new Font("微軟正黑體", Font.BOLD,16));
        joldPw.setEchoChar('●');
        c.add(joldPw);
        
        //設定Jlb_newPW大小位置及顯示字型
        Jlb_newPW.setLocation(273,165);
        Jlb_newPW.setSize(123,47);
        Jlb_newPW.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_newPW);
        
        //設定新密碼輸入框大小位置及顯示字型
        jnewPW.setLocation(396,169);
        jnewPW.setSize(220,40);
        jnewPW.setFont(new Font("微軟正黑體", Font.BOLD,16));
        jnewPW.setEchoChar('●');
        c.add(jnewPW);
        
        //設定Jlb_confirmNewPW大小位置及顯示字型
        Jlb_confirmNewPW.setLocation(214,225);
        Jlb_confirmNewPW.setSize(185,47);
        Jlb_confirmNewPW.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_confirmNewPW);
        
        //設定確認新密碼輸入框大小位置及顯示字型
        jcheckedPW.setLocation(396,229);
        jcheckedPW.setSize(220,40);
        jcheckedPW.setFont(new Font("微軟正黑體", Font.BOLD,16));
        jcheckedPW.setEchoChar('●');
        c.add(jcheckedPW);
        
        //設定確認更改按鈕大小位置及顯示字型
        Jbtn_Confirm.setLocation(314,290);
        Jbtn_Confirm.setSize(125,55);
        Jbtn_Confirm.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_Confirm.addActionListener(this);
        c.add(Jbtn_Confirm);
        
        //設定取消更改按鈕大小位置及顯示字型
        Jbtn_Cancel.setLocation(465,290);
        Jbtn_Cancel.setSize(125,55);
        Jbtn_Cancel.setFont(new Font("微軟正黑體",Font.BOLD,22));
        Jbtn_Cancel.addActionListener(this);
        c.add(Jbtn_Cancel);
        
        //設定視窗
        setSize(900, 600);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_Confirm) {
        	String oldPw = new String(joldPw.getPassword());
        	String newPw = new String(jnewPW.getPassword());
            String checkedPw = new String(jcheckedPW.getPassword());
            if(oldPw.equals("") || newPw.equals("") || checkedPw.equals("")){
                JOptionPane.showMessageDialog(new JFrame(), "欄位不可為空", "變更密碼", JOptionPane.ERROR_MESSAGE);
            }
			else if(oldPw.equals(user.password) && newPw.equals(checkedPw)) {
				try {
					Member.changePassword(user.id, oldPw, newPw);
					user.password = newPw;
				} catch (Exception e1) {e1.printStackTrace();}
				JOptionPane.showMessageDialog(new JFrame(), "密碼變更成功", "變更密碼", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "密碼不符，請重新輸入", "變更密碼", JOptionPane.ERROR_MESSAGE);
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