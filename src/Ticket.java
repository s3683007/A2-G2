
public class Ticket {

	private static String problemDiscription;
	private static int problemSeverity;

	public Ticket(String problemDiscription, int problemSeverity) {
		this.problemDiscription = problemDiscription;
		this.problemSeverity = problemSeverity;
	}

//	public void displayTicket() {
//		System.out.println();
//		System.out.println("Ticket Description: " + problemDiscription);
//		System.out.println("Ticket Severity Stated: " + problemSeverity);
//		System.out.println();
//	}

	public String problemDiscription() {
		return problemDiscription;
	}

	public int problemSeverity() {
		return problemSeverity;
	}

//For testing
	@Override
	public String toString() {
		return "\nProblem Discription =" + problemDiscription + "\nProblem Severity = " + problemSeverity+ "\n";
	}
	
}
