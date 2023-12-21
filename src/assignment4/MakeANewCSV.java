package assignment4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MakeANewCSV {
	
	public void makeANewCSV(Student[] students, String corse) {
		BufferedWriter fileWriter = null;
		try {
			fileWriter = new BufferedWriter(new FileWriter(corse+"2.csv"));
			fileWriter.write("Student ID,Student Name,Course,Grade\n");//Set that first line up
			for (Student student : students) {
				if (student.getCourse().matches(corse + ".+")) {
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
