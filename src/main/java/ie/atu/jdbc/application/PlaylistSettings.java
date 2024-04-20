package ie.atu.jdbc.application;
import ie.atu.jdbc.pool.DatabaseUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class PlaylistSettings {
    public static void createPlaylist(Connection conn, Scanner scanner,int userId){
        try {
// Prompt the user to input data
            System.out.println("Name playlist:");
            String name = scanner.nextLine();
// Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_playlist(user_playlist_id,playlist_name, user_id) VALUES (?, ?, ?)");
            stmt.setInt(1, getLastInsertId(conn));
            stmt.setString(2, name);
            stmt.setString(3, String.valueOf(userId));
            stmt.executeUpdate();
            System.out.println("------------------------");
            System.out.println("Creating playlist...");
        } catch (SQLException ex) {
            System.out.println("Failed to create playlist!");
            ex.printStackTrace();
        }
    }
    //this way it creates another user after the last one in the database and doesn't start at id=0
    private static int getLastInsertId(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
// Retrieve the maximum value of the user_id column from the users table
            ResultSet rs = stmt.executeQuery("SELECT MAX(user_playlist_id) FROM user_playlist");
            rs.next();
            int maxId = rs.getInt(1);
// Increment the maximum value by 1 to get the next available user_id
            return maxId + 1;
        }
    }

}