import java.util.Scanner;

public class Main {
    private final Scanner input = new Scanner(System.in);;
    public static void main(String[] args) {
        //create login obj
        Login login = new Login();
        //create main obj
        Main main = new Main();
        //create menuFunc obj
        MenuFunc menuFunc = new MenuFunc();

        //define variables
        int option;
        String username, password, firstName = null, lastName, contactNumber, dateOfBirth, yearOfStudy;
        UserType userType;

        //loop main menu
        while(true){
            try{
                // print main main menu
                main.printNewMenu();
                //get user input
                System.out.print("Your option: ");
                //assign to user input to option
                option = main.input.nextInt();

                //register student
                if(option == 1){
                    //display instructions
                    main.printRegMenu();

                    while (true){
                        //get inputs from user
                        System.out.print("\nFirst name: ");
                        firstName = main.input.next();

                        //check whether the first name includes more than 50 characters
                        try {
                            if (firstName.length() > 50){
                                throw new Exception("\nMax characters for first/last name allowed is 50\n");
                            }else {
                                break;
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }


                    while (true){
                        System.out.print("Last name: ");
                        lastName = main.input.next();

                        //check whether the last name includes more than 50 characters
                        try {
                            if (lastName.length() > 50){
                                throw new Exception("\nMax characters for first/last name allowed is 50\n");
                            }else {
                                break;
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    while (true){
                        System.out.print("Contact number: ");
                        contactNumber = main.input.next();

                        //check whether the contact includes more than 13 characters
                        try {
                            if (contactNumber.length() > 13){
                                throw new Exception("\nMax characters for contact number allowed is 13\n");
                            }else {
                                break;
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    while(true){
                        System.out.print("Date of birth(Format:yyyy-mm-dd ): ");
                        dateOfBirth = main.input.next();
                        //validate date
                        try {
                            if (menuFunc.isValidDate(dateOfBirth)){
                                break;
                            }
                            throw new Exception("\nInvalid date\n");
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    while (true){
                        System.out.print("Grade of study: ");
                        yearOfStudy = main.input.next();
                        //validate grade
                        try {
                            if (menuFunc.isValidGrade(yearOfStudy)){
                                break;
                            }
                            throw new Exception("\nInvalid grade.\n");
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    while(true){
                        System.out.println("Password conditions,\n" +
                                "- Password must be with in minimum length of 5 characters and maximum length of 20 characters.\n" +
                                "- Password must include at least one numeric digit (0-9).\n" +
                                "- Password must include at least one special character (e.g., !, @, #, $, %).\n" +
                                "- Password cannot include spaces");
                        System.out.print("Password: ");
                        password = main.input.next();
                        //validate the date
                        try {
                            if (menuFunc.isValidPassword(password)){
                                break;
                            }
                            throw new Exception("\nInvalid password.\n");
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    // create register obj
                    Register register = new Register();

                    //insert the userdetails to the database if user confirms
                    register.register(password, firstName, lastName, contactNumber, dateOfBirth, yearOfStudy);
                }

                // login (student/advisor)
                else if (option == 2) {
                    while(true){
                        //display instructions
                        main.printLoginMenu();
                        //create a scanner obj
                        Scanner usernameInput = new Scanner(System.in);
                        //get user input(username)
                        System.out.print("\nUsername: ");
                        username = usernameInput.nextLine();

                        //create a scanner obj
                        Scanner passwordInput = new Scanner(System.in);
                        //get user input(password)
                        System.out.print("Password: ");
                        password = passwordInput.nextLine();

                        try{
                            //get user type(advisor or student
                            userType = menuFunc.getUserType(username);

                            //advisor login
                            if (userType == UserType.ADVISOR){
                                //authenticate creds
                                Advisor loggedAdvisor = (Advisor) login.loginAdvisorAuth(username, password);
                                System.out.println("Welcome " + loggedAdvisor.getFirst_name() + "!");
                                break;
                            }

                            //student login
                            else if (userType == UserType.STUDENT) {
                                //authenticate creds
                                Student loggedStudent = (Student) login.loginStudentAuth(username, password);
                                System.out.println("Welcome " + loggedStudent.getFirst_name() + "!");
                                break;
                            }

                            //invalid user
                            else if (userType == UserType.INVALID) {
                                //System.out.println("Invalid username, Please try again");
                                throw new Exception("\nInvalid username, Please try again...\n");
                            }
                        }
                        catch (ClassCastException e){
                            System.out.println("Invalid credentials. Please try again..");
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                }
                //exit the program
                else if (option == 0) {
                    System.out.println("exiting...");
                    break;

                }
                //invalid user input given
                else {
                    //System.out.println("Invalid option. Please try again...");
                    throw new Exception("\nInvalid option. Please try again...\n");
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void printNewMenu(){
        String title = "Societas Futuis";
        System.out.println("\033[1m========== " + title +" ==========\033[0m");
        System.out.println("------------- Main Menu -------------");
        System.out.println("Please select an option\n" +
                "1 - Register\n" +
                "2 - Log In\n" +
                "0 - Quit\n");
    }

    public void printLoginMenu(){
        System.out.println("\n------------- Login -------------");
        System.out.print("Please enter your username and password to proceed,");
    }

    public void printRegMenu(){
        System.out.println("\n------------- Registration -------------");
        System.out.print("Please enter the following details,");
    }
}