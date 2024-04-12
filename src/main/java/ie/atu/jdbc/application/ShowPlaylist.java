package ie.atu.jdbc.application;

import ie.atu.jdbc.pool.DatabaseUtils;
import java.sql.*;
import java.util.ArrayList;
public class ShowPlaylist {
    public static void main(String[] args){
        String username = "";
        ShowPlaylist(username);
    }
    public static void ShowPlaylist(String username) {
        String selectSQL = "SELECT user_playlist.playlist_name, users.name  " +
                "FROM user_playlist " +
                "JOIN users ON user_playlist.user_id = users.user_id " +
                "WHERE users.username= ?";

        ArrayList<String> playlists = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSQL)){
            statement.setString(1,username);

            ResultSet resultSetPlaylist = statement.executeQuery();
            while (resultSetPlaylist.next()) {
                String playlistName = resultSetPlaylist.getString("playlist_name");
                String userName = resultSetPlaylist.getString("name");
                playlists.add(playlistName + " - "+ username);
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