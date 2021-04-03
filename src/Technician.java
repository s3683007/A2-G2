
public class Technician extends User {

	private int technicianLevel;
	private static String employeeType = "Technician";

	public Technician(String name, String email, String password, String contactNumber, int technicianLevel) {
		super(name, email, password, contactNumber, employeeType);
		this.technicianLevel = technicianLevel;
	}

	public int getTechnicianLevel() {
		return technicianLevel;
	}

}
