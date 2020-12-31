package Member;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Administrator extends Member {
	//建立基本屬性
	private ArrayList<Subject> subjects ;
	private ArrayList<Subject> studentCourse;
	public Administrator(String id, char[] password, String name) {
		super(id,new String(password),name);
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
	//--------------------------------------------------------------------------------------
	//取得使用者清單
	public String[][] getUserList() {
		String[][] data = new String[100][100];
		try {
		//建立檔案夾位置
		File f = new File("data\\account");
		File[] fileList = f.listFiles();
		
		//依序開啟檔案夾內的資料
		int count = 0;//用戶計數
		for(int i=0; i<fileList.length; i++) {
			InputStreamReader reade = new InputStreamReader(new FileInputStream(fileList[i]),"utf-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			//依序紀錄用戶
			while((line = reader.readLine()) != null) {
				data[count][0] = line.split(" ")[0];
				data[count][1] = line.split(" ")[1];
				data[count][2] = line.split(" ")[2];
				data[count][3] = line.split(" ")[3];
				count ++;
			}
			reader.close();
		}
		}catch(IOException e) {
			System.out.println("取得使用者清單 Error");
		}
		return data;
	}
	//新增使用者
	public boolean addUser(String id,String password, String name, String type) {
		//建立新用戶資料字串
		String newUser = id +" "+ password +" "+ name +" "+ type ;
		try {
			//選擇檔案路徑
			switch (id.length()){
			case 9:
				if(type.equals("學生")) {
					addUser("data\\account\\studentAccount.txt", newUser, id);
					break;
				}
				JOptionPane.showMessageDialog(null, "帳號長度與類型錯誤");
				return false;
			case 5:
				if(type.equals("教授")) {
					addUser("data\\account\\teacherAccount.txt", newUser, id);
					break;
				}
				JOptionPane.showMessageDialog(null, "帳號長度與類型錯誤");
				return false;
				
			case 4:
				if(type.equals("管理員")) {
					addUser("data\\account\\administratorAccount.txt", newUser, id);
					break;
				}
				JOptionPane.showMessageDialog(null, "帳號長度與類型錯誤");
				return false;
				
			}
		}catch(IOException e) {
			System.out.println("新增使用者 Error");
			return false;
		}
		return true;
	}
	//執行新增用戶
	private void addUser(String dataLocation, String newUser, String id) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),"utf-8");    
		BufferedReader reader = new BufferedReader(read); 
		String line,user,fileContent = "";
		boolean check = true;
		//檢查ID是否重複
		while ((line = reader.readLine()) != null) {
			user = line.split(" ")[0];
			//使用者已在文件名單內跳出while並且不做對文件動作
			if(id.equals(user)) {
				JOptionPane.showMessageDialog(null, "帳號已被使用");
				check = false;
				break;
			}
			fileContent = fileContent.concat(line + "\n");
		}
		reader.close();
		//沒重複加入新用戶
		if(check) {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"utf-8");
			BufferedWriter writer=new BufferedWriter(write);
			writer.write(fileContent + newUser);
			writer.close();
			JOptionPane.showMessageDialog(null, "新增成功");
		}
		/*
		RandomAccessFile file = new RandomAccessFile(dataLocation, "rw");
		//寫入文件最後一行
		file.seek(file.length());
		file.write(newUser.getBytes());
		file.close();
		*/
	}
	
	//刪除使用者
	public boolean removeUser(String id) {
		try {
			//選擇檔案路徑
			switch (id.length()){
			case 9:
				removeUser("data\\account\\studentAccount.txt",id);
				break;
			case 5:
				removeUser("data\\account\\teacherAccount.txt",id);
				break;
			case 4:
				removeUser("data\\account\\administratorAccount.txt",id);
				break;
			default:
				JOptionPane.showMessageDialog(null, "帳號長度不正確");
				return false;
			}
		}catch(IOException e) {
			System.out.println("新增使用者 Error");
			return false;
		}
		return true;
	}
	//執行刪除用戶
	private void removeUser(String dataLocation, String id) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(reade);
		String line,fileContent = "";
		//找出用戶
		while((line = reader.readLine()) != null) {
			if(line.split(" ")[0].equals(id)){
				//跳過要刪用戶不做紀錄
			}else {
				//其餘紀錄
				fileContent = fileContent.concat(line+"\n");
			}
		}
		reader.close();
		//重新寫入新紀錄
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(fileContent);
		writer.close();
	}

	//取得用戶資料
	public String getUserInformation(String id) {
		String dataLocation = null;
		try {
			//選擇檔案路徑
			switch (id.length()){
			case 9:
				dataLocation = "data\\account\\studentAccount.txt";
				break;
			case 5:
				dataLocation = "data\\account\\teacherAccount.txt";
				break;
			case 4:
				dataLocation = "data\\account\\administratorAccount.txt";
				break;
			default:
				JOptionPane.showMessageDialog(null, "帳號長度不正確");
			}
			if(dataLocation != null) {
				//找出用戶資料
				File f = new File(dataLocation);
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
				BufferedReader reader = new BufferedReader(reade);
				String line;
				while((line = reader.readLine()) != null) {
					if(id.equals(line.split(" ")[0])) {
						return line;
					}
				}
				JOptionPane.showMessageDialog(null, "帳號不存在");
			}
		}catch(IOException e) {
			System.out.println("取得欲變更用戶資料   Error");
		}
		return null;
	}
	//變更用戶資料
	public boolean changeUserInformation(String id, String newId, String newPassword, String newName, String newType) {
		try {
			String newData = newId +" "+ newPassword +" "+ newName +" "+ newType;
			//選擇檔案路徑
			switch (newId.length()){
			case 9:
				if(!newType.equals("學生")) {
					JOptionPane.showMessageDialog(null, "帳號長度錯誤");
					break;
				}
				changeUserInformation("data\\account\\studentAccount.txt", id, newData);
				return true;
			case 5:
				if(!newType.equals("教授")) {
					JOptionPane.showMessageDialog(null, "帳號長度錯誤");
					break;
				}
				changeUserInformation("data\\account\\teacherAccount.txt", id, newData);
				return true;
			case 4:
				if(!newType.equals("管理員")) {
					JOptionPane.showMessageDialog(null, "帳號長度錯誤");
					break;
				}
				changeUserInformation("data\\account\\administratorAccount.txt", id, newData);
				return true;
			default:
				JOptionPane.showMessageDialog(null, "帳號長度不正確");
				return false;
			}
		}catch(IOException e) {
			System.out.println("變更使用者資料 Error");
		}
		return false;
	}
	//執行用戶資料變更
	private void changeUserInformation(String dataLocation, String id, String newData) throws IOException {
		File f = new File(dataLocation);
		InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
		BufferedReader reader = new BufferedReader(reade);
		String line,fileContent = "";
		//找出要變更的用戶資料
		while((line = reader.readLine()) != null) {
			if(line.split(" ")[0].equals(id)){
				//紀錄更新資料
				fileContent = fileContent.concat(newData+"\n");
			}else {
				//紀錄其他用戶
				fileContent = fileContent.concat(line+"\n");
			}
		}
		reader.close();
		//重新寫入更新後的資料
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(fileContent);
		writer.close();
		JOptionPane.showMessageDialog(null, "變更完成");
	}
	//--------------------------------------------------------------------------------------
	// 取得全部學生資訊清單
	public String[][] getAllStudentsInformationList() {
		try {
			// 建立資料料夾位置
			File f = new File("data\\students");
			// 建立每個文件位置
			File[] listFile = f.listFiles();
			// 讀取每個學生資訊
			String[][] studentsInformationData = new String[listFile.length][3];
			for (int i = 0; i < listFile.length; i++) {
				f = new File(listFile[i] + "\\學生資訊.txt");
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
				BufferedReader reader = new BufferedReader(reade);
				String line = reader.readLine();
				String[] lineData = line.split(" ");
				studentsInformationData[i] = lineData;
			}
			return studentsInformationData;

		} catch (IOException e) {
			System.out.println("取得學生資訊 Error");
		}
		// no work
		return null;
	}
	// 檢查學生存在
	public boolean checkStudentExist(String id) {
		// 檢查 學生是否存在
		boolean check = false;
		File f = new File("data\\students");
		File[] checkData = f.listFiles();
		f = new File("data\\students\\" + id);
		for (int i = 0; i < checkData.length; i++) {
			if (checkData[i].equals(f)) {
				check = true;
			}
		}
		return check;
	}
	// 新增學生資訊
	public boolean addStudentInfo(String id, String name, String startSchool) {
		try {
			// 學生還未建立過
			if (!checkStudentExist(id)) {
				// 建立 學生檔案夾
				File f = new File("data\\students\\" + id);
				f.mkdir();
				// 建立 選修課程 檔案
				f = new File("data\\students\\" + id + "\\選修課程.txt");
				f.createNewFile();
				// 建立 學生資訊 檔案
				f = new File("data\\students\\" + id + "\\學生資訊.txt");
				f.createNewFile();
				// 訊息寫入
				OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
				BufferedWriter writer = new BufferedWriter(write);
				String data = id + " " + name + " " + startSchool;
				writer.write(data);
				writer.close();
				JOptionPane.showMessageDialog(null, "新增完成");
			} else {
				JOptionPane.showMessageDialog(null, "學生已存在");
				return false;
			}
		} catch (IOException e) {
			System.out.println("新增學生資訊Error");
			return false;
		}
		return true;
	}

	// 刪除學生資訊
	public boolean removeStudent(String id) {
		// 學生存在
		if (checkStudentExist(id)) {
			// 宣告 要刪除檔案
			File f = new File("data\\students\\" + id);
			deleteFile(f);
			JOptionPane.showMessageDialog(null, "刪除完成");
		} else {
			JOptionPane.showMessageDialog(null, "學生不存在");
			return false;
		}
		return true;
	}
	// 執行檔案刪除
	private void deleteFile(File file) {
		// 判斷路徑是否存在
		if (file.exists()) {
			// 測試此抽象路徑名錶示的檔案是否是一個標準檔案
			if (file.isFile()) {
				file.delete();
				return;
			}
			// 不是檔案
			// 儲存 路徑下的所有的檔案和資料夾到listFiles陣列中
			File[] listFiles = file.listFiles();
			// 檔案路徑下所有檔案和資料夾的絕對路徑
			for (int i = 0; i < listFiles.length; i++) {
				// 遞迴作用:由外到內先一層一層刪除裡面的檔案 再從最內層 反過來刪除資料夾
				deleteFile(listFiles[i]);
			}
			file.delete();
		} else {
			System.out.println("該file路徑不存在！！");
		}
	}

	// 取得學生資訊
	public String getStudentInformation(String id) {
		String data = null;
		if (checkStudentExist(id)) {
			try {
				File f = new File("data\\students\\" + id + "\\學生資訊.txt");
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
				BufferedReader reader = new BufferedReader(reade);
				data = reader.readLine();
				reader.close();
				return data;
			} catch (IOException e) {
				System.out.println("取得學生資訊Error");
			}
		} else {
			JOptionPane.showMessageDialog(null, "學生不存在");
		}
		return data;
	}
	// 修改學生資訊
	public boolean changeStudentInformation(String newId, String newName, String newStartSchool) {
		try {
			/*
			 * File oldFile = new File("data\\students\\"+ id); File f = new
			 * File("data\\students\\"+ newId); oldFile.renameTo(f);
			 */
			// 建立新學生資料夾
			//addStudentInfo(newId, newName, newStartSchool);
			//copyFolder("data\\students\\" + id, "data\\students\\" + newId);
			String oldName = "";
			// 建立學生資料
			File f = new File("data\\students\\" + newId + "\\學生資訊.txt");
			// 建立新內容
			InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			String line, fileContent = "";
			// 捨棄舊資訊
			line = reader.readLine();
			oldName = line.split(" ")[1];
			// 加入新資訊
			fileContent = fileContent.concat(newId + " " + newName + " " + newStartSchool + "\n");
			// 加入原有選修課
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent.concat(line + "\n");
			}
			// 寫入新資料
			reader.close();
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			
			//更新學生帳戶的名稱
			f = new File("data\\account\\studentAccount.txt");
			reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
			reader = new BufferedReader(reade);
			fileContent = "";
			while((line = reader.readLine()) != null) {
				if(line.split(" ")[2].equals(oldName)) {
					fileContent = fileContent.concat(newId + " " + line.split(" ")[1] + " " + newName + " " + line.split(" ")[3] + "\n");
				}else {
					fileContent = fileContent.concat(line +"\n");
				}
			}
			reader.close();
			write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
			writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			
			// 刪除舊檔案
			//deleteFile(new File("data\\students\\" + id));
			//new File("data\\students\\" + id).delete();
			JOptionPane.showMessageDialog(null, "修改完成");
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	//-------------------------------------------------------------------------------------
	//檢查課程是否存在
	private boolean checkSubjectExist(String year, String subject) {
		// 檢查 學年是否存在
		File f = new File("data\\course");
		File[] checkYearList = f.listFiles();
		f = new File("data\\course\\" + year);
		for (int i = 0; i < checkYearList.length; i++) {
			//檢查 學年內有沒有開課
			if (checkYearList[i].equals(f)) {
				File[] checkSubjectList = checkYearList[i].listFiles();
				f = new File("data\\course\\" + year +"\\"+subject+".txt");
				for(int j=0; j<checkSubjectList.length; j++) {
					if(checkSubjectList[j].equals(f)) {
						//有課程
						return true;
					}
				}
			}
		}
		//沒有課程
		return false;
	}
	//新增課程
	public boolean addSubject(String year, String id, String subject, String credit, String type, String teacher) {
		try {
			//檢查教授是否存在
			boolean teacherNotExist = true;
			String teacherNumber = "";
			File f = new File("data\\account\\teacherAccount.txt");
			InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
			BufferedReader reader = new BufferedReader(read);
			String line;
			while((line = reader.readLine()) != null) {
				if(teacher.equals(line.split(" ")[2])) {
					teacherNumber = line.split(" ")[0];
					teacherNotExist = false;
					break;
				}
			}
			reader.close();
			if(teacherNotExist) {
				JOptionPane.showMessageDialog(null, "教授不存在");
				return false;
			}
			//課程存在 不可新增
			else if(checkSubjectExist(year, subject)) {
				//課程已存在
				JOptionPane.showMessageDialog(null, "課程資訊重複");
				return false;
			}
			else{
				//建立 學年資料夾
				f = new File("data\\course\\"+ year);
				f.mkdir();
				//建立 課程檔案
				f = new File("data\\course\\"+ year +"\\"+ subject +".txt");
				f.createNewFile();
				//寫入課程資訊
				String subjectInformation = id +" "+ subject +" "+ credit +" "+ type +" "+ teacher;
				OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
				BufferedWriter writer = new BufferedWriter(write);
				writer.write(subjectInformation);
				writer.close();
				
				//更新 教授執導課程
				f = new File("data\\teachers\\"+ teacherNumber + "\\指導課程.txt");
				String fileContent = year + " " + subject;
				read = new InputStreamReader(new FileInputStream(f),"UTF-8");
				reader = new BufferedReader(read);
				while((line = reader.readLine()) != null) {
					fileContent = fileContent.concat(line + "\n");
				}
				write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
				writer = new BufferedWriter(write);
				writer.write(fileContent);

				//完成提示
				JOptionPane.showMessageDialog(null, "新增完成");
				return true;
			}
		}catch(IOException e) {
			System.out.println("新增課程資訊Error");
		}
		return false;
	}
	//刪除課程
	public boolean removeSubject(String year, String subject) {
		//課程存在
		if(checkSubjectExist(year, subject)) {
			//刪除課程
			File f = new File("data\\course\\"+ year +"\\"+ subject +".txt");
			f.delete();
			//如果 學年下沒有其他課程 刪除 學年資料夾
			f = new File("data\\course\\"+ year);
			f.delete();
			JOptionPane.showMessageDialog(null, "刪除完成");
			return true;
		}else {
			//課程不存在
			JOptionPane.showMessageDialog(null, "課程不存在");
			return false;
		}
	}
	//取得 課程資訊 "108-1 SM101 計算機概論 3 必修 葉道明"
	public String getSubjectInformation(String year, String subject) {
		//課程存在
		if(checkSubjectExist(year, subject)) {
			try {
				//打開檔案讀取第一行 課程資訊 回傳
				File f = new File("data\\course\\"+ year +"\\"+ subject +".txt");
				InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
				BufferedReader reader = new BufferedReader(reade);
				String line = year + " ";
				line = line.concat(reader.readLine());
				reader.close();
				return line;
			}catch(IOException e) {
				System.out.println("取得課程資訊Error");
			}
		//課程不存在
		}else {
			JOptionPane.showMessageDialog(null, "課程不存在");
		}
		return null;
	}
	//取得 學年課程清單
	public Object[][] getYearSubjectInformationList(String year) {
		File f = new File("data\\course\\"+ year);
		//學年存在
		if(f.exists()) {
			try {
				//依序存取學年下每一個課程
				File[] listFiles = f.listFiles();
				Object[][] data = new Object[listFiles.length][5];
				for(int i=0; i<listFiles.length; i++) {
					//讀取課程第一行訊息
					InputStreamReader reade = new InputStreamReader(new FileInputStream(listFiles[i]), "utf-8");
					BufferedReader reader = new BufferedReader(reade);
					String[] line = reader.readLine().split(" ");
					//紀錄訊息
					data[i] = line;
					reader.close();
				}
				return data;
			}catch(IOException e) {
				System.out.println("學年課程清單Error");
			}
		//學年不存在
		}else {
			JOptionPane.showMessageDialog(null, "學年不存在");
		}
		return null;
	} 
	//修改 課程資訊
	public boolean changeSubjectInformation(String year, String subject, String newYear, String newId, String newSubject,
			String newCredit, String newType, String newTeacher) throws IOException {
		//檢查教授是否存在
		boolean teacherNotExist = true;
		File f = new File("data\\account\\teacherAccount.txt");
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
		BufferedReader reader = new BufferedReader(read);
		String line;
		while((line = reader.readLine()) != null) {
			if(newTeacher.equals(line.split(" ")[2])) {
				teacherNotExist = false;
				break;
			}
		}
		reader.close();
		if(teacherNotExist) {
			JOptionPane.showMessageDialog(null, "教授不存在");
			return false;
		}
		// 課程存在
		else if (checkSubjectExist(year, subject)) {
			// 更改目標已存在課程
			if (!newYear.equals(year) && !newSubject.equals(subject) && checkSubjectExist(newYear, newSubject)) {
				JOptionPane.showMessageDialog(null, "更改目標已存在課程\n請嘗試刪除更改目標課程");
				return false;
				// 更改目標 未被使用
			} else {
				try {
					//紀錄 舊老師,舊課名,學生學號 辨認 導師,學生資料 是否需要變更
					String oldTeacher, oldSubject;
					ArrayList<String> students = new ArrayList<String>();
					
					//課程資料變更
					//開舊檔 記錄學生與成績
					f = new File("data\\course\\" + year + "\\" + subject + ".txt");
					InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
					reader = new BufferedReader(reade);
					
					String fileContent = "";
					//紀錄 新資訊
					fileContent = fileContent.concat(newId +" "+ newSubject +" "+ newCredit +" "+ newType +" "+ newTeacher +"\n");
					//紀錄 舊老師,舊課名
					line = reader.readLine();
					oldTeacher = line.split(" ")[4];
					oldSubject = line.split(" ")[1];
					//紀錄 學生和成績
					while((line = reader.readLine()) != null) {
						students.add(line.split(" ")[0]);
						fileContent = fileContent.concat(line +"\n");
					}
					reader.close();
					String dataLocation = null;
					//學年與科名 沒變化
					if ((year.equals(newYear) && subject.equals(newSubject))) {
						dataLocation = "data\\course\\" + year + "\\" + subject + ".txt";
						//學年與科名 有變化
					} else {
						//刪除 舊檔案
						f.delete();
						//刪除 學年內沒有課程的資料夾
						f = new File("data\\course\\" + year);
						f.delete();
						//建立 新路徑
						dataLocation = "data\\course\\" + newYear + "\\" + newSubject + ".txt";
						addSubject(newYear, newId, newSubject, newCredit, newType, newTeacher);
					}
					//寫入 紀錄資料
					f = new File(dataLocation);
					OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
					BufferedWriter writer = new BufferedWriter(write);
					writer.write(fileContent);
					writer.close();
					
					//如果導師有變換 修改導師資料
					if(!oldTeacher.equals(newTeacher)) {
						//導師資料變更
						//找出 (舊,新)導師 資料
						f = new File("data\\account\\teacherAccount.txt");
						reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
						reader = new BufferedReader(reade);
						String oldTeacherNumber = null, newTeacherNumber = null, tNumber, tName;
						//比對 teacherAccount.txt 的一筆資料
						while((line = reader.readLine()) != null) {
							tNumber = line.split(" ")[0];
							tName = line.split(" ")[2];
							//是 舊老師資料
							if(tName.equals(oldTeacher)) {
								oldTeacherNumber = tNumber;
							}else if(tName.equals(newTeacher)) {
								newTeacherNumber = tNumber;
							}
							//導師編號 都找到了 跳出迴圈
							if(oldTeacherNumber != null && newTeacherNumber != null){
								break;
							}
						}
						reader.close();
						//************刪除 舊導師 課程資料*************
						f = new File("data\\teachers\\" + oldTeacherNumber + "\\指導課程.txt" );
						reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
						reader = new BufferedReader(reade);
						fileContent = "";
						//找出 要修改資訊
						while((line = reader.readLine()) != null) {
							//符合 目標修改課程 不做紀錄
							if(line.split(" ")[0].equals(year) && line.split(" ")[1].equals(subject)) {
								//不做任何紀錄
							//紀錄其他指導課程
							}else {
								fileContent = fileContent.concat(line + "\n");
							}
						}
						reader.close();
						//重新寫入 已刪除 修改課程 的資料
						write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
						writer = new BufferedWriter(write);
						writer.write(fileContent);
						writer.close();
						
						//************增加 新導師 課程資料************
						f = new File("data\\teachers\\" + newTeacherNumber + "\\指導課程.txt" );
						reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
						reader = new BufferedReader(reade);
						fileContent = newYear + " " + newSubject + "\n";
						//紀錄 原有 指導課程
						while((line = reader.readLine()) != null) {
							fileContent = fileContent.concat(line + "\n");
						}
						reader.close();
						//重新寫入 已新增 修改課程 的資料
						write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
						writer = new BufferedWriter(write);
						writer.write(fileContent);
						writer.close();
					}// end Teacher change
					
					//************如果 課名變更 修改學生資料************
					if(!oldSubject.equals(newSubject)) {
						//更新 學生們的 選修課程資料
						for(int i=0; i<students.size(); i++) {
							f = new File("data\\students\\" + students.get(i) + "\\選修課程.txt" );
							reade = new InputStreamReader(new FileInputStream(f), "UTF-8");
							reader = new BufferedReader(reade);
							fileContent = newYear + " " + newSubject + "\n";
							//找出 要修改資訊
							while((line = reader.readLine()) != null) {
								//符合 目標修改課程 不做紀錄
								if(line.split(" ")[0].equals(year) && line.split(" ")[1].equals(subject)) {
									//不做任何紀錄
								//紀錄 其他選修課程
								}else {
									fileContent = fileContent.concat(line + "\n");
								}
							}
							reader.close();
							//重新寫入 已新增 修改課程 的資料
							write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
							writer = new BufferedWriter(write);
							writer.write(fileContent);
							writer.close();
						}
					}
					JOptionPane.showMessageDialog(null, "變更完成");
				} catch (IOException e) {
					System.out.println("修改課程資訊Error");
				}
			}
			return true;
			//課程不存在
		} else {
			JOptionPane.showMessageDialog(null, "修改課程對象不存在");
			return false;
		}
	}
	//--------------------------------------------------------------------------------
	//新增 學生各學期的選修課程
	public boolean addStudentCourse(String year, String subject, String studentId) {
		try {
			//建立 學生,課程 路徑
			File fileStudentInformation = new File("data\\students\\" + studentId + "\\學生資訊.txt");
			File fileStudent = new File("data\\students\\" + studentId + "\\選修課程.txt");
			File fileSubject = new File("data\\course\\" + year + "\\" + subject + ".txt");

			//抓取 學生姓名
			InputStreamReader reade = new InputStreamReader(new FileInputStream(fileStudentInformation), "UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			String studentName =  reader.readLine().split(" ")[1];
			
			//*****新增 學生 選修課程*******
			reade = new InputStreamReader(new FileInputStream(fileStudent), "UTF-8");
			reader = new BufferedReader(reade);
			//新增 課程資料
			String subjectYear, subjectName, line;
			String fileContent = year + " " + subject + "\n";
			//紀錄 其他選修課程
			while((line = reader.readLine()) != null) {
				subjectYear = line.split(" ")[0];
				subjectName = line.split(" ")[1];
				//紀錄 要刪以外的科目
				if(subjectYear.equals(year) && subjectName.equals(subject)) {
					return false;
				}
				fileContent = fileContent.concat(line + "\n");
			}
			reader.close();
			//重新寫入 已新增 選修課程 的資料
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(fileStudent), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			
			//*****新增 課程 學生資料*******
			reade = new InputStreamReader(new FileInputStream(fileSubject), "UTF-8");
			reader = new BufferedReader(reade);
			fileContent = reader.readLine() + "\n";
			//新增 學生資料
			fileContent = fileContent.concat(studentId + " " + studentName +  " 0\n");
			//紀錄 其他學生
			while((line = reader.readLine()) != null) {
				fileContent = fileContent.concat(line + "\n");
			}
			reader.close();
			//重新寫入 已新增 課程學生 的資料
			write = new OutputStreamWriter(new FileOutputStream(fileSubject), "UTF-8");
			writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			
			JOptionPane.showMessageDialog(null,"新增完成");
			return true;
		}catch(IOException e) {
			System.out.println("新增學生選修課程Error");
		}
		return false;
	}
	
	//刪除 學生各學期的選修課程
	public boolean removeStudentCourse(String year, String subject, String studentId) {
		try {
			//刪除 學生,課程 路徑
			File fileStudentInformation = new File("data\\students\\" + studentId + "\\學生資訊.txt");
			File fileStudent = new File("data\\students\\" + studentId + "\\選修課程.txt");
			File fileSubject = new File("data\\course\\" + year + "\\" + subject + ".txt");

			//抓取 學生姓名
			InputStreamReader reade = new InputStreamReader(new FileInputStream(fileStudentInformation), "UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			String studentName =  reader.readLine().split(" ")[2];
			
			//*****刪除 學生 選修課程*******
			reade = new InputStreamReader(new FileInputStream(fileStudent), "UTF-8");
			reader = new BufferedReader(reade);
			//新增 課程資料
			String line, subjectYear, subjectName,fileContent = "";
			//紀錄 其他選修課程
			while((line = reader.readLine()) != null) {
				subjectYear = line.split(" ")[0];
				subjectName = line.split(" ")[1];
				//紀錄 要刪以外的科目
				if(!subjectYear.equals(year) || !subjectName.equals(subject)) {
					fileContent = fileContent.concat(line + "\n");
				}
			}
			reader.close();
			//重新寫入 已刪除 選修課程 的資料
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(fileStudent), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			
			//*****刪除 課程 學生資料*******
			reade = new InputStreamReader(new FileInputStream(fileSubject), "UTF-8");
			reader = new BufferedReader(reade);
			//紀錄 課程訊息
			fileContent = reader.readLine() + "\n";
			//紀錄 要刪除以外其他學生
			while((line = reader.readLine()) != null) {
				if(!studentId.equals(line.split(" ")[0]) && !studentName.equals(line.split(" ")[1])) {
					fileContent = fileContent.concat(line + "\n");
				}
			}
			reader.close();
			//重新寫入 已刪除 課程學生 的資料
			write = new OutputStreamWriter(new FileOutputStream(fileSubject), "UTF-8");
			writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
			
			JOptionPane.showMessageDialog(null,"刪除完成");
			return true;
		}catch(IOException e) {
			System.out.println("刪除學生選修課程Error");
			return false;
		}
	}
	
	//取得 學生學年修課清單
	public Object[][] getStudentCourseList(String year, String studentId) {
		this.subjects = new ArrayList<Subject>();
		String location = "data\\students\\" + studentId + "\\選修課程.txt";
		createCourse(location, subjects);
		Object[][] temp = new Object[subjects.size()][5];
		int count = 0;
		for(Subject i: subjects) {
			if(i.getYear().equals(year)) {
				temp[count][0] = i.getNumber();
				temp[count][1] = i.getName();
				temp[count][2] = i.getCredit();
				temp[count][3] = i.getType();
				temp[count++][4] = i.getTeacher();
			}
		}
		//去除temp陣列中空白值
		Object[][] data = new Object[count][5];
		for (int i = 0; i < count; i++) {
			for(int j = 0; j < temp[i].length; j++) {
				data[i][j] = temp[i][j];
			}
		}
		return data;
	}
	//建立選修課程
	private void createCourse(String dataLoaction, ArrayList<Subject> array){
		try {
			File f = new File(dataLoaction);
			InputStreamReader reade = new InputStreamReader(new FileInputStream(f), "utf-8");
			BufferedReader reader = new BufferedReader(reade);
			String line;
			while ((line = reader.readLine()) != null) {
				array.add(new Subject(line.split(" ")[0], line.split(" ")[1]));
			}
			reader.close();
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null,"學號錯誤");
		}
	}
	//修改 學生各學期的選修課程
	public void changeStudentCourse(String oldYear, String oldSubject, String newYear, String newSubject, String studentId) {
		
		File fileStudent = new File("data\\students\\" + studentId + "\\選修課程.txt");
		File fileNewSubject = new File("data\\course\\" + newYear + "\\" + newSubject + ".txt");
		File fileOldSubject = new File("data\\course\\" + oldYear + "\\" + oldSubject + ".txt");
		//檢查 學生 是否已在修改目標課程內
		boolean check = true;
		for(Subject i: subjects) {
			if(i.getYear().equals(newYear) && i.getName().equals(newSubject)) {
				check = false;
			}
		}
		
		//學生與科目 存在 執行變更
		if(fileStudent.exists() && fileNewSubject.exists() && fileOldSubject.exists() && check) {
			addStudentCourse(newYear,newSubject,studentId);
			removeStudentCourse(oldYear,oldSubject,studentId);
		}else {
			//學生和課程 都不存在
			if(!fileStudent.exists() && !fileNewSubject.exists() && !fileOldSubject.exists()) {
				JOptionPane.showMessageDialog(null,"輸入錯誤");
			//學生 不存在
			}else if(!fileStudent.exists()) {
				JOptionPane.showMessageDialog(null,"學生不存在");
			//修改科目 不存在
			}else if(!fileNewSubject.exists()) {
				JOptionPane.showMessageDialog(null,"修改科目不存在");
			}else if(!check) {
			//科目 已選修
				JOptionPane.showMessageDialog(null,"科目已選修");
			}
		}
	}
	//產生每個學生各學期成績單
	public  void OutputStudentCourse(String year, String studentId) {
		// Idea_1
		// 某某某 學年度    第 某 學期    學期成績單
		// 學號:        姓名: 某某某
		// 課目名稱    必選修別   學分   成績  
		// 某某某      必修       3      80
		// 以下空白---
		//
		// 學期總平均: 80
		// 實得學分: 3
		
		//建立 學生選修課程
		studentCourse = new ArrayList<Subject>();
		String location = "data\\students\\" + studentId + "\\選修課程.txt";
		createCourse(location, studentCourse);
		
		int totalCredits = 0;
		int totalScore = 0;
		int sumScore = 0;
		int averageScore = 0;
		String studentName = "";
		//抓取 學生姓名
		try {
			File f = new File("data\\students\\" + studentId +"\\學生資訊.txt");
			InputStreamReader reade = new InputStreamReader(new FileInputStream(f),"UTF-8");
			BufferedReader reader = new BufferedReader(reade);
			studentName = reader.readLine().split(" ")[1];
			reader.close();
		}catch(Exception e) {
			System.err.println(e);
		}
		//學生資料欄
		String student = "                        學號: " + studentId + "    姓名: " + studentName + "\n";
		//建立 檔案內容
		StringBuilder fileContent = new StringBuilder();
		fileContent.append("           國立高雄師範大學\n");
		fileContent.append("     "+year);
		//上學期
		if(year.split("")[4].equals("1")){
			fileContent.replace(28, 30, "學年     第一學期     學期成績單\n");
		//下學期
		}else {
			fileContent.replace(28, 30, "學年     第二學期     學期成績單\n");
		}
		fileContent.append(student);
		//標題欄
		String line = String.format("%8s%15s%9s%9s\n","課目名稱"," 必選修別","學分","成績\n");
		fileContent.append(line);
		
		int countLine = 0;
		//檢查 有無 必修 科目
		boolean cheackType = false;
		for(Subject i : studentCourse) {
			if(i.getYear().equals(year) && i.getType().equals("必修")) {
				cheackType = true;
				break;
			}
		}
		//紀錄 必修科目
		if(cheackType) {
			fileContent.append("[必修]\n");
			countLine++;
			for(Subject i : studentCourse) {
				if(i.getYear().equals(year) && i.getType().equals("必修")) {
					totalCredits += Integer.parseInt(i.getCredit());
					String name = i.getName();
					String type = i.getType();
					String credit = i.getCredit();
					String score = "";
					int addNameLong = name.length();
					for(int k=addNameLong; k<12; k++) {
						name += "    ";
					}
					for(SubjectStudents j : i.getSubjectStudents() ) {
						if(j.getId().equals(studentId)) {
							sumScore += Integer.parseInt(j.getScore());
							totalScore++;
							score = j.getScore();
							break;
						}
					}
					line = String.format("%-1s%-23s%-17s%3s\n",name,type,credit,score);
					fileContent.append(line);
					countLine++;
				}
			}
		}
		
		//檢查 有無 選修 科目
		cheackType = false;
		for(Subject i : studentCourse) {
			if(i.getYear().equals(year) && i.getType().equals("選修")) {
				cheackType = true;
				break;
			}
		}
		//紀錄 選修科目
		if(cheackType) {
			fileContent.append("[選修]\n");
			countLine++;
			for(Subject i : studentCourse) {
				if(i.getYear().equals(year) && i.getType().equals("選修")) {
					totalCredits += Integer.parseInt(i.getCredit());
					String name = i.getName();
					String type = i.getType();
					String credit = i.getCredit();
					String score = "";
					//課目名稱排版
					int addNameLong = name.length();
					for(int k=addNameLong; k<12; k++) {
						name += "    ";
					}
					for(SubjectStudents j : i.getSubjectStudents() ) {
						if(j.getId().equals(studentId)) {
							sumScore += Integer.parseInt(j.getScore());
							totalScore++;
							score = j.getScore();
							break;
						}
					}
					line = String.format("%-1s%-23s%-17s%3s\n",name,type,credit,score);
					fileContent.append(line);
					countLine++;
				}
			}
		}
		//無其他課程
		fileContent.append("---以下空白---\n");
		countLine++;
		//跳至頁尾
		for(int i=countLine; i<17; i++) {
			fileContent.append(" \n");
		}
		//學期平均與學分
		averageScore = sumScore/totalScore;
		fileContent.append("學期總平均: " + averageScore + "\n");
		fileContent.append("實得學分: " + totalCredits);
		
		//建立 txt檔
		try {
			File file = new File("data\\printFile\\toPdf.txt");
			file.createNewFile();
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent.toString());
			writer.close();
			
		}catch(Exception e) {
			System.err.println(e);
		}
		//輸出Pdf檔到C槽
		File f = new File("C:\\學期成績單");
		f.mkdir();
		new OutputPdf("data\\printFile\\toPdf.txt", "C:\\學期成績單\\" + studentId + " " + year + ".pdf");
	}
}// end class