
public class Ticket {

	private String problemDescription;
	private int problemSeverity;
	private String status;
	private User issuedBy;
	private User technician;

	public Ticket(String problemDescription, int problemSeverity, User issuedBy, User technician) {
		this.problemDescription = problemDescription;
		this.problemSeverity = problemSeverity;
		this.status = "Open";
		this.issuedBy = issuedBy;
		this.technician = technician;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public int getProblemSeverity() {
		return problemSeverity;
	}

//	public void setProblemSeverity(int problemSeverity) {
//		this.problemSeverity = problemSeverity;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Ticket [problemDescription=" + problemDescription + ", problemSeverity=" + problemSeverity + ", status="
				+ status + ", issuedBy=" + issuedBy + ", technician=" + technician + "]";
	}

	public User getIssuedBy() {
		return issuedBy;
	}

	public User getTechnician() {
		return this.technician;
	}

}
