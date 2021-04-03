
public class Staff extends User{
	
	private static String employeeType = "Staff";

	public Staff(String name, String email, String password, String contactNumber) {
		super(name, email, password, contactNumber, employeeType);
	}
	


}
