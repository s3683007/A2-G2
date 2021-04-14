
public class Ticket {

	private String problemDiscription;
	private int problemSeverity;
	private String status;
	private User issuedBy;

	public Ticket(String problemDiscription, int problemSeverity, User issuedBy) {
		this.problemDiscription = problemDiscription;
		this.problemSeverity = problemSeverity;
		this.status = "Open";
		this.issuedBy = issuedBy;
	}

//	public void displayTicket() {
//		System.out.println();
//		System.out.println("Ticket Description: " + problemDiscription);
//		System.out.println("Ticket Severity Stated: " + problemSeverity);
//		System.out.println();
//	}

	public String getProblemDiscription() {
		return problemDiscription;
	}

	public void setProblemDiscription(String problemDiscription) {
		this.problemDiscription = problemDiscription;
	}

	public int getProblemSeverity() {
		return problemSeverity;
	}

	public void setProblemSeverity(int problemSeverity) {
		this.problemSeverity = problemSeverity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//For testing
	@Override
	public String toString() {
		return "\nProblem Discription =" + problemDiscription + "\nProblem Severity = " + problemSeverity
				+ "\nIssued By= " + this.issuedBy.getName();
	}

	public User getIssuedBy() {
		return issuedBy;
	}

}
