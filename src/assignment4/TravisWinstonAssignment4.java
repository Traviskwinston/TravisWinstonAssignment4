package assignment4;
		//1. Read the csv file, store it to an array.
		//2. Compare the first part of the "Course" column against the others
		//   to separate files into 3 different arrays
		//3. Sort the arrays by Column 4, for the grade. Highest grade at top
		//4. Store these new arrays into 3 new CSV files.
import java.io.*;
import java.util.*;

public class TravisWinstonAssignment4 {

	public static void main(String[] args) {
		//Declare Java Objects
		BufferedReader fileReader = null;
		BufferedReader fileSizeFinder = null; // Use to find the size of the CSV file.
		BufferedWriter fileWriter = null;
		BufferedWriter fileWriter2 = null;
		BufferedWriter fileWriter3 = null;

		
		//Declare Other Objects
		StudentService studentService = new StudentService();
		MakeANewCSV makeANewCSV = new MakeANewCSV();
		
		//Declare Primitives, and Strings
		String line; //Used to read the line the fileReader is on.
		int lineCount=-1; //used to find out how many lines are in the file. Starting -1 to not include the Student ID,Student Name,Course,Grade Line
		int lineNumber=0; //used to assign the line in the file to the corresponding spot in the array
		
		//Declare Array Variables
		//String[] masterList = new String[3]; //Read from main file store in here
		//String[] apMTH; //Store all APMTH students in here
		//String[] compSCI; //Store all COMPSCI students in here
		//String[] stat; //Store all STAT students in here
		
		//Declare Object Arrays
		Student[] students;
		
		try {
			//Store the Master List into an Array
			fileSizeFinder = new BufferedReader(new FileReader("student-master-list.csv"));
			while ((line = fileSizeFinder.readLine()) != null) {
				lineCount++; //how many lines are there so I know how big to make my array
			}
			
			students = new Student[lineCount];//Set the students array size to match the size of the list.
			//Assign each line from the data.txt file into a User Array.
			fileReader = new BufferedReader(new FileReader("student-master-list.csv"));//Need a new BufferedReader because the last one already went to the end of the file.
			fileReader.readLine(); // use this to skip that first pesky line.
			while ((line = fileReader.readLine()) != null){
				students[lineNumber] = studentService.createStudent((studentService.parseText(line)));
				lineNumber++;
			}
			//Sort by grade
			Arrays.sort(students, new Comparator<Student>() {
				@Override
				public int compare(Student person1, Student person2) {
					return person2.getGrade().compareTo(person1.getGrade());
				}
			});
			makeANewCSV.makeANewCSV(students,"APMTH");
			makeANewCSV.makeANewCSV(students,"COMPSCI");
			makeANewCSV.makeANewCSV(students,"STAT");
			//Write to new file
			//fileWriter = new BufferedWriter(new FileWriter("APMTH.csv"));
			//fileWriter2 = new BufferedWriter(new FileWriter("COMPSCI.csv"));
			//fileWriter3 = new BufferedWriter(new FileWriter("STAT.csv"));
			//fileWriter.write("Student ID,Student Name,Course,Grade\n");//Set that first line up
			//fileWriter2.write("Student ID,Student Name,Course,Grade\n");//Set that first line up
			//fileWriter3.write("Student ID,Student Name,Course,Grade\n");//Set that first line up
			
			//test if they are in APMTH then if they are, put them in a new csv file
//			for (Student student : students) {
//				if (student.getCourse().matches("APMTH.+")) {
//					fileWriter.write(student.getStudentID() + "," +
//									student.getStudentName() + "," +
//									student.getCourse() + "," + 
//									student.getGrade() + "\n");
//				}
//				else if (student.getCourse().matches("COMPSCI.+")) {
//					fileWriter2.write(student.getStudentID() + "," +
//									student.getStudentName() + "," +
//									student.getCourse() + "," + 
//									student.getGrade() + "\n");
//				}
//				else if (student.getCourse().matches("STAT.+")) {
//					fileWriter3.write(student.getStudentID() + "," +
//									student.getStudentName() + "," +
//									student.getCourse() + "," + 
//									student.getGrade() + "\n");
//				}
//			}
			
			//test sorting
			System.out.println("Check the lists");
			
			
			//Print the List
//			for (Student student : students) {
//				System.out.println(student.getStudentID());
//			}
			
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
				//fileWriter.close();
				//fileWriter2.close();
				//fileWriter3.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
