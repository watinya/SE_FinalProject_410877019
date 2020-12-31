import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import Member.Administrator;

@SuppressWarnings("serial")
public class generateTranscriptsFrame extends JFrame implements ActionListener{
    private JLabel Jlb_time = new JLabel("學期：");
    private JLabel Jlb_id = new JLabel("學號：");
    private JTextField jid = new JTextField(9);
    private JComboBox<String> jcb_time = new JComboBox<String>();
    private JButton Jbtn_Confirm = new JButton("產生成績單");
    
    private Administrator user;
    
    public generateTranscriptsFrame(Administrator user) {
        super("產生成績單");
        this.user = user;
        Container c = getContentPane();
        c.setLayout(null);
        
        //設定學期標籤大小位置及顯示字型
        Jlb_time.setLocation(67,84);
        Jlb_time.setSize(100,47);
        Jlb_time.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_time);
       
        //設定帳號輸入框大小位置及顯示字型
        jid.setLocation(167,39);
        jid.setSize(220,40);
        jid.setFont(new Font("微軟正黑體", Font.BOLD,24));
        c.add(jid);
       
        //設定學號標籤大小位置及顯示字型
        Jlb_id.setLocation(67,32);
        Jlb_id.setSize(100,47);
        Jlb_id.setFont(new Font("微軟正黑體", Font.BOLD, 30));
        c.add(Jlb_id);
       
        //設定下拉式選單大小位置及顯示字型
        File file = new File("data\\course");
        String[] directories = file.list(new FilenameFilter() {
        @Override
        public boolean accept(File current, String name) {
          return new File(current, name).isDirectory();
        }
        });
        jcb_time.addItem("請選擇");
      	for(int i = directories.length - 1; i >= 0 ; i--) {
    	  	jcb_time.addItem(directories[i]);
    	  }
        jcb_time.setLocation(167, 93);
        jcb_time.setSize(130, 36);
        jcb_time.setFont(new Font("微軟正黑體",Font.BOLD,22));
        c.add(jcb_time);
        
        //設定產生成績單按鈕大小位置及顯示字型
        Jbtn_Confirm.setLocation(152,144);
        Jbtn_Confirm.setSize(148,47);
        Jbtn_Confirm.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        Jbtn_Confirm.addActionListener(this);
        c.add(Jbtn_Confirm);
       
        //設定視窗
        setSize(450, 250);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	  if(e.getSource() == Jbtn_Confirm) {
            String selectedSemester = (String) jcb_time.getSelectedItem();
            String studentID = jid.getText();
            user.OutputStudentCourse(selectedSemester, studentID);
            
            JOptionPane.showMessageDialog(null, "檔案已儲存至C槽");
            jcb_time.setSelectedIndex(0);
            jid.setText("");
    	  }
    }   
}