package assignment4;
		
import java.util.*;

public class TravisWinstonAssignment4 {

	public static void main(String[] args) {
		
		//Declare Objects
		MakeANewCSV makeANewCSV = new MakeANewCSV();
		FileService fileService = new FileService("student-master-list.csv");//Adds this file to an Array
		
		//Returns the Array made to use for sorting.
		Student[] students = fileService.returnList();
		
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
	
	}
}
