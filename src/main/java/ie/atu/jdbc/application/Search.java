import ie.atu.jdbc.pool.DatabaseUtils;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

public class Search {

    public void searchSongs(String songName) {

        String searchSongs = "SELECT song_name FROM songs WHERE song_name = ?";

        try (Connection connection = DatabaseUtils.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(searchSongs)) {

            preparedStatement.setString(1, songName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    String song = resultSet.getString("song_name");

                    System.out.println(song + " - ");

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public void searchAlbums(String albumName) {

        String searchAlbums = "SELECT album_name FROM Albums WHERE album_name = ?";

        try (Connection connection = DatabaseUtils.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(searchAlbums)) {

            preparedStatement.setString(1, albumName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    String album = resultSet.getString("album_name");

                    System.out.println(album + " - ");

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public void searchArtist(String artistName) {

        String searchArtist = "SELECT artist_name FROM Artist WHERE artist_name = ?";

        try (Connection connection = DatabaseUtils.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(searchArtist)) {

            preparedStatement.setString(1, artistName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    String artist = resultSet.getString("artist_name");

                    System.out.println(artist + " - ");

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
