import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Member.Administrator;

public class SetChangeUserIdFrame implements ActionListener {
	Administrator user;
	JFrame f;
	//宣告輸入欄
    JTextField userId = new JTextField(9);

	public SetChangeUserIdFrame(Administrator user) {
		this.user = user;
		//設定框架
		f = new JFrame("修改帳號");
		f.setSize(450, 250);
		f.setLocationRelativeTo(null);//視窗置中
		f.setResizable(false);//視窗放大按鈕無效
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//建立標籤
		//標籤 修改帳號
		JLabel lb = new JLabel("修改帳號：");
		lb.setLocation(32,31);
		lb.setSize(161,78);
		lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		cp.add(lb);
        
        //建立輸入欄
        //輸入欄 userId
        userId.setLocation(191,53);
        userId.setSize(220,47);
        userId.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		cp.add(userId);
		
		//建立按鈕
		JButton check = new JButton("確認");
		JButton reset = new JButton("重置");
		//按鈕 確認
		check.setLocation(74,131);
		check.setSize(123,47);
		check.setFont(new Font("微軟正黑體",Font.BOLD,28));
		check.addActionListener(this);
        cp.add(check);
        //按鈕 重置
        reset.setLocation(230,131);
        reset.setSize(123,47);
        reset.setFont(new Font("微軟正黑體",Font.BOLD,28));
        reset.addActionListener(this);
        cp.add(reset);
		
		//啟動
		f.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("確認")) {
			String ID = userId.getText();
			if(ID.equals("")) {
				JOptionPane.showMessageDialog(null, "請輸入要修改的帳號");
			}
			else {
				String userInformation = user.getUserInformation(ID);
				new ChangeUserInformationFrame(user, userInformation);
				f.dispose();
			}
		}
		if(cmd.equals("重置")) {
			userId.setText("");
		}
		
	}
}