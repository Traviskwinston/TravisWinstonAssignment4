package assignment4;
		
import java.io.*;
import java.util.*;

public class TravisWinstonAssignment4 {

	public static void main(String[] args) {
		//Declare Java Objects
		BufferedReader fileReader = null;
		BufferedReader fileSizeFinder = null; // Use to find the size of the CSV file because I don't like to hard-code
		
		//Declare Other Objects
		StudentService studentService = new StudentService();
		MakeANewCSV makeANewCSV = new MakeANewCSV();
		
		//Declare Primitives, and Strings
		String line; //Used to read the line the fileReaders are on.
		int lineCount=-1; //used to find out how many lines are in the file. Starting -1 to not include the Student ID,Student Name,Course,Grade Line
		int lineNumber=0; //used to assign the line in the file to the corresponding spot in the array
		
		//Declare Object Arrays
		Student[] students;
		
		//Program Starts here
		try {
			//Store the Master List into an Array
			fileSizeFinder = new BufferedReader(new FileReader("student-master-list.csv"));
			while ((line = fileSizeFinder.readLine()) != null) {
				lineCount++; //how many lines are there so I know how big to make my array
			}
			
			students = new Student[lineCount];
			//Assign each line from the student-master-list.csv file into a User Array.
			fileReader = new BufferedReader(new FileReader("student-master-list.csv"));
			fileReader.readLine(); // use this to skip that pesky first line.
			while ((line = fileReader.readLine()) != null){
				students[lineNumber] = studentService.createStudent((studentService.parseText(line)));
				lineNumber++;
			}
			//Sort by grade Highest to Lowest
			Arrays.sort(students, new Comparator<Student>() {
				@Override
				public int compare(Student person1, Student person2) {
					return person2.getGrade().compareTo(person1.getGrade());
				}
			});
			//Make the new Lists
			makeANewCSV.makeANewCSV(students,"APMTH");
			makeANewCSV.makeANewCSV(students,"COMPSCI");
			makeANewCSV.makeANewCSV(students,"STAT");
		//--------------------------------------------------------------	
		//Catch exceptions below, and close readers. Rest of the code should be above
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
}
