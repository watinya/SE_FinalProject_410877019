package Member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Teacher extends Member{
	//所有指導課程
	public ArrayList<Subject> subjects = new ArrayList<Subject>();
	//建立帳號基本屬性
	public Teacher(String id, char[] password, String name){
		//紀錄當前使用者:帳號,密碼,使用者名稱
		super(id,new String(password),name);
		//建立指導課程
		try {
		createCourse("data\\teachers\\"+ id +"\\指導課程.txt");	
		}catch(IOException e) {
			System.out.println("建立指導課程 Error");
		}	
	}
	//建立指導課程
	private void createCourse(String dataLoaction) throws IOException {
			File f = new File(dataLoaction);
			InputStreamReader reade = new InputStreamReader(new FileInputStream(f),"utf-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			while((line = reader.readLine()) != null) {
				subjects.add(new Subject(line.split(" ")[0],line.split(" ")[1]));
			}
			reader.close();
	}
		
	//列出課程學生成績
	public Object[][] getCourseStudentScore(String year, String subjectName)  {
		//建檔案路徑
		String dataLocation = "data\\course\\" + year + "\\" + subjectName + ".txt";
		try {
			ArrayList<String> temp = getCourseStudentScore(dataLocation);
			Object[][] data = new Object[temp.size()][];
			for(int i = 0; i < data.length; i++){
				data[i] = temp.get(i).split(" ");
			}
			return data;
		}catch(FileNotFoundException e) {}
		catch(IOException e) {
			System.err.println(e);
		}
		return null;
	}
	private ArrayList<String> getCourseStudentScore(String dataLocation) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		String line;
		//跳過第一行
		reader.readLine();
		ArrayList<String> temp = new ArrayList<String>();
		//抓出每個學生學號與名字
		while((line = reader.readLine()) != null) {
			temp.add(line);
		}
		reader.close();
		return temp;
	}

	//寫入成績
	public boolean writeScore(String semester, String course, Object[][] data){
		String dataLocation = "data\\course\\" + semester + "\\" + course + ".txt";
		try{
			writeScore(dataLocation, data);
		}catch(IOException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
    private void writeScore(String dataLocation, Object[][] data) throws IOException {
		FileInputStream fr = new FileInputStream(dataLocation);
		BufferedReader br = new BufferedReader(new InputStreamReader(fr, "utf8"));
		String title = br.readLine();
		String writeText = title + "\n";
		int count = 0;
		while (br.ready()) {
			String temp = br.readLine();
			String[] info = temp.split(" ");
			String id = (String) data[count][0];
			String score = (String) data[count++][1];

			if (!info[0].equals(id) || !info[2].equals(score)) {
				temp = "";
				info[2] = score;
				for(int i = 0; i < info.length; i++) 
					temp += info[i] + " ";
			}
			writeText += temp + "\n";
		}
		br.close();
		FileOutputStream writerStream = new FileOutputStream(dataLocation);    
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 
		writer.write(writeText);
		writer.close(); 
    }
}//end class
