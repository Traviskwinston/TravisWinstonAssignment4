package assignment4;
		
public class TravisWinstonAssignment4 {

	public static void main(String[] args) {
		
		//Declare Objects
		StudentService studentService = new StudentService();
		//Sort by grade Highest to Lowest
		studentService.sortByGrade();
		//Make the new Lists
		studentService.makeANewCSV("APMTH");
		studentService.makeANewCSV("COMPSCI");
		studentService.makeANewCSV("STAT");
	
	}
}
