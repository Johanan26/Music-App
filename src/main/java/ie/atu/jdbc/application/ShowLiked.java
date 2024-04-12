package ie.atu.jdbc.application;


import ie.atu.jdbc.pool.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;

public class ShowLiked {
    public static void main(String[] args){
        String username = "";
        showLikedSongs(username);
    }
    public static void showLikedSongs(String username) {
        String selectSQL = "SELECT songs.song_name, artists.name " +
                "FROM songs " +
                "JOIN UserLikes ON songs.song_id = UserLikes.song_id " +
                "JOIN users ON UserLikes.user_id = users.user_id " +
                "JOIN artists ON songs.artist_id = artists.artist_id " +
                "WHERE users.username = ?";
        ArrayList<String> likedSongs = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSQL)){
             statement.setString(1,username);
             ResultSet resultSetLiked = statement.executeQuery();

            while (resultSetLiked.next()) {
                String songName = resultSetLiked.getString("song_name");
                String artistName = resultSetLiked.getString("name");
                likedSongs.add(songName + " - "+ artistName);
            }

        if(likedSongs.size()==0){
            System.out.println("No liked songs\n");
        }
        else {
            System.out.println("Liked Songs:");
            for(String likedSong : likedSongs){
                System.out.println(likedSong);

            }
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

