import java.util.Scanner;

public class Main {
    private Scanner input = new Scanner(System.in);
    private Scanner usernameInput = new Scanner(System.in);
    private Scanner passwordInput = new Scanner(System.in);
    private Student student = new Student();

    public static void main(String[] args) {
        //create login obj
        Login login = new Login();
        //create main obj
        Main main = new Main();
        //create menuFunc obj
        MenuFunc menuFunc = new MenuFunc();

        //define variables
        int option;
        String username, password, userType, firstName, lastName, contactNumber, dateOfBirth, yearOfStudy;

        //loop main menu
        while(true){
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

                //get inputs from user
                System.out.print("\nFirst name: ");
                firstName = main.input.next();

                System.out.print("Last name: ");
                lastName = main.input.next();

                System.out.print("Contact number: ");
                contactNumber = main.input.next();

                while(true){
                    System.out.print("Date of birth(Format:yyyy-mm-dd ): ");
                    dateOfBirth = main.input.next();
                    //validate date
                    if (menuFunc.isValidDate(dateOfBirth)){
                        break;
                    }
                    System.out.println("Invalid date. Please try again\n");
                }


                while (true){
                    System.out.print("Grade of study: ");
                    yearOfStudy = main.input.next();
                    //validate grade
                    if (menuFunc.isValidGrade(yearOfStudy)){
                        break;
                    }
                    System.out.println("Invalid grade. Please try again\n");
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
                    if (menuFunc.isValidPassword(password)){
                        break;
                    }
                    System.out.println("Invalid password. Please try again\n");
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
                        if (userType.equals("advisor")){
                            //authenticate creds
                            Advisor loggedAdvisor = (Advisor) login.loginAdvisorAuth(username, password);
                            System.out.println("Welcome " + loggedAdvisor.getFirst_name() + "!");
                            break;
                        }

                        //student login
                        else if (userType.equals("student")) {
                            System.out.println("student");
                        }

                        //invalid user
                        else if (userType.equals("invalid")) {
                            System.out.println("Invalid username, Please try again");
                        }
                    }
                    catch (ClassCastException e){
                        System.out.println("Invalid credentials. Please try again..");
                    }
                    catch (Exception e){
                        System.out.println(e);
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
                System.out.println("Invalid option. Please try again...");
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