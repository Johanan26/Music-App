package ie.atu.jdbc.pool;

import ie.atu.jdbc.pool.DatabaseUtils;

import java.sql.*;

public class UpdateExample {
    public static void main(String[] args) {
        //String updateSQL = "UPDATE users SET name = 'David Jayakumar' WHERE user_id = 1";
        //String updateSQL = "UPDATE users SET Parental_controls = 'yes' WHERE user_id = 20";
        //String updateSQL = "UPDATE users SET  marketing='no', subscription_id=3 WHERE user_id = 20";
        //String updateSQL = "UPDATE songs SET  album_id=2 WHERE song_id = 46";
        String updateSQL = "UPDATE users SET  marketing='no', subscription_id=3 WHERE user_id = 20";

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement()) {
            int rowsUpdated = statement.executeUpdate(updateSQL);
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
