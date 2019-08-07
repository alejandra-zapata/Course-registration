import java.util.*;

public class Course {
	 
	private String courseName;
	private Student[] roster;
	private Student[] waitlist;
	private int maxRosterSize;
	private int maxWaitlistSize;
	
	private static int currentRosterSize = 0;
	private static int currentWaitlistSize = 0;
	
	private static final int DEFAULT_MAX_SIZE = 2;
	
	public Course(String courseName, int maxRosterSize, int maxWaitlistSize) {
		this.courseName = courseName;
		this.maxRosterSize = maxRosterSize;
		this.maxWaitlistSize = maxWaitlistSize;
		
		roster = new Student[currentRosterSize];
		waitlist = new Student[currentWaitlistSize];	
	}
	
	public Course(String courseName) {
		this(courseName, DEFAULT_MAX_SIZE, DEFAULT_MAX_SIZE);
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public int getMaxRosterSize() {
		return maxRosterSize;
	}
	
	public int getMaxWaitlistSize() {
		return maxWaitlistSize;
	}
	
	public Student[] getRoster() {
		return Arrays.copyOf(roster, roster.length);
	}
	
	public Student[] getWaitlist() {
		return Arrays.copyOf(waitlist, waitlist.length);
	}
	
	//No setters: No external user should be able to change either the courseName or the maximum size of the roster/waitlist. 
	
	@Override
	public String toString() {
		String s =  courseName + "\n" + "\n"  + 
				roster.length + " students  in the roster (maximum allowed: " + maxRosterSize + ")\n" +
				"Roster: " + Arrays.toString(roster) + "\n" + 
				"\n" + 
				waitlist.length + " students in the waitlist (maximum allowed: " + maxWaitlistSize + ")\n" +
				"Waitlist: " + Arrays.toString(waitlist) + "\n"; 
		return s;			
	}
	
	public boolean addStudent(Student student) {
		if(student.isTuitionPaid()) {
			if(isStudentThere(student, roster) || isStudentThere(student, waitlist)) {
				return false;
			}	
			if(currentRosterSize < maxRosterSize) {
				roster = addingStudentToArray(student, roster);
				if(roster.length > currentRosterSize) {
					currentRosterSize++;	
					return true;
				}
			}
			else if(currentRosterSize == maxRosterSize && currentWaitlistSize < maxWaitlistSize) {
				waitlist = addingStudentToArray(student, waitlist);
				if(waitlist.length > currentWaitlistSize) {
					currentWaitlistSize++;
					return true;
				}
			}						
		}
		return false;
	}
	
	public boolean dropStudent(Student student) {
		if(!isStudentThere(student, roster) && !isStudentThere(student, waitlist)) {
			return false;
		}
		if(isStudentThere(student, waitlist)) {
			waitlist = removingStudentFromArray(student, waitlist);
			if(waitlist.length < currentWaitlistSize) {
				currentWaitlistSize--;
				return true;
			}
		}
		if(isStudentThere(student, roster)) {
			roster = removingStudentFromArray(student, roster);
			if(roster.length < currentRosterSize) {
				currentRosterSize--;
			}

			if(currentWaitlistSize > 0) {
				roster = addingStudentToArray(waitlist[0], roster);
				currentRosterSize++;
				
				waitlist = removingStudentFromArray(waitlist[0], waitlist);
				currentWaitlistSize--;	
			}
			return true;
		}	
		return false;
	}
	
	private boolean isStudentThere(Student student, Student[] existingArray) {
		for(Student element : existingArray) {
			if(student.getID().equals(element.getID())) {
				return true;
			}	
		}	
		return false;
	}
	
	private Student[] addingStudentToArray(Student student, Student[] existingArray) {
		Student[] copyOfArray = new Student[existingArray.length +1];
		
		for(int i=0 ; i<existingArray.length ; i++) {
			copyOfArray[i] = existingArray[i];
		}
		copyOfArray[copyOfArray.length -1] = student;

		return copyOfArray;		
	}
	
	private Student[] removingStudentFromArray(Student student, Student[] existingArray) {
		Student[] copyOfArray = new Student[existingArray.length - 1];	
		int studentToRemoveIndex = -1;
		
		for(int i = 0; i<existingArray.length ; i++){
			if(existingArray[i].getID().equals(student.getID())) {
				studentToRemoveIndex = i;
			}
		}	
		for (int j = 0, k = 0; j < existingArray.length; j++) { 
			if (j == studentToRemoveIndex) { 
		        continue; 
		    }else{
		    	 copyOfArray[k++] = existingArray[j]; 	
		    }	
		}	
		return copyOfArray;
		}		
}
