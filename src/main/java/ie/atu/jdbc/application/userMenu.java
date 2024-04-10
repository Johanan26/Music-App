package ie.atu.jdbc.application;
import java.sql.*;
import java.util.Scanner;
public class userMenu {
    public static void main(String[] args) throws SQLException {
        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "password");
        Scanner scanner = new Scanner(System.in);
        char log;
        System.out.println("Log in or Sign up (L or S):");
        log = scanner.nextLine().charAt(0);
        //if a user clicks s, they can sign up and create an account.
        if (log == 'S' || log == 's') {
            try {
                // Prompt the user to input data
                System.out.println("Enter name:");
                String name = scanner.nextLine();
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter email:");
                String email = scanner.nextLine();
                System.out.println("Enter subscription_id:");
                String subscriptionId = scanner.nextLine();
                System.out.println("Enter gender:");
                String gender = scanner.nextLine();
                System.out.println("Enter country:");
                String country = scanner.nextLine();
                // Insert a new record into the "users" table
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(user_id,name, username, email, subscription_id, gender, country) VALUES (?, ?, ?, ?, ?, ?, ?)");
                stmt.setInt(1, getLastInsertId(conn));
                stmt.setString(2, name);
                stmt.setString(3, username);
                stmt.setString(4, email);
                stmt.setString(5, subscriptionId);
                stmt.setString(6, gender);
                stmt.setString(7, country);
                stmt.executeUpdate();
                System.out.println("Creating user...");
            } catch (SQLException ex) {
                System.out.println("Failed to create user!");
                ex.printStackTrace();
            }
        }
        //when a user enters L, they enter their username and password.
        //if it's in database, login is successful
        else if(log == 'L' || log== 'l'){
            try {
                // Prompt the user to input data
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();
                // Check if the provided credentials exist in the database
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Login successful.");
                } else {
                    System.out.println("Invalid username or password.");
                }
            } catch (SQLException ex) {
                System.out.println("Login failed.");
                ex.printStackTrace();
            }
        }
        //if a user does not enter L or S when asked
        else{
            System.out.println("Invalid option. 'L' for login, or 'S' for sign up.");
        }
    }

    //increments the user_id for signing up
    //this way it creates another user after the last one in the database and doesn't start at id=0
    private static int getLastInsertId(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Retrieve the maximum value of the user_id column from the users table
            ResultSet rs = stmt.executeQuery("SELECT MAX(user_id) FROM users");
            rs.next();
            int maxId = rs.getInt(1);
            // Increment the maximum value by 1 to get the next available user_id
            return maxId + 1;
        }
    }
}