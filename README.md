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
2. Enter your email address after the prompt.
3. Enter your full name after the prompt.
4. Enter your phone number after the prompt.
5. Enter a unique password that is 20 characters minimum and contains the following: at least 1 uppercase letter, 1 lowercase letter and 1 number. 

#### Forgotten Password
1. Retrieve your password by selecting 3 into the console after viewing the main menu. 
2. Enter the email you used to sign up for an account.

#### Create a New Ticket
1. Follow Login section, steps 1-3 as a prerequisite.
2. To submit a new ticket enter 1 into the console to view staff menu. 
3. Enter your new problem discription
4. Enter your problem severity (1 for low, 2 for medium and 3 for high).

#### View Staff Tickets
1. Follow Login section, steps 1-3 as a prerequisite.
2. Enter 2 in the staff menu to view open tickets.

## Usage - Technicians mode
#### Login
1. Login to an existing account by entering 1 into the console. 
2. Enter your email address after the prompt.
3. Enter your password after the prompt.
4. If you have forgotten your password then enter 1 to retrieve your password.

#### View Your Tickets
1. Follow Login section, steps 1-3 as a prerequisite.
2. Enter 1 in the staff menu to view open tickets.

#### View All Tickets
1. Follow Login section, steps 1-3 as a prerequisite.
2. Enter 2 in the staff menu to view open tickets.



## Roadmap
#### Current Versions

Version 2.0 - Assign tickets to technicians based on level and current assigned tickets
            - Ticket status'
            - Staff ticket management system

Version 1.0 - Main menu functionality with account creation ability, login and retrieval of password. 
            - Staff menu functionality with ability, to log new tickets

#### Future Versions
- Manage tickets
- Ticket archive system
- Change a severity level to tickets


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
