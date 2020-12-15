import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Account {
	String account;
	String name;
	String password;
	char character;// 學生:'s' 教師:'t' 管理員:'m'

	Account(String account, String password, String name, char character) {
		this.account = account;
		this.name = name;
		this.password = password;
		this.character = character;
	}
	
	//登入method
	static Account login(String account, String password) throws IOException {
		FileReader fr;
		try {
			switch (account.length()) {
			case 9:// 學生
				fr = new FileReader("data/studentAccount.txt");
				BufferedReader br = new BufferedReader(fr);
				while (br.ready()) {
					String[] info = br.readLine().split(" ");
					if (info[0].equals(account) && info[1].equals(password)) {
						Account student = new Account(info[0], info[1], info[2], 's');
						return student;
					}
				}
				fr.close();
				break;
			case 5:// 教師
				fr = new FileReader("data/teacherAccount.txt");
				br = new BufferedReader(fr);
				while (br.ready()) {
					String[] info = br.readLine().split(" ");
					if (info[0].equals(account) && info[1].equals(password)) {
						Account teacher = new Account(info[0], info[1], info[2], 't');
						return teacher;
					}
				}
				fr.close();
				break;
			default:
				fr = new FileReader("data/manageAccount.txt");
				br = new BufferedReader(fr);
				while (br.ready()) {
					String[] info = br.readLine().split(" ");
					if (info[0].equals(account) && info[1].equals(password)) {
						Account manager = new Account(info[0], info[1], info[2], 'm');
						return manager;
					}
				}
				fr.close();
				break;
			}
		} catch (FileNotFoundException e1) {
		}
		Account error = new Account(null, null, null,'e');
		return error;
	}

	//修改密碼method
    static void changePassword(char character, String account, String oldPassword, String newPassword) throws IOException {
		String file;
		switch (character) {
			case 's':
				file = "data/studentAccount.txt";
				break;
			case 't':
				file = "data/teacherAccount.txt";
				break;
			default:
				file = "data/manageAccount.txt";
				break;
		}
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
	}
	
}
