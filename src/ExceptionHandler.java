import java.sql.SQLException;
import java.util.Scanner;

// ExceptionHandler class for handling exceptions
public class ExceptionHandler {

    /* Contribution - Haniffa, Haadiya 20211462/2237937 */

    public static void handleSQLException(SQLException e) {
        e.printStackTrace();
    }

    public static int getIntInput(Scanner scanner, String message) {
        int input = -1;
        while (true) {
            try {
                System.out.println(message);
                input = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.\n");
                scanner.nextLine(); // consume the invalid input
            }
        }
        return input;
    }

    public static String getStringInput(Scanner scanner, String message) {
        String input = "";
        while (true) {
            try {
                System.out.println(message);
                input = scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid string.\n");
            }
        }
        return input;
    }
}
