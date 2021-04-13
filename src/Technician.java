
public class Technician extends User {

	private int technicianLevel;

	public Technician(String name, String email, String password, String contactNumber, int technicianLevel) {
		super(name, email, password, contactNumber);
		this.technicianLevel = technicianLevel;
	}

	public int getTechnicianLevel() {
		return technicianLevel;
	}

}
