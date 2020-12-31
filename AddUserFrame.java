import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Member.Administrator;

public class AddUserFrame implements ActionListener {
	Administrator user;
	JFrame f;
	//宣告輸入欄
    JTextField newId = new JTextField(9);
    JTextField newPw = new JTextField(25);
    JTextField newName = new JTextField(20);
    JComboBox<String> newType;
    
	public AddUserFrame(Administrator user) {
		this.user = user;
		//設定框架
		f = new JFrame("新增用戶");
		f.setSize(900, 600);
		f.setLocationRelativeTo(null);//視窗置中
		f.setResizable(false);//視窗放大按鈕無效
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//建立標籤
		//標籤 帳號
		JLabel lb = new JLabel("帳號：");
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
        lb = new JLabel("使用者名稱：");
        lb.setLocation(196,225);
        lb.setSize(185,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD,30));
        cp.add(lb);
        //標籤 類型
        lb = new JLabel("類型：");
		lb.setLocation(287,285);
        lb.setSize(120,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        cp.add(lb);
        
        //建立輸入欄
        //輸入欄 newId
        newId.setLocation(380,114);
		newId.setSize(220,35);
		newId.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		cp.add(newId);
		//輸入欄 newPw 
		newPw.setLocation(380,174);
		newPw.setSize(220,35);
		newPw.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		cp.add(newPw);
		//輸入欄 newName
		newName.setLocation(380,234);
		newName.setSize(220,35);
		newName.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		cp.add(newName);
		//選擇欄 newType
		newType = new JComboBox<String>();
		newType.addItem("請選擇");
		newType.addItem("學生");
		newType.addItem("教授");
		newType.addItem("管理員");
		newType.setLocation(380,294);
		newType.setSize(220,35);
		newType.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		cp.add(newType);
		
		//建立按鈕
		JButton add = new JButton("新增");
		JButton reset = new JButton("重置");
		//按鈕 新增
		add.setLocation(297,356);
		add.setSize(120,47);
		add.setFont(new Font("微軟正黑體",Font.BOLD,28));
		add.addActionListener(this);
        cp.add(add);
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
		if(cmd.equals("新增")) {
			String id = newId.getText();
			String pw = newPw.getText();
			String name = newName.getText();
			String type = (String) newType.getSelectedItem();
			if(id.equals("") || pw.equals("") || name.equals("") || type.equals("")){
				JOptionPane.showMessageDialog(null, "欄位不可為空");
			}
			else if(id.length() == 9 || id.length() == 5 || id.length() == 4) {
				user.addUser(id, pw, name, type);
				newId.setText("");
				newPw.setText("");
				newName.setText("");
				newType.setSelectedIndex(0);
			}
		}
		if(cmd.equals("重置")) {
			newId.setText("");
			newPw.setText("");
			newName.setText("");
			newType.setSelectedIndex(0);
		}
		
	}
}