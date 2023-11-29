import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/* Contribution - Haniffa, Haadiya 20211462/2237937 */

// AttendanceTracker class for managing menu and user choices
class AttendanceTracker {
    private Connection connection;
    private Scanner scanner;

    public AttendanceTracker(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void showMenu() {
        System.out.println("*** ATTENDANCE TRACKING ***\n");
        System.out.println("Which option of tracking attendance would you like to do?\n");
        System.out.println("1. Add Attendance");
        System.out.println("2. View Attendance");
        System.out.println("3. Exit\n");

        int choice = ExceptionHandler.getIntInput(scanner, "Enter your choice:");

        switch (choice) {
            case 1:
                // Create AddAttendance object and add attendance
                AddAttendance addAttendance = new AddAttendance(connection, scanner);
                addAttendance.addAttendance();
                break;
            case 2:
                // Create ViewAttendance object and view attendance
                ViewAttendance viewAttendance = new ViewAttendance(connection, scanner);
                viewAttendance.viewAttendance();
                break;
            case 3:
                // Close resources and exit
                scanner.close();
                try {
                    connection.close();
                } catch (SQLException e) {
                    ExceptionHandler.handleSQLException(e);
                }
                System.exit(0);
                System.out.println("Exiting Attendance Tracking");
                break;
            default:
                System.out.println("Invalid choice. Please try again.\n");
                break;
        }
    }
}