# Cinco Version 2.0

Cinco is a multi level support ticket system that automatically assigns tickets, depending on their severity, to a technician who can then manage the assigned tickets within the system. A technician can login, create an account an retrieve forgotten passwords through the system. 

## Installation
1. Navigate to https://mydesktop.rmit.edu.au/ in your browser.
2. Login.
3. Select "Browser Window".
4. Select "Desktops" followed by "All Desktops".
5. Select "RMIT Desktop".
6. Extract the zipped folder to the "H" drive.
7. Navigate to the start menu and type "cmd".
8. Open the program called "Command Prompt".
9. Enter "cd H:\Sprint1-A2G2\A2-G2-main\src" into the command line (without the quotation marks).
10. Enter "javac --release 8 Main.java"
11. Enter "java Main" to run the program.
12. You have successfully entered the program. 

## Usage - Staff mode
#### Login
1. Login to an existing account by entering 1 into the console. 
2. Enter your email address after the prompt.
3. Enter your password after the prompt.
4. If you have forgotten your password then enter 1 to retrieve your password.

#### Account Creation 
1. Create an account by entering 2 into the console.
2. Select 1 when asked what type of account is to be created. 
3. Enter your email address after the prompt.
4. Enter your full name after the prompt.
5. Enter your phone number after the prompt.
6. Enter a unique password that is 20 characters minimum and contains the following: at least 1 uppercase letter, 1 lowercase letter and 1 number. 

#### Forgotten Password
1. Retrieve your password by selecting 3 into the console after viewing the main menu. 
2. Enter the email you used to sign up for an account.

#### Create a New Ticket
1. Follow Login section, steps 1-3 as a prerequisite.
2. To submit a new ticket enter 1 into the console to view staff menu. 
3. Enter your new problem discription
4. Enter your problem severity (1 for low, 2 for medium and 3 for high).

#### View Your Open Tickets
1. Follow Login section, steps 1-3 as a prerequisite.
2. Enter 2 in the staff menu to view your open tickets.

## Usage - Technicians mode
#### Login
1. Login to an existing account by entering 1 into the console. 
2. Enter your email address after the prompt.
3. Enter your password after the prompt.
4. If you have forgotten your password then enter 1 to retrieve your password.

#### Account Creation 
1. Create an account by entering 2 into the console.
2. Select 2 when asked what type of account is to be created. 
3. Enter your email address after the prompt.
4. Enter your full name after the prompt.
5. Enter your phone number after the prompt.
6. Enter a unique password that is 20 characters minimum and contains the following: at least 1 uppercase letter, 1 lowercase letter and 1 number. 
7. Enter the Service Desk Level after the prompt (1 or 2).

#### View Assigned Tickets
1. Enter 1 when in the Technician Menu.

#### Change Ticket Severity
1. Select 2 in the Technician Menu.
2. Enter the number corresponding with the ticket number you wish to alter. 
3. Enter a severity level between 1 - 3.

#### Change Ticket Status 
1. Select 3 in the Technician Menu.
2. Enter the number corresponding with the ticket number you wish to alter.
3. Enter either 1 for closed and resolved or 2 for closed and unresolved.

#### View All Tickets
1. Select 4 in the Technician Menu to view all tickets within the system.

#### Re-open Ticket
1. Select 5 in the Technician Menu to view all tickets within the system.
2. Enter the number corresponding with the ticket number you wish to alter.
3. Confirm decision after the prompt (1 for yes, 2 for no, 3 to exit.

#### Generate Report
1. Select 6 in the Technician Menu to access Report Generation submenu and view how many ticket are outstanding and resolved in the last 30 days.
2. Enter the number in the submenu of the option you wish to generate a report of (1 for Resolved, 2 for Outstanding).


## Roadmap
#### Current Versions

Version 3.0 - Technicians ticket severity can be changed
            - Tickets can be closed and reopened and set as resolved or unresolved
            - Generate Ticket report (last 30 days)
            - Reopen closed tickets 
            - Tickets are automatically reassigned to a level 2 tech when their severity level is moved from low or medium to high

Version 2.0 - Assign tickets to technicians based on level and current assigned tickets
            - Ticket status viewable
            - Technicians can view assigned tickets and all tickets in the system
            - Staff can create tickets and view open tickets

Version 1.0 - Main menu functionality with account creation ability, login and retrieval of password. 
            - Staff menu functionality with ability, to log new tickets

#### Future Versions
            - Ticket archive system
            - Bug fixes
          

### Known Bugs
- Sometimes technicians do not have the ability to change the status or severity of a ticket.
- Sometimes all tickets are now shown when finding one to alter status or severity of.

## F.A.Q.
Q. H:\Sprint1-A2G2\A2-G2-main\src does not exist when I run the command in step 10.

A. If the command does not work, ensure that you have extracted the zipped folder correctly to the H drive. Then locate the A2-G2-main\src and copy the url in the address bar, insert that location into the command so it becomes "cd (your folder location)". 

Q. I need an alternative download

A. Follow the steps below to install via GitHub.
            1. Open Firefox and enter https://github.com/s3683007/A2-G2 into the address bar. 
            2. Select the green "code" tab then "download zip".
            3. Navigate to the downloaded zip folder.
            4. Right click the zip folder and choose "Extract all.."
            5. Extract the folder, by selecting browse, to the "H" drive.

Q. I can not find command prompt

A. Instead you can use Windows Powershell which is located within the start menu and can be found when scrolling down the menu.

## Authors
[Rahin Farook](https://github.com/RahinF), [Alanna Hembrow](https://github.com/s3683007), and [Matthew Hughes](https://github.com/s3816641)

## License
[MIT](https://choosealicense.com/licenses/mit/)
