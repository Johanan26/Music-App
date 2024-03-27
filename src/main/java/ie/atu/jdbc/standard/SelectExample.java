package ie.atu.jdbc.standard;

import java.sql.*;

public class SelectExample {
    public static void main(String[] args) {
        // MySQL database connection details
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "password";

        // SQL statement
        String selectSQL = "SELECT artists.name, albums.album_name, songs.song_name " +
                "FROM songs " +
                "INNER JOIN albums  ON songs.album_id = albums.album_id " +
                "INNER JOIN artists  ON albums.artist_id = artists.artist_id "+
                "WHERE albums.album_id = 2";
/////
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String album = resultSet.getString("album_name");
                String song = resultSet.getString("song_name");

                System.out.println("Artist: " + name + ", Album: " + album + ", Songs: " + song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
