package assignment4;

public class StudentService {
	
	public Student createStudent(String[] stringInput) {
		Student student = new Student();
		student.setStudentID(Integer.parseInt(stringInput[0]));
		student.setStudentName(stringInput[1]);
		student.setCourse(stringInput[2]);
		student.setGrade(Integer.parseInt(stringInput[3]));
		return student;
	}
		
	public String[] parseText(String input) {
		return input.split(",");
	}
}

