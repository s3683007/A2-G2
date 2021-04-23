import java.time.Instant;

public class Ticket {

	private String problemDescription;
	private String problemSeverity;
	private String status;
	private User issuedBy, technician;
	private Instant timeIssued, timeClosed;

	public Ticket(String problemDescription, String problemSeverity, User issuedBy, User technician,
			Instant timeIssued) {
		this.problemDescription = problemDescription;
		this.problemSeverity = problemSeverity;
		this.status = "Open";
		this.issuedBy = issuedBy;
		this.technician = technician;
		this.timeIssued = timeIssued;
		this.timeClosed = null;
	}

	public Instant getTimeClosed() {
		return timeClosed;
	}

	public void setTimeClosed(Instant timeClosed) {
		this.timeClosed = timeClosed;
	}

	public Instant getTimeIssued() {
		return timeIssued;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public String getProblemSeverity() {
		return problemSeverity;
	}

	public void setProblemSeverity(String problemSeverity) {
		this.problemSeverity = problemSeverity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Ticket [problemDescription=" + problemDescription + ", problemSeverity=" + problemSeverity + ", status="
				+ status + ", issuedBy=" + issuedBy + ", technician=" + technician + ", timeIssued=" + timeIssued
				+ ", timeClosed=" + timeClosed + "]";
	}

	public User getIssuedBy() {
		return issuedBy;
	}

	public User getTechnician() {
		return this.technician;
	}

	public void setTechnician(User technician) {
		this.technician = technician;

	}

}
