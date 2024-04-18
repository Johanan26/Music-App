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
            System.out.println("------------------------");
// Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_playlist(user_playlist_id,playlist_name, user_id) VALUES (?, ?, ?)");
            stmt.setInt(1, getLastInsertId(conn));
            stmt.setString(2, name);
            stmt.setString(3, String.valueOf(userId));
            stmt.executeUpdate();
            System.out.println("------------------------");
            System.out.println("Creating playlisy...");
        } catch (SQLException ex) {
            System.out.println("Failed to create playlist!");
            ex.printStackTrace();
        }
    }
    public static void deletePlaylist(Connection conn,String username, Scanner scanner) {
        String selectSQL = "SELECT user_playlist.user_playlist_id, user_playlist.playlist_name, users.name " +
                "FROM user_playlist " +
                "JOIN users ON user_playlist.user_id = users.user_id " +
                "WHERE users.username= ?";
        ArrayList<String> playlists = new ArrayList<>();
        int playlistIDs = 0;
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSQL)) {
            statement.setString(1, username);
            ResultSet resultSetPlaylist = statement.executeQuery();
            int playlistNum = 0;
            while (resultSetPlaylist.next()) {
                playlistNum++;
                playlistIDs++;
                String playlistID = resultSetPlaylist.getString("user_playlist_id");
                String playlistName = resultSetPlaylist.getString("playlist_name");
                playlists.add(playlistID + " - " + playlistName);
                System.out.println(playlistNum + ". " + playlistName);
            }
            if (playlists.size() == 0) {
                System.out.println("You have no playlists!");
            } else {
                System.out.println("Which playlist do you want to delete?");
                int playlistChoice = Integer.parseInt(scanner.nextLine());
//playlists is array list name
                if (playlistChoice > 0 && playlistChoice <= playlists.size()) {
                    int selectedPlaylistID = resultSetPlaylist.getInt("user_playlist_id");

// elete the user_id from all tables one at tiem
                    PreparedStatement deletePlaylistSongs = conn.prepareStatement("DELETE FROM user_playlist_songs WHERE user_playlist_id = ?");
                    deletePlaylistSongs.setInt(1, selectedPlaylistID);
                    deletePlaylistSongs.executeUpdate();
                    PreparedStatement deletePlaylist = conn.prepareStatement("DELETE FROM user_playlist WHERE user_playlist_id = ?");
                    deletePlaylist.setInt(1, selectedPlaylistID);
                    deletePlaylist.executeUpdate();

                } else {
                    System.out.println("Invalid Entry!!!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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