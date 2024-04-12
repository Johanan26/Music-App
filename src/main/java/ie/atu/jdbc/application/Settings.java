package ie.atu.jdbc.application;

import ie.atu.jdbc.pool.DatabaseUtils;

import java.sql.*;
import java.util.Scanner;

public class Settings{
   public static void updateName(Connection conn,String updatedName,String username) {
        try {
            // Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET name = ? WHERE username = ?");
            stmt.setString(1, updatedName);
            stmt.setString(2, username);
            stmt.executeUpdate();


            System.out.println("Updated Name..");
        } catch (SQLException ex) {

            System.out.println("Failed to update name");
            ex.printStackTrace();
        }


    }
    public static void updateUsername(Connection conn,String updatedUsername,String username) {
        try {
            // Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET username = ? WHERE username = ?");
            stmt.setString(1, updatedUsername);
            stmt.setString(2, username);
            stmt.executeUpdate();


            System.out.println("Updated Username..");
        } catch (SQLException ex) {

            System.out.println("Failed to update username");
            ex.printStackTrace();
        }
    }

    public static void updatePassword(Connection conn,String updatedPassword,String username) {
        try {
            // Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
            stmt.setString(1, updatedPassword);
            stmt.setString(2, username);
            stmt.executeUpdate();


            System.out.println("Updated Password..");
        } catch (SQLException ex) {

            System.out.println("Failed to update Password");
            ex.printStackTrace();
        }
    }
    public static void updateEmail(Connection conn,String updatedEmail,String username) {
        try {
            // Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET email = ? WHERE username = ?");
            stmt.setString(1, updatedEmail);
            stmt.setString(2, username);
            stmt.executeUpdate();


            System.out.println("Updated Email..");
        } catch (SQLException ex) {

            System.out.println("Failed to update Email");
            ex.printStackTrace();
        }
    }
    public static void updateSubscription_id(Connection conn,String updateSubscription_id,String username) {
        try {
            // Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET subscription_id = ? WHERE username = ?");
            stmt.setString(1, updateSubscription_id);
            stmt.setString(2, username);
            stmt.executeUpdate();


            System.out.println("Updated Subscription..");
        } catch (SQLException ex) {

            System.out.println("Failed to update Subscription");
            ex.printStackTrace();
        }
    }
    // Helper method to get the ID of the last inserted record
    private static int getLastInsertId (Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        stmt.close();
        return id;
    }
}
