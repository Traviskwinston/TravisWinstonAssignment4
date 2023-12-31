package assignment4;

import java.util.Arrays;
import java.util.Comparator;

public class StudentService {
	
	private Student[] students;

	public StudentService() {
		FileService fileService = new FileService();
		students = fileService.addStudentsToArray();
	}
	
	//Sort the students by Grade.
	public void sortByGrade() {
		Arrays.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student person1, Student person2) {
				return person2.getGrade().compareTo(person1.getGrade());
			}
		});
	}
	
	//Use the FileService to write to a new CSV file based on the name of the course
	public void makeANewCSV(String course) {
		FileService fileService = new FileService();
		fileService.makeANewCSV(students, course);
	}
	
//	public Student createStudent(String[] stringInput) {
//		Student student = new Student();
//		student.setStudentID(Integer.parseInt(stringInput[0]));
//		student.setStudentName(stringInput[1]);
//		student.setCourse(stringInput[2]);
//		student.setGrade(Integer.parseInt(stringInput[3]));
//		return student;
//	}
		
	public String[] parseText(String input) {
		return input.split(",");
	}
}

