import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class Frame_course extends JFrame implements ActionListener {
	private JLabel Jlb_semester = new JLabel("學期：");
	private JComboBox jcb_time = new JComboBox();
    
    public Frame_course()
    {
        super("高燕大課程平台 開課查詢");
        Container c = getContentPane();
        c.setLayout(null);
                
        //設定Jlb_semester大小位置及顯示字型
        Jlb_semester.setLocation(468,32);
        Jlb_semester.setSize(94,46);
        Jlb_semester.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_semester);
        
        //設定下拉式選單大小位置及顯示字型
        jcb_time.setLocation(559, 41);
        jcb_time.setSize(130, 36);
        jcb_time.setFont(new Font("微軟正黑體",Font.BOLD,22));
        jcb_time.addActionListener(this);
        c.add(jcb_time);
        
        //設定視窗
        setSize(1200, 800);
        setLocationRelativeTo(null);//視窗置中
        //setLocation(300,200);
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jcb_time) {
			
        }
    }
}
