
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
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

//	Create and add Technicians to the system
	private void addTechnicians() {

		User harry = new Technician("Harry Styles", "HS@cinco.com", "HS", "0400000001", 1);
		User niall = new Technician("Niall Horanan", "NH@cinco.com", "NH", "0400000002", 1);
		User liam = new Technician("Liam Payne", "LP@cinco.com", "LP", "0400000003", 1);
		User louis = new Technician("Louis Tomlinson", "LT@cinco.com", "LT", "0400000004", 2);
		User zayn = new Technician("Zayn Malik", "ZM@cinco.com", "ZM", "0400000005", 2);

		Collections.addAll(users, harry, niall, liam, louis, zayn);
	}

//	Main Menu
	private void displayMainMenu() {

//		Menu Options
		displayMessageHeader("Main Menu");
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

	private void displayMessageHeader(String message) {
		System.out.println("--------------------------------------");
		System.out.println(message);
		System.out.println("--------------------------------------");
	}

	private void exit() {
		displayMessageHeader("You have exited the program.");
		System.exit(0);

	}

	private void login() {
		displayMessageHeader("Login Selected");

		User user = null;
		boolean usernameCorrect = false;

//		Asks for login email
		do {
			System.out.print("Please enter your email: ");
			String email = scanner.nextLine();

			for (int counter = 0; counter < users.size(); counter++) {

				User currentUser = users.get(counter);

//			If email is valid asks user for password
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
			System.out.print("Please enter your password: ");
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

		System.out.println("--------------------------------------");
		System.out.printf("Logged in as %s\n", this.loggedInUser.getName());

//		redirect user based on user type (staff or tech)
		if (this.loggedInUser instanceof Staff) {
			displayStaffMenu();
		}

		else if (this.loggedInUser instanceof Technician) {
			displayTechnicianMenu();
		}

	}

	private void displayTechnicianMenu() {

		archiveTickets();

		displayMessageHeader("Technician Menu");
		System.out.println("0. Logout");
		System.out.println("1. View Assigned Tickets");
		System.out.println("2. Change Severity");
		System.out.println("3. Change Status");
		System.out.println("4. View all Tickets");
		System.out.println("5. Re-open Ticket");
		System.out.println("6. Generate Ticket report (last 30 days)");
		System.out.println();

//		Requires valid user input
		System.out.print("Please select an option: ");
		int menuInput = scanner.nextInt();

		while (menuInput > 6 || menuInput < 0) {
			System.out.print("Please select an option: ");
			menuInput = scanner.nextInt();

		}

//		fixes Scanner line error
		scanner.nextLine();

		switch (menuInput) {
		case 0:
			this.loggedInUser = null;
			displayMainMenu();
			break;

		case 1:
			checkAssignedTicketsAsTech();
			break;

		case 2:
			changeSeverity();
			break;

		case 3:
			changeStatus();
			break;

		case 4:
			checkAllTicketsAsTech();
			break;

		case 5:
			reopenTicket();
			break;

		case 6:
			generateReport();
			break;

		}

	}

	private void generateReport() {

		displayTicketReportCount();

		System.out.println("Select type of report to generate");
		System.out.println("1. Resolved Tickets");
		System.out.println("2. Outstanding Tickets");
		System.out.println("3. Exit");
		System.out.println();

		System.out.print("Please select an option: ");
		int menuInput = scanner.nextInt();

		scanner.nextLine();

		System.out.println();

		switch (menuInput) {
		case 1:
			generateResolvedTicketReport();
			break;

		case 2:
			generateOutstandingTicketReport();
			break;

		case 3:
			displayTechnicianMenu();
			break;

		default:
			generateReport();
			break;
		}

		displayTechnicianMenu();

	}

	private void displayTicketReportCount() {

		int resolvedTicketCount = 0;
		int outstandingTicketCount = 0;

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			switch (currentTicket.getStatus()) {

			case "Open":
				outstandingTicketCount++;
				break;

			case "Closed & Resolved":
				resolvedTicketCount++;
				break;

			}

		}

		System.out.println();
		System.out.printf("Resolved: %s\n", resolvedTicketCount);
		System.out.printf("Outstanding: %s\n", outstandingTicketCount);
		System.out.println();

	}

	private void generateOutstandingTicketReport() {

		Instant oneMonthAgo = Instant.now().minus(30, ChronoUnit.DAYS);

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			int value = currentTicket.getTimeIssued().compareTo(oneMonthAgo);

//			 if ticket was created within the timeframe
			if (value > 0 && currentTicket.getStatus().equals("Open")) {

//				convert time instant to readable format
				Date date = Date.from(currentTicket.getTimeIssued());
				SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
				String formattedDate = formatter.format(date);

				System.out.printf("Description: %s\n", currentTicket.getProblemDescription());
				System.out.printf("Severity: %s\n", currentTicket.getProblemSeverity());
				System.out.printf("Time Submitted: %s\n", formattedDate);
				System.out.printf("Issued by: %s\n", currentTicket.getIssuedBy().getName());
				System.out.println();
			}
		}

	}

	private void generateResolvedTicketReport() {

		Instant oneMonthAgo = Instant.now().minus(30, ChronoUnit.DAYS);

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			int value = currentTicket.getTimeIssued().compareTo(oneMonthAgo);

//		 if ticket was created within the timeframe
			if (value > 0 && currentTicket.getStatus().contains("Resolved")) {

//			convert time instant to readable format
				Date date = Date.from(currentTicket.getTimeIssued());
				SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
				String formattedDate = formatter.format(date);

//			time taken
				Duration timeElapsed = Duration.between(currentTicket.getTimeIssued(), currentTicket.getTimeClosed());

				long duration = 0;
				String timeTaken;

				if (timeElapsed.toDays() > 0) {
					duration = timeElapsed.toDays();
					timeTaken = String.format("%s day/s", duration);
				}

				else if (timeElapsed.toHours() > 0) {
					duration = timeElapsed.toHours();
					timeTaken = String.format("%s hour/s", duration);
				}

				else if (timeElapsed.toMinutes() > 0) {
					duration = timeElapsed.toMinutes();
					timeTaken = String.format("%s minute/s", duration);
				}

				else {
					duration = timeElapsed.toSeconds();
					timeTaken = String.format("%s second/s", duration);
				}

				System.out.printf("Description: %s\n", currentTicket.getProblemDescription());
				System.out.printf("Severity: %s\n", currentTicket.getProblemSeverity());
				System.out.printf("Time Submitted: %s\n", formattedDate);
				System.out.printf("Issued by: %s\n", currentTicket.getIssuedBy().getName());
				System.out.printf("Technician Assigned: %s\n", currentTicket.getTechnician().getName());
				System.out.printf("Time Taken: %s\n", timeTaken);

				System.out.println();
			}
		}

	}

//	check ticket and set status to archived if time closed in greater than 24 hours
	private void archiveTickets() {

		Instant currentTime = Instant.now();

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);
			Duration timeElapsed = Duration.between(currentTime, currentTicket.getTimeIssued());

			if (currentTicket.getStatus().contains("Closed") && timeElapsed.toHours() > 24) {
				currentTicket.setStatus("Archived");
			}

		}

	}

	private void reopenTicket() {

		Instant currentTime = Instant.now();

		ArrayList<Ticket> tempTickets = new ArrayList<>();

		int ticketCounter = 0;
		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);
			Duration timeElapsed = Duration.between(currentTime, currentTicket.getTimeIssued());

//			display ticket that are closed that can be reopened.
			if (currentTicket.getStatus().contains("Closed") && timeElapsed.toHours() < 24)

			{
				System.out.println();
				System.out.println("--------------------------------------");
				System.out.printf("Ticket Number %s\n", ticketCounter);
				System.out.println("--------------------------------------");
				displayTicketInfo(currentTicket);
				tempTickets.add(currentTicket);
				ticketCounter++;
			}

		}

		if (tempTickets.isEmpty()) {
			System.out.println("There are no tickets that can be reopened at this time.");
			displayTechnicianMenu();
		}

		System.out.println();
		System.out.println("--------------------------------------");
		System.out.print("Please select a ticket: ");
		int ticketSelected = scanner.nextInt();

		scanner.nextLine();

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			if (tempTickets.get(ticketSelected).equals(currentTicket)) {
				System.out.println("--------------------------------------");
				System.out.println("Ticket Selected");
				displayTicketInfo(currentTicket);
				System.out.println("--------------------------------------");

				System.out.printf("Re-open Ticket?\n");
				System.out.println("--------------------------------------");
				System.out.println("1. Yes");
				System.out.println("2. No");
				System.out.println("3. Exit");

				System.out.println("Please select an option: ");
				int reopenConfirmation = scanner.nextInt();

				scanner.nextLine();

				switch (reopenConfirmation) {
				case 1:
					currentTicket.setStatus("Open");
					break;

				case 2:
					reopenTicket();
					break;

				case 3:
					displayTechnicianMenu();
					break;

				}

			}
		}

		displayTechnicianMenu();

	}

	private void changeStatus() {

		ArrayList<Ticket> tempTickets = new ArrayList<>();

		int ticketCounter = 0;
		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			if (currentTicket.getTechnician() == this.loggedInUser && currentTicket.getStatus().equals("Open"))

			{
				System.out.println();
				System.out.println("--------------------------------------");
				System.out.printf("Ticket Number %s\n", ticketCounter);
				System.out.println("--------------------------------------");
				displayTicketInfo(currentTicket);
				tempTickets.add(currentTicket);
				ticketCounter++;
			}

		}

		if (tempTickets.isEmpty()) {
			System.out.println("There are no tickets in the system.");
			displayTechnicianMenu();
		}

		System.out.println();
		System.out.println("--------------------------------------");
		System.out.print("Please select a ticket: ");
		int ticketSelected = scanner.nextInt();

		scanner.nextLine();

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			if (tempTickets.get(ticketSelected).equals(currentTicket)) {
				System.out.println("--------------------------------------");
				System.out.println("Ticket Selected");
				displayTicketInfo(currentTicket);
				System.out.println("--------------------------------------");
				System.out.println();

				System.out.println("Change status from Open to");
				System.out.println("1. Closed & Resolved");
				System.out.println("2. Closed & Unresolved");
				System.out.println("3. Exit");

				System.out.print("Please select an option: ");
				int statusSelected = scanner.nextInt();

				scanner.nextLine();

//				create closing timestamp
				Instant timeClosed = Instant.now();

				switch (statusSelected) {
				case 1:
					currentTicket.setStatus("Closed & Resolved");
					currentTicket.setTimeClosed(timeClosed);
					break;

				case 2:
					currentTicket.setStatus("Closed & Unresolved");
					currentTicket.setTimeClosed(timeClosed);

					System.out.println(currentTicket);
					break;

				case 3:
					displayTechnicianMenu();
					break;

				}

			}
		}
		displayTechnicianMenu();

	}

	private void changeSeverity() {
		ArrayList<Ticket> tempTickets = new ArrayList<>();

		int ticketCounter = 0;
		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			if (currentTicket.getTechnician() == this.loggedInUser && currentTicket.getStatus().equals("Open"))

			{
				System.out.println();
				System.out.println("--------------------------------------");
				System.out.printf("Ticket Number %s\n", ticketCounter);
				System.out.println("--------------------------------------");
				displayTicketInfo(currentTicket);
				tempTickets.add(currentTicket);
				ticketCounter++;
			}

		}

		if (tempTickets.isEmpty()) {
			System.out.println("There are no tickets in the system.");
			displayTechnicianMenu();
		}

		System.out.println();
		System.out.println("--------------------------------------");
		System.out.print("Please select a ticket: ");
		int ticketSelected = scanner.nextInt();

		scanner.nextLine();

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			if (tempTickets.get(ticketSelected).equals(currentTicket)) {
				System.out.println("--------------------------------------");
				System.out.println("Ticket Selected");
				displayTicketInfo(currentTicket);
				System.out.println("--------------------------------------");

				System.out.printf("Current Severity: %s \n", currentTicket.getProblemSeverity());
				System.out.println("--------------------------------------");
				System.out.println("1. Low");
				System.out.println("2. Medium");
				System.out.println("3. High");
				System.out.println("4. Exit");
				System.out.println();

				System.out.print("Please select an option: ");
				int severitySelected = scanner.nextInt();

				scanner.nextLine();

				int serviceDeskLevel = 0;
				String severity = null;

				switch (severitySelected) {
				case 1:
					severity = "Low";
					serviceDeskLevel = 1;
					break;

				case 2:
					severity = "Medium";
					serviceDeskLevel = 1;
					break;

				case 3:
					severity = "High";
					serviceDeskLevel = 2;
					break;

				case 4:
					displayTechnicianMenu();
					break;

				}

//				reassign technician if the severity level has changed
				if (!currentTicket.getProblemSeverity().equals(severity)) {
					System.out.printf("Severity level has been changed from %s to %s.\n",
							currentTicket.getProblemSeverity(), severity);

					currentTicket.setProblemSeverity(severity);
					currentTicket.setTechnician(assignTechnician(serviceDeskLevel));

				}

				else {
					System.out.printf("Severity level remains %s.\n", currentTicket.getProblemSeverity());
				}

			}
		}
		displayTechnicianMenu();
	}

	private void createAccount() {
		displayMessageHeader("Create Account Selected");

		String email;
		boolean emailIsUnique = false;

		System.out.println("Type of account:");
		System.out.println("1. Staff");
		System.out.println("2. Technician");
		System.out.println();

		System.out.print("Please select an option: ");

		int typeOfAccount = scanner.nextInt();
		scanner.nextLine();
		System.out.println();

//		Asks user for unique email. Will repeat until user enters a unique email.
		do {

			System.out.print("Please enter your email: ");
			email = scanner.nextLine();

			int usersFound = 0;
			for (int counter = 0; counter < users.size(); counter++) {

				User currentUser = users.get(counter);

				if (email.matches(currentUser.getEmail())) {
					usersFound++;
				}

			}

			if (usersFound > 0) {
				System.out.print("Account with this email already exists.");
			}

			else {
				emailIsUnique = true;
			}

		}

		while (!emailIsUnique);

		System.out.print("Please enter your full name: ");
		String name = scanner.nextLine();

		System.out.print("Please enter your contact number: ");
		String contactNumber = scanner.nextLine();

//		Asks user to enter password
		String password;
		boolean validPassword = false;
		do {

			System.out.print("Please select a password: ");
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
				displayMessageHeader(
						"Password must contain:\n 1 uppercase\n 1 lowercase\n 1 digit\n Must be 20 characters in length");
			}

		} while (!validPassword);

//		create user based on user selection
		User newUser = null;

		switch (typeOfAccount) {
		case 1:
			newUser = new Staff(name, email, password, contactNumber);
			break;

		case 2:
			System.out.print("Please enter service desk level (1 or 2): ");
			int serviceDeskLevel = scanner.nextInt();
			scanner.nextLine();
			newUser = new Technician(name, email, password, contactNumber, serviceDeskLevel);
			break;
		}

		users.add(newUser);
		System.out.println("You have successfully created an account.");

		this.loggedInUser = newUser;
		System.out.println("Logged in as " + this.loggedInUser.getName());

//		log into newly created account
		if (this.loggedInUser instanceof Staff) {
			displayStaffMenu();
		}

		else if (this.loggedInUser instanceof Technician) {
			displayTechnicianMenu();
		}

	}

	private void forgottenPassword() {
		displayMessageHeader("Forgotten Password Selected");

//		Check if user exists
		boolean passwordFound = false;
		do {

			System.out.print("Please enter your email: ");
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
				displayMessageHeader("Account does not exist.");
			}

		}

		while (!passwordFound);

		displayMainMenu();

	}

	private void displayStaffMenu() {

		archiveTickets();

//		Menu Options
		displayMessageHeader("Staff Menu");
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
			this.loggedInUser = null;
			displayMainMenu();
			break;
		case 1:
			createTicket();
			break;
		case 2:
			checkTicketsAsStaff();
			break;
		}

	}

	private void displayTicketInfo(Ticket currentTicket) {

		System.out.println();
		System.out.printf("Description: %s\n", currentTicket.getProblemDescription());
		System.out.printf("Severity: %s\n", currentTicket.getProblemSeverity());
		System.out.printf("Status: %s\n", currentTicket.getStatus());
		System.out.printf("Issued by: %s\n", currentTicket.getIssuedBy().getName());
		System.out.printf("Technician Assigned: %s\n", currentTicket.getTechnician().getName());
		System.out.println();
	}

	private void checkTicketsAsStaff() {

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			if (currentTicket.getIssuedBy() == this.loggedInUser) {
				displayTicketInfo(currentTicket);
			}

		}

		displayStaffMenu();
	}

//	View all tickets assigned to the logged in technician.
	private void checkAssignedTicketsAsTech() {

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			if (currentTicket.getTechnician() == this.loggedInUser) {
				displayTicketInfo(currentTicket);
			}
		}

		displayTechnicianMenu();
	}

//	display tickets belonging to logged in tech or have status of closed or archived.
	private void checkAllTicketsAsTech() {

		if (tickets.isEmpty()) {
			System.out.println("There are no tickets in the system.");
			displayTechnicianMenu();
		}

		for (int counter = 0; counter < tickets.size(); counter++) {

			Ticket currentTicket = tickets.get(counter);

			if (currentTicket.getTechnician() == this.loggedInUser
					|| currentTicket.getStatus().equals("Closed & Resolved")
					|| currentTicket.getStatus().equals("Closed & Unresolved")
					|| currentTicket.getStatus().equals("Archived"))

			{
				displayTicketInfo(currentTicket);
			}

		}

		displayTechnicianMenu();

	}

	private void createTicket() {
		System.out.print("Please enter your problem description: ");
		String problemDescription = scanner.nextLine();

		System.out.println("Please select your problem severity.");
		System.out.println("1. Low");
		System.out.println("2. Medium");
		System.out.println("3. High");
		System.out.print("Please select an option: ");
		int severitySelected = scanner.nextInt();
		scanner.nextLine();

//		Sets the severity and service desk level based on user input
		int serviceDeskLevel = 0;
		String severity = null;

		switch (severitySelected) {
		case 1:
			serviceDeskLevel = 1;
			severity = "Low";
			break;

		case 2:
			serviceDeskLevel = 1;
			severity = "Medium";
			break;

		case 3:
			serviceDeskLevel = 2;
			severity = "High";
			break;

		}

		User assignedTechnician = assignTechnician(serviceDeskLevel);

//		create timestamp
		Instant timeIssued = Instant.now();

		Ticket ticket = new Ticket(problemDescription, severity, this.loggedInUser, assignedTechnician, timeIssued);

		tickets.add(ticket);

		System.out.println("--------------------------------------");
		System.out.println("Your ticket has been submitted, you can expect a response within 24 hours.");

		displayStaffMenu();

	}

	private User assignTechnician(int serviceDeskLevel) {

//		create list of techs with same severity level
		Map<User, Integer> technicians = createTechnicianList(serviceDeskLevel);

//		count tickets of techs
		Map<User, Integer> technicianTicketCount = countTickets(technicians);

//		find lowest count
		int minCount = Integer.MAX_VALUE;
		for (Entry<User, Integer> entry : technicianTicketCount.entrySet()) {

			if (entry.getValue() < minCount) {
				minCount = entry.getValue();
			}
		}

//		find all techs with lowest count 
		ArrayList<User> techs = new ArrayList<>();

		for (Entry<User, Integer> entry : technicians.entrySet()) {

			if (entry.getValue() == minCount) {
				techs.add(entry.getKey());
			}
		}

		User assignedUser = null;

//		if more than 1 tech with lowest count
		if (techs.size() > 1) {
//			random selection
			Random random = new Random();
			assignedUser = techs.get(random.nextInt(techs.size()));
		}

		else {
//			assign single tech
			assignedUser = techs.get(0);
		}

		return assignedUser;
	}

//	count tickets of user
	private Map<User, Integer> countTickets(Map<User, Integer> technicians) {

		for (int counter = 0; counter < tickets.size(); counter++) {

			for (Entry<User, Integer> entry : technicians.entrySet()) {

				if (tickets.get(counter).getTechnician().getEmail().equals(entry.getKey().getEmail())) {

					technicians.put(entry.getKey(), entry.getValue() + 1);
				}
			}
		}
		return technicians;
	}

//	create map with technicians and default ticket count of 0
//	with tech level equal to severity
	private Map<User, Integer> createTechnicianList(int problemSeverity) {

//		find tech with same service level as issue
		Map<User, Integer> technicians = new HashMap<>();
		for (int counter = 0; counter < users.size(); counter++) {

			User currentUser = users.get(counter);

			if (currentUser instanceof Technician) {

				Technician technician = (Technician) currentUser;

				if (technician.getTechnicianLevel() == problemSeverity) {
					technicians.put(technician, 0);
				}
			}
		}

		return technicians;

	}

	public static void main(String[] args) {
		new Main();

	}

}
