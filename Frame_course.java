import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class Frame_course extends JFrame implements ActionListener {
	private JLabel Jlb_semester = new JLabel("�Ǵ��G");
	private JComboBox jcb_time = new JComboBox();
    
    public Frame_course()
    {
        super("���P�j�ҵ{���x �}�Ҭd��");
        Container c = getContentPane();
        c.setLayout(null);
                
        //�]�wJlb_semester�j�p��m����ܦr��
        Jlb_semester.setLocation(468,32);
        Jlb_semester.setSize(94,46);
        Jlb_semester.setFont(new Font("�L�n������", Font.BOLD, 30));
        c.add(Jlb_semester);
        
        //�]�w�U�Ԧ����j�p��m����ܦr��
        jcb_time.setLocation(559, 41);
        jcb_time.setSize(130, 36);
        jcb_time.setFont(new Font("�L�n������",Font.BOLD,22));
        jcb_time.addActionListener(this);
        c.add(jcb_time);
        
        //�]�w����
        setSize(1200, 800);
        setLocationRelativeTo(null);//�����m��
        //setLocation(300,200);
        setResizable(false);//������j���s�L��
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jcb_time) {
			
        }
    }
}
