import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Frame_administratorFunction extends JFrame implements ActionListener {
	private JButton Jbtn_Course = new JButton("�}�Ҭd��");
	private JButton Jbtn_accountManage = new JButton("�b��޲z");
	private JButton Jbtn_studentManage = new JButton("�ǥ͸�T�޲z");
	private JButton Jbtn_courseManage = new JButton("�ҵ{��T�޲z");
    private JButton Jbtn_electiveCourseMange = new JButton("�ǥͿ�׽ҵ{�޲z");
    private JButton Jbtn_electiveCourseScore = new JButton("��׽ҵ{���Z�޲z");
    private JButton Jbtn_generateTranscripts = new JButton("���ͦ��Z��");
    private JButton Jbtn_ChangePW = new JButton("�ק�K�X");
   
    public Frame_administratorFunction()
    {
        super("���P�j�ҵ{���x �޲z���\��");
        Container c = getContentPane();
        c.setLayout(null);
                 
        //�]�w�}�Ҭd��(�M��B���e�B�ǥͲM��)���s�j�p��m����ܦr��
        Jbtn_Course.setLocation(909,150);
        Jbtn_Course.setSize(218,127);
        Jbtn_Course.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_Course.addActionListener(this);
        c.add(Jbtn_Course);
       
        //�]�w�b���޲z(�s�W�B�R���B�ק�)���s�j�p��m����ܦr��
        Jbtn_accountManage.setLocation(63,150);
        Jbtn_accountManage.setSize(218,127);
        Jbtn_accountManage.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_accountManage.addActionListener(this);
        c.add(Jbtn_accountManage);
        
        //�]�w�ǥ͸�T(�m�W�B�Ǹ��B�J�Ǧ~��)�޲z���s�j�p��m����ܦr��
        Jbtn_studentManage.setLocation(345,150);
        Jbtn_studentManage.setSize(218,127);
        Jbtn_studentManage.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_studentManage.addActionListener(this);
        c.add(Jbtn_studentManage);
        
        //�]�w�ҵ{��T�޲z���s(�N�X�B�W�١B�Ǥ��B�б¡B����)�j�p��m����ܦr��
        Jbtn_courseManage.setLocation(627,150);
        Jbtn_courseManage.setSize(218,127);
        Jbtn_courseManage.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_courseManage.addActionListener(this);
        c.add(Jbtn_courseManage);
        
        //�]�w�ǥͿ�׽ҵ{�޲z(�s�W�B�R���B�ק�)���s�j�p��m����ܦr��
        Jbtn_electiveCourseMange.setLocation(345,329);
        Jbtn_electiveCourseMange.setSize(218,127);
        Jbtn_electiveCourseMange.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_electiveCourseMange.addActionListener(this);
        c.add(Jbtn_electiveCourseMange);
        
        //�]�w��׽ҵ{���Z�޲z(��J�B��s)���s�j�p��m����ܦr��
        Jbtn_electiveCourseScore.setLocation(63,329);
        Jbtn_electiveCourseScore.setSize(218,127);
        Jbtn_electiveCourseScore.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_electiveCourseScore.addActionListener(this);
        c.add(Jbtn_electiveCourseScore);
        
        //�]�w���ͦ��Z����s�j�p��m����ܦr��
        Jbtn_generateTranscripts.setLocation(627,329);
        Jbtn_generateTranscripts.setSize(218,127);
        Jbtn_generateTranscripts.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_generateTranscripts.addActionListener(this);
        c.add(Jbtn_generateTranscripts);
        
        //�]�w�ק�K�X���s�j�p��m����ܦr��
        Jbtn_ChangePW.setLocation(909,329);
        Jbtn_ChangePW.setSize(218,127);
        Jbtn_ChangePW.setFont(new Font("�L�n������",Font.BOLD,22));
        Jbtn_ChangePW.addActionListener(this);
        c.add(Jbtn_ChangePW);
       
        //�]�w����
        setSize(1200, 800);
        setLocationRelativeTo(null);//�����m��
        //setLocation(300,200);
        setResizable(false);//������j���s�L��
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_ChangePW) {
        	
			new Frame_changePassword();
        }
        else if(e.getSource() == Jbtn_Course) {
        	new Frame_course();
        }
    }
}