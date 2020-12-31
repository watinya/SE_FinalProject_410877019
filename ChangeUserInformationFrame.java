import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Member.Administrator;

public class ChangeUserInformationFrame implements ActionListener {
	Administrator user;
	String userInformation;
	JFrame f;
	//宣告輸入欄
    JTextField newId = new JTextField(9);
    JTextField newPw = new JTextField(20);
    JTextField newName = new JTextField(20);
    JTextField type = new JTextField();
    
	public ChangeUserInformationFrame(Administrator user, String userInformation) {
		this.user = user;
		this.userInformation = userInformation;
		//設定框架
		f = new JFrame("修改用戶資訊");
		f.setSize(900, 600);
		f.setLocationRelativeTo(null);//視窗置中
		f.setResizable(false);//視窗放大按鈕無效
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//建立標籤
		//標籤 帳號
		JLabel lb = new JLabel("帳號： ");
		lb.setLocation(287,105);
		lb.setSize(120,47);
		lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		cp.add(lb);
		//標籤 密碼
		lb = new JLabel("密碼：");
		lb.setLocation(287,165);
        lb.setSize(120,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        cp.add(lb);
        //標籤 使用者名稱
        lb = new JLabel("使用者名稱： ");
        lb.setLocation(198,225);
        lb.setSize(200,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD,30));
        cp.add(lb);
        //標籤 類型
        lb = new JLabel("類型： ");
		lb.setLocation(287,285);
        lb.setSize(120,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        cp.add(lb);
        
        //建立輸入欄
        //輸入欄 newId
        newId.setLocation(380,114);
		newId.setSize(220,35);
		newId.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		newId.setText(userInformation.split(" ")[0]);
		cp.add(newId);
		//輸入欄 newPw 
		newPw.setLocation(380,174);
		newPw.setSize(220,35);
		newPw.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		newPw.setText(userInformation.split(" ")[1]);
		cp.add(newPw);
		//輸入欄 newName
		newName.setLocation(380,234);
		newName.setSize(220,35);
		newName.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		newName.setText(userInformation.split(" ")[2]);
		cp.add(newName);
		//輸入欄 newType
		String id = userInformation.split(" ")[0];
		switch(id.length()) {
		case 9:
			type.setText("學生");
			break;
		case 5:
			type.setText("教授");
			break;
		case 4:
			type.setText("管理員");
			break;
		}
		type.setLocation(380, 294);
		type.setSize(220, 35);
		type.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		type.setEditable(false);
		type.setToolTipText("類型不可更改");
		cp.add(type);
		
		//建立按鈕
		JButton change = new JButton("變更");
		JButton reset = new JButton("重置");
		//按鈕 變更
		change.setLocation(297,356);
		change.setSize(120,47);
		change.setFont(new Font("微軟正黑體",Font.BOLD,28));
		change.addActionListener(this);
        cp.add(change);
        //按鈕 重置
        reset.setLocation(443,356);
        reset.setSize(120,47);
        reset.setFont(new Font("微軟正黑體",Font.BOLD,28));
        reset.addActionListener(this);
        cp.add(reset);
		
		//啟動
		f.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("變更")) {
			if(user.changeUserInformation(userInformation.split(" ")[0], newId.getText(), newPw.getText(), newName.getText(), type.getText()))
				f.dispose();
		}
		if(cmd.equals("重置")) {
			new ChangeUserInformationFrame(user, userInformation);
			f.dispose();
		}
		
	}
}