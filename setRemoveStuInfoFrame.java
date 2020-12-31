import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Member.Administrator;

public class setRemoveStuInfoFrame implements ActionListener {
	Administrator user;
	JFrame f;
	//宣告輸入欄
    JTextField userId = new JTextField(9);

	public setRemoveStuInfoFrame(Administrator user) {
		this.user = user;
		//設定框架
		f = new JFrame("刪除學生資訊");
		f.setSize(450, 250);
		f.setLocationRelativeTo(null);//視窗置中
		f.setResizable(false);//視窗放大按鈕無效
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//建立標籤
		//標籤 學號
		JLabel lb = new JLabel("學號：");
		lb.setLocation(60,50);
		lb.setSize(105,47);
		lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		cp.add(lb);
        
        //建立輸入欄
        //輸入欄 userId
        userId.setLocation(159, 53);
        userId.setSize(220, 47);
        userId.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		cp.add(userId);
		
		//建立按鈕
		JButton remove = new JButton("刪除");
		JButton reset = new JButton("重置");
		//按鈕 刪除
		remove.setLocation(74,131);
		remove.setSize(123,47);
		remove.setFont(new Font("微軟正黑體",Font.BOLD,28));
		remove.addActionListener(this);
        cp.add(remove);
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
		if(cmd.equals("刪除")) {
			String ID = userId.getText();
			if(ID.equals("")) {
				JOptionPane.showMessageDialog(null, "請輸入要刪除的學號");
			}else {
				if(user.checkStudentExist(ID)){
					user.removeStudent(ID);
					f.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "學生不存在");
				}
			}
		}
		if(cmd.equals("重置")) {
			userId.setText("");
		}
	}
}