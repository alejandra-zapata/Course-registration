import java.util.*;

public class CourseDriver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//Course course = new Course("Programming Made Fun");
		CourseAL course = new CourseAL("Programming Made Fun");
		
		System.out.print("Welcome to the Course Registration system for the following course:   ");
		System.out.println(course);
		
		String menu =   "What action would you like to take? Press:" + "\n" + 
						"1 to add a student" + "\n" +
						"2 to drop a student" + "\n" +
						"3 to print the course" + "\n" +
						"4 to exit";		
		
		boolean keepGoing = true;
		while(keepGoing) {
			
			System.out.println(menu);	
			int userChoice = Integer.parseInt(scan.nextLine());
			
			if(userChoice == 1) {			
				Student student = new Student(nameFromUser(), idFromUser(), isTuitionPaidFromUser());
				boolean added = course.addStudent(student);
				System.out.println(student + (added ? " added successfully to either roster or waitlist" : " not added") + "\n" );			
			}

			if(userChoice == 2) {			
				Student student = new Student(nameFromUser(), idFromUser(), isTuitionPaidFromUser());
				boolean dropped = course.dropStudent(student);
				System.out.println(student + (dropped ? " dropped successfully to either roster or waitlist" : " not dropped") + "\n");
			}

			if(userChoice == 3) {			
				System.out.println(course);
			}
			
			if (userChoice == 4) {
				System.out.println("Thank you for using the registration system. Good-bye! " + course);
				keepGoing = false;
			}		
		}
	}
	
	public static String nameFromUser() {		
		Scanner scan = new Scanner(System.in);		
		System.out.println("Enter the student name: ");
		String name = scan.nextLine();
		
		return name;
	}
	
	public static String idFromUser() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the student ID: ");
		String id = scan.nextLine();
		
		return id;	
	}
	
	public static boolean isTuitionPaidFromUser() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Has the student paid tuition? Enter y or n:");
		char tuition = scan.nextLine().charAt(0);
		boolean tuitionPaid = false;
		
		if(tuition == 'y') {
			tuitionPaid = true;
		}
		return tuitionPaid;		
	}	
}
