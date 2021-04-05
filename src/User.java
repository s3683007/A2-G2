
public abstract class User {

	private String name;
	private String email;
	private String password;
	private String contactNumber;
	private String employeeType;

	public User(String name, String email, String password, String contactNumber, String employeeType) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.employeeType = employeeType;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getEmployeeType() {
		return employeeType;
	}

//	For testing //
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", contactNumber=" + contactNumber
				+ ", employeeType=" + employeeType + "]";
	}

}
