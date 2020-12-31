package Member;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Member {
	public String id;
	public String password;
	public String name;

	public Member(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	//更改密碼method
	public static boolean changePassword(String account, String oldPassword, String newPassword){
		String file;
		//找檔案路徑
		switch (account.length()) {
		case 9:
			file = "data\\account\\studentAccount.txt";
			break;
		case 5:
			file = "data\\account\\teacherAccount.txt";
			break;
		case 4:
			file = "data\\account\\administratorAccount.txt";
			break;
		default:
			file = "";
			return false;
		}
		try {
			FileInputStream fr = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fr, "utf8"));
			String writeText="";
			while (br.ready()) {
				String temp = br.readLine();
				String[] info = temp.split(" ");
	
				if (info[0].equals(account) && info[1].equals(oldPassword)) {
					temp = "";
					info[1] = newPassword;
					for(int i = 0; i < info.length; i++) 
						temp += info[i] + " ";
				}	
				writeText += temp+"\n";
			}
			br.close();
			FileOutputStream writerStream = new FileOutputStream(file);    
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 
			writer.write(writeText);
			writer.close();
		}catch(Exception e) {
			System.err.println(e);
		}
		return true;
	}
	
	//列出課程同學
	public static boolean outputClassmate(String year, String subjectName)  {
		//建檔案路徑
		String dataLocation = "data\\course\\" + year + "\\" + subjectName + ".txt";
		try {
			outputClassmate(dataLocation);
		}catch (Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
	private static boolean outputClassmate(String dataLocation) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		String line,id,name,fileContent = "";
		//跳過第一行
		reader.readLine();
		//抓出每個學生學號與名字
		while((line = reader.readLine()) != null) {
			id = line.split(" ")[0];
			name = line.split(" ")[1];
			fileContent = fileContent.concat(id +"  "+ name +"\n");
		}
		reader.close();
		JOptionPane.showMessageDialog(null, fileContent, "選課名單", JOptionPane.PLAIN_MESSAGE);
		return true;
	}
	
	//列出學期課程清單
	public static Object[][] outputCourseList(String year) {
		String dataLocation = "data\\course\\" + year;
		try {
			Object[][] coursesData = createCourseList(year, dataLocation);
			return coursesData;
		}catch(IOException e) {
			System.err.println(e);
		}
		return null;
	}
	private static Object[][] createCourseList(String year, String dataLocation) throws IOException {
		File f = new File(dataLocation);
		File[] fileList = f.listFiles();
		Object[][] data = new Object[fileList.length][];
		//讀取每個課程第一行內容
		for(int i=0; i<fileList.length; i++) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileList[i]), "utf-8");
			BufferedReader reader = new BufferedReader(read);
			data[i] = reader.readLine().split(" ");
			reader.close();
		}
		return data;
	}
}// end class


