import java.sql.*;
import java.util.Scanner;

// AddAttendance class for adding attendance
public class AddAttendance {

    /*Contribution - Haniffa, Haadiya 20211462/2237937*/

    private Connection connection;
    private Scanner scanner;

    public AddAttendance(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addAttendance() {
        try {
            scanner.nextLine();
            String eventId = ExceptionHandler.getStringInput(scanner, "Enter Event ID:");

            String studentId = ExceptionHandler.getStringInput(scanner, "Enter Student ID:\n");

            try {
                // Check for duplicate student ID
                if (isDuplicateStudentId(eventId, studentId)) {
                    System.out.println("Duplicate student ID. Please enter a unique student ID.\n");
                    return;
                }

                int attended = ExceptionHandler.getIntInput(scanner, "Did the student attend? (1 for Yes, 0 for No):");

                // Insert data into the attendance table
                String query = "INSERT INTO student_event (student_id, event_id, attendance) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, studentId);
                    preparedStatement.setString(2, eventId);
                    preparedStatement.setInt(3, attended);
                    preparedStatement.executeUpdate();
                    System.out.println("\nAttendance added successfully!");

                    // Ask the user if they want to add another student or go back to the menu
                    askForAnotherOperation();
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("\nError: Duplicate entry for Student ID. Please enter a unique Student ID.");
            }
        } catch (SQLException e) {
            ExceptionHandler.handleSQLException(e);
        }
    }

    // Check if there is a duplicate student ID for the given event and club
    private boolean isDuplicateStudentId(String eventId,  String studentId) throws SQLException {
        String query = "SELECT COUNT(*) FROM student_event WHERE student_id = ? AND event_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    // Ask the user if they want to add another student or go back to the menu
    private void askForAnotherOperation() {
        int choice = ExceptionHandler.getIntInput(scanner, "\nDo you want to add another student? (1 for Yes, 0 for No):");

        if (choice == 0) {
            // Go back to the menu
            return;
        } else if (choice == 1) {
            // Continue adding students
            addAttendance();
        } else {
            System.out.println("\nInvalid choice. Returning to the menu.");
        }
    }
}

