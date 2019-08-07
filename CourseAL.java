//Course class using ArrayList instead of Array

import java.util.*;

public class CourseAL {
	private String courseName;
	private ArrayList<Student> roster;
	private ArrayList<Student> waitlist;
	private int maxRosterSize;
	private int maxWaitlistSize;
		
	private static final int DEFAULT_MAX_SIZE = 2;
	
	public CourseAL(String courseName, int maxRosterSize, int maxWaitlistSize) {
		this.courseName = courseName;
		this.maxRosterSize = maxRosterSize;
		this.maxWaitlistSize = maxWaitlistSize;
		
		roster = new ArrayList<Student>();
		waitlist = new ArrayList<Student>();	
	}
	public CourseAL(String courseName) {
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
	public ArrayList<Student> getRoster(){
		return roster;
	}
	public ArrayList<Student> getWatilist(){
		return waitlist;
	}
	
	//No setters: No external user should be able to change either the courseName or the maximum size of the roster/waitlist.
	
	public String toString() {
		String s =  courseName + "\n" + "\n"  + 
				roster.size() + " students  in the roster (maximum allowed: " + maxRosterSize + ")\n" +
				"Roster: " + roster + "\n" + 
				"\n" + 
				waitlist.size() + " students in the waitlist (maximum allowed: " + maxWaitlistSize + ")\n" +
				"Waitlist: " + waitlist + "\n"; 
		return s;			
	}
	
	public boolean addStudent(Student student) {
		if(student.isTuitionPaid()) {
			if(isStudentThere(student, roster) ||isStudentThere(student, waitlist) ) {
				return false;
			}	
			if(roster.size() < maxRosterSize) {	
				return roster.add(student);		
			}
			else if(roster.size() == maxRosterSize && waitlist.size() < maxWaitlistSize) {
				return waitlist.add(student);
			}
		}						
	return false;
	}
	
	public boolean dropStudent(Student student) {	
		if(!isStudentThere(student, waitlist) && !isStudentThere(student, roster)) {
			return false;
		}
		if(isStudentThere(student, waitlist) && removeWithIterator(student, waitlist)) {
			return true;
		}
		if(isStudentThere(student, roster) && removeWithIterator(student, roster)) {
			if(!waitlist.isEmpty()) {
				Student StudentToAddRemove = waitlist.get(0);
				roster.add(StudentToAddRemove);
				removeWithIterator(StudentToAddRemove, waitlist);
			}
			return true;
		}
	return false;
	}
	
	private boolean isStudentThere(Student student, ArrayList<Student> existingArray) {
		for(Student element : existingArray) {
			if(student.getID().equals(element.getID())) {
				return true;
			}	
		}	
		return false;
	}
	
	private boolean removeWithIterator(Student student, ArrayList<Student> list) {
		Iterator<Student> iterator = list.iterator();
		
		while(iterator.hasNext()) {
	        Student element = iterator.next();
	        
	        if(element.getID().equals(student.getID())) {
	             iterator.remove();
	             return true;
	        }
		}	
		return false;
	}
}
