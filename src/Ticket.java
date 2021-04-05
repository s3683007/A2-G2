
public class Ticket {

	private static String problemDiscription;
	private static int problemSeverity;
	
	
	public Ticket(String problemDiscription, int problemSeverity) {
		this.problemDiscription = problemDiscription;
		this.problemSeverity = problemSeverity;
	}


	public static void displayTicket() {
		System.out.println();
		System.out.println("Ticket Description: " + problemDiscription);
		System.out.println("Ticket Severity Stated: " + problemSeverity);
		System.out.println();
	}



}
