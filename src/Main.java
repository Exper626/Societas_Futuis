import java.util.Scanner;

public class Main {
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        int option;

        while(true){
            main.printNewMenu();
            System.out.print("Your option: ");
            option = main.input.nextInt();

            if(option == 1){
                System.out.println("register");
                break;
            }
            else if (option == 2) {
                System.out.println("login");
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
}