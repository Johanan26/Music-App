package ie.atu.jdbc.application;

import ie.atu.jdbc.pool.DatabaseUtils;

import java.sql.*;

public class Home {
    public void showSongs() {
        String showSongs = "SELECT songs.song_name, artists.name " +
                "FROM songs " +
                "JOIN artists on songs.artist_id = artists.artist_id " +
                "ORDER BY RAND() " +
                "LIMIT 5";

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(showSongs)) {

            while (resultSet.next()) {
                String song = resultSet.getString("song_name");
                String artist = resultSet.getString("name");

                System.out.println(song + " - " + artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void showAlbums() {
        String showAlbums = "SELECT artists.name, albums.album_name " +
                "FROM albums " +
                "INNER JOIN artists  ON albums.artist_id = artists.artist_id "+
                "ORDER BY RAND() " +
                "LIMIT 2";

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(showAlbums)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String album = resultSet.getString("album_name");

                System.out.println("Artist: " + name + ", Album: " + album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
