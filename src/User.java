
public abstract class User {

	private String name, email, password, contactNumber;

	public User(String name, String email, String password, String contactNumber) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;

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

//	For testing
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", contactNumber=" + contactNumber
				+ "]";
	}

}
