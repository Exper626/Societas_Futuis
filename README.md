# Societas_Futuis
Object-oriented development project.

## Prerequisites

- Java Development Kit (JDK) installed on your machine.
- A local database server (e.g., MySQL) installed.

## Setup

1. **Database Setup:**

   - Locate the `database` folder in the project directory.
   - Import the provided SQL script (`database_setup.sql`) into your database server to create the necessary tables.

2. **Configure Database Connection:**

   - Open the `sqlConnection` class in your Java project.
   - Find the database connection URL, username, and password settings.
   - Modify the following variables according to your local database configuration:

     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
     private static final String USER = "your_username";
     private static final String PASSWORD = "your_password";
     ```

## Running the Program

- Compile and run (Main) your Java program using your preferred IDE or build tool.

UML diagrams were designed. Documenting was done parallely
Each student took responsibility over each function.
Only 2 contributors could access.
others sent codes via discord
merged and uploaded
