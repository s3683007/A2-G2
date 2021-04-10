
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private Scanner scanner;
	private ArrayList<User> users;
	private ArrayList<Ticket> tickets;
	private User loggedInUser;

	public Main() {
		scanner = new Scanner(System.in);
		users = new ArrayList<>();
		tickets = new ArrayList<>();

		addTechnicians();
		displayMainMenu();
	}

	private void addTechnicians() {

//		Create Technicians
		User harry = new Technician("Harry Styles", "HS@cirno.com", "HS", "0400000001", 1);
		User niall = new Technician("Niall Horanand", "NH@cirno.com", "NH", "0400000002", 1);
		User liam = new Technician("Liam Payne", "LP@cirno.com", "LP", "0400000003", 1);
		User louis = new Technician("Louis Tomlinsonand", "LT@cirno.com", "LT", "0400000004", 2);
		User zayn = new Technician("Zayn Malik", "ZM@cirno.com", "ZM", "0400000005", 2);

		// added following user for quicker testing
		User matt = new Technician("1", "1", "1", "0400000006", 2);

//		Add to array
		Collections.addAll(users, harry, niall, liam, louis, zayn, matt);
	}

//	Main Menu
	private void displayMainMenu() {

//		Menu Options
		System.out.println("--------------------------------------");
		System.out.println("Main Menu");
		System.out.println("--------------------------------------");
		System.out.println("0. Exit");
		System.out.println("1. Login");
		System.out.println("2. Create Account");
		System.out.println("3. Forgotten Password");
		System.out.println();

//		Requires valid user input
		System.out.print("Please select an option: ");
		int menuInput = scanner.nextInt();

		while (menuInput > 3 || menuInput < 0) {
			System.out.print("Please select an option: ");
			menuInput = scanner.nextInt();

		}

//		fixes Scanner line error
		scanner.nextLine();

		switch (menuInput) {
		case 0:
			exit();
			break;
		case 1:
			login();
			break;
		case 2:
			createAccount();
			break;
		case 3:
			forgottenPassword();
			break;
		}

	}

	private void exit() {
		System.out.println("--------------------------------------");
		System.out.println("You have exited the program.");
		System.out.println("--------------------------------------");
		System.exit(0);

	}

	private void login() {
		System.out.println("--------------------------------------");
		System.out.println("Login Selected");
		System.out.println("--------------------------------------");

		User user = null;
		boolean usernameCorrect = false;

//		Asks for login email
		do {
			System.out.println("Please enter your email: ");
			String email = scanner.nextLine();

			for (int counter = 0; counter < users.size(); counter++) {

				User currentUser = users.get(counter);

//			If valid will ask for password
				if (email.matches(currentUser.getEmail())) {
					usernameCorrect = true;
					user = users.get(counter);
				}

			}

			if (!usernameCorrect) {
				System.out.println("Email incorrect.");
			}

		}

		while (!usernameCorrect);
//		Maximum 3 attempts allowed
		int totalAttempts = 3;
//		Ask for password
		boolean passwordCorrect = false;

		do {
			System.out.println("Please enter your password: ");
			String password = scanner.nextLine();

			if (totalAttempts != 0) {
//				if valid log user in
				if (password.matches(user.getPassword())) {
					passwordCorrect = true;

				} else
					System.out.println("Password incorrect.");
				totalAttempts--;

			} else {
// 				Prompt for password retrieval after maximum invalid password attempts
				System.out.println("Maximum number of attempts exceeded. Select an option below.");
				System.out.println("1. Forgotten Password");
				System.out.println("2. Exit");

				int maxAttemptMenu = scanner.nextInt();

// 				Fixes next line issue
				scanner.nextLine();
				switch (maxAttemptMenu) {
				case 1:
					forgottenPassword();
					break;
				case 2:
					exit();
					break;
				}
			}
		}

		while (!passwordCorrect);

		this.loggedInUser = user;
		System.out.printf("Logged in as %s\n", this.loggedInUser.getName());

		displayStaffMenu();

	}

	private void createAccount() {
		System.out.println("--------------------------------------");
		System.out.println("Create Account Selected");
		System.out.println("--------------------------------------");

		String email;
		boolean emailIsUnique = false;

//		Asks user for unique email. Will repeat until user enters a unique email.
		do {

			System.out.println("Please enter your email: ");
			email = scanner.nextLine();

			int usersFound = 0;
			for (int counter = 0; counter < users.size(); counter++) {

				User currentUser = users.get(counter);

				if (email.matches(currentUser.getEmail())) {
					usersFound++;
				}

			}

			if (usersFound > 0) {
				System.out.println("Account with this email already exists.");
			}

			else {
				emailIsUnique = true;
			}

		}

		while (!emailIsUnique);

		System.out.println("Please enter your full name: ");
		String name = scanner.nextLine();

		System.out.println("Please enter your contact number: ");
		String contactNumber = scanner.nextLine();

//		Asks user to enter password
		String password;
		boolean validPassword = false;
		do {

			System.out.println("Please select a password: ");
			password = scanner.nextLine();

//		Regex for password. Required 1 Upper, 1 Lower, 1 Digit, 20 min characters.
			String regex = "^.*(?=.{20,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(password);

//		if password is valid
			if (matcher.find()) {
				validPassword = true;
			}

			else {
				System.out.println(
						"Password must contain 1 uppercase, 1 lowercase, 1 digit and must be 20 characters in length.");
			}

		} while (!validPassword);

//	If details are correct create a staff object
		User user = new Staff(name, email, password, contactNumber);

//	add to array
		users.add(user);
		System.out.println("You have successfully created an account.");

		displayMainMenu();
	}

	private void forgottenPassword() {
		System.out.println("--------------------------------------");
		System.out.println("Forgotten Password Selected");
		System.out.println("--------------------------------------");

//		Check if user exists
		boolean passwordFound = false;
		do {

			System.out.println("Please enter your email: ");
			String email = scanner.nextLine();

			for (int counter = 0; counter < users.size(); counter++) {

				User currentUser = users.get(counter);

//				If found display Password
				if (email.matches(currentUser.getEmail())) {
					System.out.printf("Your Password is: %s\n", currentUser.getPassword());
					passwordFound = true;
				}

			}

			if (!passwordFound) {
				System.out.println("Account does not exist.");
			}

		}

		while (!passwordFound);

		displayMainMenu();

	}

//	Main Menu
	private void displayStaffMenu() {

//		Menu Options
		System.out.println("--------------------------------------");
		System.out.println("Staff Menu");
		System.out.println("--------------------------------------");
		System.out.println("0. Logout");
		System.out.println("1. Submit new ticket");
		System.out.println("2. Check existing ticket status");
		System.out.println();

//		Requires valid user input
		System.out.print("Please select an option: ");
		int menuInput = scanner.nextInt();

		while (menuInput > 3 || menuInput < 0) {
			System.out.print("Please select an option: ");
			menuInput = scanner.nextInt();

		}

//		fixes Scanner line error
		scanner.nextLine();

		switch (menuInput) {
		case 0:
			displayMainMenu();
			break;
		case 1:
			newTicket();
			break;
		case 2:
			checkTickets();
			break;
		}

	}

	private void checkTickets() {

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);
			System.out.println(currentTicket);

		}

		displayStaffMenu();
	}

	private void newTicket() {
		System.out.println("Please enter your problem description: ");
		String problemDiscription = scanner.nextLine();

		System.out.println("Please enter your problem severity:(1 for higher priority or 2 for lower priority)");
		int problemSeverity = scanner.nextInt();
		scanner.nextLine();

		Ticket ticket = new Ticket(problemDiscription, problemSeverity);

		tickets.add(ticket);

		System.out.println("Your ticket has been submitted, you can expect a response within 24 hours.");
		displayStaffMenu();

	}

	public static void main(String[] args) {
		new Main();

	}

}
