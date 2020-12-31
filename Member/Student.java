package Member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Student extends Member {

	private ArrayList<Subject> subjects = new ArrayList<Subject>();

	// 建立帳號基本屬性
	public Student(String id, char[] password, String name) {
		// 紀錄當前使用者:帳號,密碼,使用者名稱
		super(id, new String(password), name);
		// 建立選修課程
		try {
			createCourse("data\\students\\" + id + "\\選修課程.txt");
		} catch (IOException e) {
			System.out.println("建立選修課程 Error");
		}
	}

	// 建立選修課程
	private void createCourse(String dataLoaction) throws IOException {
		File f = new File(dataLoaction);
		InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(reade);
		String line;
		while ((line = reader.readLine()) != null) {
			subjects.add(new Subject(line.split(" ")[0], line.split(" ")[1]));
		}
		reader.close();
	}
	/*
	// 查看所有成績
	public void getScore() {
		String content = "";
		for (int i = 0; i < subjects.size(); i++) {
			content = content.concat(getScore(subjects.get(i).getYear(), subjects.get(i).getName()) + "\n");
		}
		JOptionPane.showMessageDialog(null, content, "課程成績", JOptionPane.PLAIN_MESSAGE);
	}
	*/
	//查看成績
	public String getScore(String id, String year, String subject) {
		for(Subject i : subjects) {
			if(i.getYear().equals(year)) {
				for(SubjectStudents j : i.getSubjectStudents()) {
					if(j.getId().equals(id)) {
						return j.getScore();
					}
				}
			}
		}
		return null;
	}
	// 找出每科成績
	private String getScore(String year, String subject) {
		for (int i = 0; i < subjects.size(); i++) {
			if (subjects.get(i).getYear().equals(year) && subjects.get(i).getName().equals(subject)) {
				for (int j = 0; j < subjects.get(i).getSubjectStudents().size(); j++) {
					if (subjects.get(i).getSubjectStudents().get(j).getId().equals(super.id)) {
						String content = String.format("%s %s%5s%3s", year, subject, "成績:",
								subjects.get(i).getSubjectStudents().get(j).getScore());
						return content;
						// JOptionPane.showMessageDialog(null, content, "課程成績",
						// JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		}
		return null;
	}
	
	// 列印成績單
	public boolean printScore(Member user) {
		// 取得成績
		String content = id + " " + name + "成績單 " + "\n";
		for (int i = 0; i < subjects.size(); i++) {
			content = content.concat(getScore(subjects.get(i).getYear(), subjects.get(i).getName()) + "\n");
		}
		try {
			// 寫入列印文檔
			File f = new File("data\\printFile\\temp.txt");
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("列印成績單Error");
			return false;
		}
		//txt 轉成 Pdf
		new OutputPdf("data\\printFile\\temp.txt","data\\printFile\\temp.pdf");
		// 打開列印視窗
		new PrintFile();
		return true;
	}

	// 取得成績清單
	public Object[][] getScoreList(String year) {
		//建立清單
		String[][] temp = new String[subjects.size()][2];
		int count = 0;
		for(int i=0; i<subjects.size(); i++) {
			//如果符合課程學年
			if(subjects.get(i).getYear().equals(year)) {
				//加入課程資訊
				temp[count++] = getScoreList(subjects.get(i).getYear(), subjects.get(i).getName());
			}
		}
		//去除temp陣列中空白值
		String[][] data = new String[count][2];
		for(int i = 0; i < count; i++) {
			data[i][0] = temp[i][0];
			data[i][1] = temp[i][1];
		}
		return data;
	}
	
	// 取得成績清單
	private String[] getScoreList(String year, String subject) {
		//確認每個有選修的課程
		for (int i = 0; i < subjects.size(); i++) {
			//如果 學年 與 課程名稱 符合
			if (subjects.get(i).getYear().equals(year) && subjects.get(i).getName().equals(subject)) {
				//找出 (本使用者ID)的成績 由 this.id 
				for (int j = 0; j < subjects.get(i).getSubjectStudents().size(); j++) {
					//符合 本使用者ID 紀錄 課程 與 成績
					if (subjects.get(i).getSubjectStudents().get(j).getId().equals(super.id)) {
						//回傳 如: 108-1 計算機概論 88
						//String[] content = { year, subject, subjects.get(i).getSubjectStudents().get(j).getScore() };
						//回傳 如: 計算機概論 88
						String[] content = { subject, subjects.get(i).getSubjectStudents().get(j).getScore() };
						return content;
					}
				}
			}
		}
		return null;
	}

}
