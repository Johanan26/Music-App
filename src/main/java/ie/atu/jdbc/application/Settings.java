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
