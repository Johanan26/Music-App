package ie.atu.jdbc.application;
//reference code lab 9

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class SignUp extends User {
    public SignUp(String name, String username, String email,String password, String subscriptionId, String gender, String country) {
        super(name, username, email,password, subscriptionId, gender, country);
    }

    // This method handles the sign-up process
    public static void signUp(Connection conn, Scanner scanner){
        ArrayList<String> details = new ArrayList<String>();

        try {
            // Prompt the user to input data
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            details.add("Name:"+name);

            System.out.println("Enter username:");
            String username = scanner.nextLine();
            details.add("Username:"+username);

            System.out.println("Enter email:");
            String email = scanner.nextLine();
            details.add("Email:"+email);

            System.out.println("Enter password:");
            String password = scanner.nextLine();
            details.add("Password:"+password);

            System.out.println("Enter subscription_id:");
            System.out.println("--------------------------");
            System.out.println("ID\t|\tType\t|\tPrice\t");
            System.out.println("--------------------------");
            System.out.println("1\t|\tFree\t|\t€0\t");
            System.out.println("2\t|\tStudent\t|\t€5.99\t");
            System.out.println("3\t|\tSolo\t|\t€10.99\t");
            System.out.println("4\t|\tDuo\t\t|\t€14.99\t");
            System.out.println("5\t|\tFamily\t|\t€17.99\t");

            String subscriptionId = scanner.nextLine();
            details.add("Subscription:"+subscriptionId);

            System.out.println("Enter gender:");
            String gender = scanner.nextLine();
            details.add("Gender:"+ gender);

            System.out.println("Enter country:");
            String country = scanner.nextLine();
            details.add("Country:"+ country);

            System.out.println("------------------------");

            for(String detail : details){
                System.out.println(detail);

            }

            // Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(user_id,name, username, email,password, subscription_id, gender, country) VALUES (?, ?, ?,?, ?, ?, ?, ?)");
            stmt.setInt(1, getLastInsertId(conn));
            stmt.setString(2, name);
            stmt.setString(3, username);
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.setString(6, subscriptionId);
            stmt.setString(7, gender);
            stmt.setString(8, country);
            stmt.executeUpdate();
            System.out.println("------------------------");
            System.out.println("Creating user...");

        } catch (SQLException ex) {
            System.out.println("Failed to create user!");
            ex.printStackTrace();
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
