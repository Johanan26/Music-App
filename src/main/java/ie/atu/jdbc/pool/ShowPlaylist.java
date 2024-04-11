package ie.atu.jdbc.pool;

import ie.atu.jdbc.pool.DatabaseUtils;
import java.sql.*;
import java.util.ArrayList;
public class ShowPlaylist {
    public static void main(String[] args) {
        String selectSQL = "SELECT user_playlist.playlist_name, users.name  " +
                "FROM user_playlist " +
                "JOIN users ON user_playlist.user_id = users.user_id " +
                "WHERE users.user_id = 20";
        ArrayList<String> playlists = new ArrayList<>();
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            while (resultSet.next()) {
                String playlistName = resultSet.getString("playlist_name");
                String userName = resultSet.getString("name");
                playlists.add(playlistName + " - "+ userName);
            }
            if(playlists.size()==0){
                System.out.println("No playlists\n");
            }
            else {
                System.out.println("Playlists:");
                for(String playlist : playlists){
                    System.out.println(playlist);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}