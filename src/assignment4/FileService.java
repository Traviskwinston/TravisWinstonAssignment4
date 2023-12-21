package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileService {
	BufferedReader fileReader = null;
	BufferedReader fileSizeFinder = null;
	
	//Declare Primitives, and Strings
	String line; //Used to read the line the fileReaders are on.
	int lineCount=-1; //used to find out how many lines are in the file. Starting -1 to not include the Student ID,Student Name,Course,Grade Line
	int lineNumber=0; //used to assign the line in the file to the corresponding spot in the array
	
	Student[] students;
	StudentService studentService = new StudentService();
	
	FileService(String listName) {
		try {
			//Store the Master List into an Array
			fileSizeFinder = new BufferedReader(new FileReader(listName));
			while ((line = fileSizeFinder.readLine()) != null) {
				lineCount++; //how many lines are there so I know how big to make my array
			}
			
			students = new Student[lineCount];
			//Assign each line from the student-master-list.csv file into a User Array.
			fileReader = new BufferedReader(new FileReader(listName));
			fileReader.readLine(); // use this to skip that pesky first line.
			while ((line = fileReader.readLine()) != null){
				students[lineNumber] = studentService.createStudent((studentService.parseText(line)));
				lineNumber++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("No student-master-list.csv file found");
		} catch (IOException e) {
			System.out.println("There is I/O Exception");
		} finally {
			try {
				fileReader.close();
				fileSizeFinder.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public Student[] returnList() {
		return students;
	}
	
}
