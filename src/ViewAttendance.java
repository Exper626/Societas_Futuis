import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// ViewAttendance class for viewing attendance
public class ViewAttendance {

    /* Contribution - Haniffa, Haadiya 20211462/2237937 */

    private Connection connection;
    private Scanner scanner;

    public ViewAttendance(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void viewAttendance() {
        try {
            scanner.nextLine();
            String eventId = ExceptionHandler.getStringInput(scanner, "Enter Event ID:");

            // Retrieve data from the attendance table
            String query = "SELECT student_id FROM student_event WHERE event_id = ? AND attendance = 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, eventId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("\nStudents who attended the event:");
                        do {
                            String studentId = resultSet.getString("student_id");
                            System.out.println("Student ID: " + studentId);
                        } while (resultSet.next());
                    } else {
                        // Check if the club and event ID combination exists
                        if (!isClubEventCombinationExists(eventId)) {
                            System.out.println("\nNo records found for the specified Club ID and Event ID combination.");
                        } else {
                            System.out.println("\nNo students attended the event.");
                        }
                    }
                }
            }

            // Ask the user if they want to view attendance for another event or go back to the menu
            askForAnotherOperation();
        } catch (SQLException e) {
            ExceptionHandler.handleSQLException(e);
        }
    }

    // Check if the specified Club ID and Event ID combination exists
    private boolean isClubEventCombinationExists(String eventId) throws SQLException {
        String query = "SELECT COUNT(*) FROM student_event WHERE event_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    // Ask the user if they want to view attendance for another event or go back to the menu
    private void askForAnotherOperation() {
        int choice = ExceptionHandler.getIntInput(scanner, "\nDo you want to view attendance for another event? (1 for Yes, 0 for No):");

        if (choice == 0) {
            // Go back to the menu
            return;
        } else if (choice == 1) {
            // Continue viewing attendance
            viewAttendance();
        } else {
            System.out.println("\nInvalid choice. Returning to the menu.");
        }
    }
}

