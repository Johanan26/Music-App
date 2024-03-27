package ie.atu.jdbc.standard;
import java.sql.*;

public class InsertTransactionExample {

    public static void main(String[] args) throws SQLException {

        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "password");

        try {
            // Set auto-commit to false to start a transaction
            conn.setAutoCommit(false);

            // Insert a new record into the "artists" table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO artists (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, "Arctic Monkeys");
            stmt.executeUpdate();

            // Retrieve the generated key for the new artist record
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int artistId = -1;
            if (generatedKeys.next()) {
                artistId = generatedKeys.getInt(1);
                System.out.println("Last inserted artist ID: " + artistId);
            }

            // Insert a new record into the "feat_artist" table, referencing the new artist
            stmt = conn.prepareStatement("INSERT INTO feat_artist (feat_id, artist_id) VALUES (?, ?)");
            stmt.setInt(1, artistId); // Use the artist ID as feat_id
            stmt.setInt(2, artistId); // Use the artist ID as artist_id
            stmt.executeUpdate();

            // Commit the transaction
            conn.commit();

            System.out.println("Transaction completed successfully.");
        } catch (SQLException ex) {
            // If there is an error, rollback the transaction
            conn.rollback();

            System.out.println("Transaction failed.");
            ex.printStackTrace();
        } finally {
            // Set auto-commit back to true to end the transaction
            conn.setAutoCommit(true);
            // Close the connection
            conn.close();
        }
    }
}