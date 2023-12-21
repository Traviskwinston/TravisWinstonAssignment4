package assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {
	
	private final String FILENAME = "student-master-list.csv";
	
	//Adds the students from the FILE to an array
	public Student[] addStudentsToArray() {
		String line; //Used to read the line the fileReaders are on.
		int lineCount=-1; //used to find out how many lines are in the file. Starting -1 to not include the Student ID,Student Name,Course,Grade Line
		int lineNumber=0; //used to assign the line in the file to the corresponding spot in the array
		BufferedReader fileReader = null;
		BufferedReader fileSizeFinder = null;
		
		Student[] students = null;
		StudentService studentService = new StudentService();
		
			try {
				//Store the Master List into an Array
				fileSizeFinder = new BufferedReader(new FileReader(FILENAME));
				while ((line = fileSizeFinder.readLine()) != null) {
					lineCount++; //how many lines are there so I know how big to make my array
				}
				
				students = new Student[lineCount];
				//Assign each line from the student-master-list.csv file into a User Array.
				fileReader = new BufferedReader(new FileReader(FILENAME));
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
			return students;
		}
	
	public void makeANewCSV(Student[] students, String course) {
		BufferedWriter fileWriter = null;
		try {
			fileWriter = new BufferedWriter(new FileWriter(course+".csv"));
			fileWriter.write("Student ID,Student Name,Course,Grade\n");//Set that first line up
			for (Student student : students) {
				if (student.getCourse().matches(course + ".+")) {
					fileWriter.write(student.getStudentID() + "," +
							student.getStudentName() + "," +
							student.getCourse() + "," + 
							student.getGrade() + "\n");
				}
			}
		} catch (IOException e) {
			System.out.println("There is I/O Exception");
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
}
