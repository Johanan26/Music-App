package ie.atu.jdbc.pool;


import ie.atu.jdbc.pool.DatabaseUtils;

import java.sql.*;

public class SelectExample {
    public static void main(String[] args) {
        String selectSQL = "SELECT songs.song_name, artists.name " +
                "FROM songs " +
                "JOIN artists on songs.artist_id = artists.artist_id " +
                "WHERE songs.feat_id >=0";

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String song = resultSet.getString("song_name");
                String artist = resultSet.getString("name");

                System.out.println("Song: " + song + ", Artist: " + artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}