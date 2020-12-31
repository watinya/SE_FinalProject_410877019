import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Member.Administrator;

public class changeStudentInfoFrame implements ActionListener {
	Administrator user;
	String userInformation;
	JFrame f;
	//宣告輸入欄
    JTextField Id = new JTextField(9);
    JTextField Name = new JTextField(30);
    JTextField InYear = new JTextField(10);
    JComboBox<String> newType;
    
	public changeStudentInfoFrame(Administrator user, String userInformation) {
		this.user = user;
		this.userInformation = userInformation;
		//設定框架
		f = new JFrame("修改學生資訊");
		f.setSize(900, 600);
		f.setLocationRelativeTo(null);//視窗置中
		f.setResizable(false);//視窗放大按鈕無效
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//建立標籤
		//標籤 學號
		JLabel lb = new JLabel("學號： ");
		lb.setLocation(287,137);
		lb.setSize(120,47);
		lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		cp.add(lb);
		//標籤 姓名
		lb = new JLabel("姓名：");
		lb.setLocation(287,197);
        lb.setSize(120,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        cp.add(lb);
        //標籤 入學年分
        lb = new JLabel("入學年分： ");
        lb.setLocation(227,257);
        lb.setSize(180,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD,30));
        cp.add(lb);
        
        //建立輸入欄
        //輸入欄 Id
        Id.setLocation(380,146);
        Id.setSize(220,35);
        Id.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        Id.setText(userInformation.split(" ")[0]);
        Id.setEditable(false);
        Id.setToolTipText("學號不可更改");
		cp.add(Id);
		//輸入欄 Name 
		Name.setLocation(380,206);
		Name.setSize(220,35);
		Name.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		Name.setText(userInformation.split(" ")[1]);
		cp.add(Name);
		//輸入欄 newName
		InYear.setLocation(380,266);
		InYear.setSize(220,35);
		InYear.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		InYear.setText(userInformation.split(" ")[2]);
		cp.add(InYear);
		
		//建立按鈕
		JButton change = new JButton("變更");
		JButton reset = new JButton("重置");
		//按鈕 變更
		change.setLocation(297,335);
		change.setSize(120,47);
		change.setFont(new Font("微軟正黑體",Font.BOLD,28));
		change.addActionListener(this);
        cp.add(change);
        //按鈕 重置
        reset.setLocation(443,335);
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
			user.changeStudentInformation(Id.getText(), Name.getText(), InYear.getText());
			f.dispose();
		}
		if(cmd.equals("重置")) {
			new changeStudentInfoFrame(user, userInformation);
			f.dispose();
		}
		
	}
}