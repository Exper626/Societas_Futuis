import java.util.Scanner;

public class Main {
    private Scanner input = new Scanner(System.in);
    private Scanner usernameInput = new Scanner(System.in);
    private Scanner passwordInput = new Scanner(System.in);

    public static void main(String[] args) {
        Login login = new Login();
        Main main = new Main();
        int option;
        String username,password, userType;

        while(true){
            main.printNewMenu();
            System.out.print("Your option: ");
            option = main.input.nextInt();

            if(option == 1){
                System.out.println("register");
                break;
            }
            else if (option == 2) {
                while(true){
                    main.printLoginMenu();
                    Scanner usernameInput = new Scanner(System.in);
                    System.out.print("\nUsername: ");
                    username = usernameInput.nextLine();

                    Scanner passwordInput = new Scanner(System.in);
                    System.out.print("Password: ");
                    password = passwordInput.nextLine();
                    try{
                        userType = String.valueOf(username.charAt(0));
                        if (userType.equals("a")){
                            Advisor loggedAdvisor = (Advisor) login.loginAuth(username, password);
                            break;
                        }
                    }
                    catch (ClassCastException e){
                        System.out.println("Invalid credentials. Please try again..");
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }

                }
                System.out.println("hiii");
                break;

            }
            else if (option == 0) {
                System.out.println("exiting...");
                break;

            }
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
}