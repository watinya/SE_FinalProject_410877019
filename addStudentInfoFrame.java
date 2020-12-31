import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Member.Administrator;

public class addStudentInfoFrame implements ActionListener {
	Administrator user;
	JFrame f;
	//宣告輸入欄
    JTextField Id = new JTextField(9);
    JTextField Name = new JTextField(20);
    JTextField InYear = new JTextField(10);
    
	public addStudentInfoFrame(Administrator user) {
		this.user = user;
		//設定框架
		f = new JFrame("新增學生資訊");
		f.setSize(900, 600);
		f.setLocationRelativeTo(null);//視窗置中
		f.setResizable(false);//視窗放大按鈕無效
		Container cp = f.getContentPane();
		cp.setLayout(null);
		
		//建立標籤
		//標籤 學號
		JLabel lb = new JLabel("學號：");
		lb.setLocation(287,140);
		lb.setSize(120,47);
		lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		cp.add(lb);
		//標籤 姓名
		lb = new JLabel("姓名：");
		lb.setLocation(287,200);
        lb.setSize(120,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        cp.add(lb);
        //標籤 入學年分
        lb = new JLabel("入學年分：");
        lb.setLocation(228,260);
        lb.setSize(156,47);
        lb.setFont(new Font("微軟正黑體", Font.BOLD,30));
        cp.add(lb);
        
        //建立輸入欄
        //輸入欄 Id
        Id.setLocation(380,149);
		Id.setSize(220,35);
		Id.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		cp.add(Id);
		//輸入欄 Name 
		Name.setLocation(380,209);
		Name.setSize(220,35);
		Name.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		cp.add(Name);
		//輸入欄 InYear
		InYear.setLocation(380,269);
		InYear.setSize(220,35);
		InYear.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		cp.add(InYear);
		
		//建立按鈕
		JButton add = new JButton("新增");
		JButton reset = new JButton("重置");
		//按鈕 新增
		add.setLocation(297,330);
		add.setSize(120,47);
		add.setFont(new Font("微軟正黑體",Font.BOLD,28));
		add.addActionListener(this);
        cp.add(add);
        //按鈕 重置
        reset.setLocation(443,330);
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
		String id = Id.getText();
		String name = Name.getText();
		String inYear = InYear.getText();
		if(cmd.equals("新增")) {
			if(id.equals("") || name.equals("") || inYear.equals("")){
				JOptionPane.showMessageDialog(null, "欄位不可為空");
			}
			else if(id.length() != 9){
				JOptionPane.showMessageDialog(null, "學號須為九位數");
			}
			else {
				user.addStudentInfo(id, name, inYear);
				Id.setText("");
				Name.setText("");
				InYear.setText("");
			}
		}
		if(cmd.equals("重置")) {
			Id.setText("");
			Name.setText("");
			InYear.setText("");
		}
		
	}
}