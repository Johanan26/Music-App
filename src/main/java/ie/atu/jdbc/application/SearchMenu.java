package ie.atu.jdbc.application;

import ie.atu.jdbc.pool.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchMenu implements Search {
    @Override
    public ArrayList<String> searchSong(Connection conn, Scanner scanner, String userSearch) throws SQLException {
        ArrayList<String> results = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT songs.song_id, songs.song_name, artists.name " +
                "FROM songs " +
                "JOIN artists ON songs.artist_id = artists.artist_id " +
                "WHERE (songs.song_name LIKE ? OR artists.name LIKE ?)");
        stmt.setString(1, "%" + userSearch + "%");
        stmt.setString(2, "%" + userSearch + "%");

        ResultSet resultSet = stmt.executeQuery();
        int index = 1;

        while (resultSet.next()) {
            int songId = resultSet.getInt("song_id");
            String songName = resultSet.getString("song_name");
            String artistName = resultSet.getString("name");
            String indexedResult = index + ". " + songName + " - " + artistName;
            results.add(indexedResult);
            index++;
        }

        return results;
    }


    public void addSongToLikes(Connection conn, int userId, String selectedSong) throws SQLException {
        String[] parts = selectedSong.split(" - "); // Split the selected song into song name and artist
        String songName = parts[0].substring(parts[0].indexOf(". ") + 2); // Extract song name
        System.out.println("Selected Song Name: " + songName);

        PreparedStatement stmt = conn.prepareStatement("SELECT song_id FROM songs WHERE song_name = ?");
        stmt.setString(1, songName);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int songId = rs.getInt("song_id");
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO USERLIKES (user_id, song_id) VALUES (?, ?)");
            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, songId);
            insertStmt.executeUpdate();
            System.out.println("Song added to your liked songs.");
        } else {
            System.out.println("Failed to add song to liked songs. Song not found.");
        }
    }


    public static void SelectPlaylist(String username,String selectedSong, Scanner scanner) {
        String selectSQL = "SELECT user_playlist.user_playlist_id, user_playlist.playlist_name, users.name " +
                "FROM user_playlist " +
                "JOIN users ON user_playlist.user_id = users.user_id " +
                "WHERE users.username= ?";

        ArrayList<String> playlists = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSQL)) {
            statement.setString(1, username);

            ResultSet resultSetPlaylist = statement.executeQuery();
            int playlistNum = 0;
            while (resultSetPlaylist.next()) {
                playlistNum++;
                String playlistID = resultSetPlaylist.getString("user_playlist_id");
                String playlistName = resultSetPlaylist.getString("playlist_name");
                playlists.add(playlistID + " - " + playlistName);
                System.out.println(playlistNum + ". " + playlistName);
            }

            if (playlists.size() == 0) {
                System.out.println("You have no playlists!");

            } else {
                System.out.println("Which playlist do you want to add song to?");

                int playlistChoice = Integer.parseInt(scanner.nextLine());

                //playlists is array list name
                if (playlistChoice > 0 && playlistChoice <= playlists.size()) {
                    String selectedPlaylistID = playlists.get(playlistChoice-1).split(" - ")[0];
                    addSongToPlaylist(connection, Integer.parseInt(selectedPlaylistID),selectedSong);
                } else {
                    System.out.println("Invalid Entry!!!");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addSongToPlaylist(Connection conn, int playlistID, String selectedSong) throws SQLException {
        String[] parts = selectedSong.split(" - "); // Split the selected song into song name and artist
        String songName = parts[0].substring(parts[0].indexOf(". ") + 2); // Extract song name
        System.out.println("Selected Song Name: " + songName);

        PreparedStatement stmt = conn.prepareStatement("SELECT song_id FROM songs WHERE song_name = ?");
        stmt.setString(1, songName);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int songId = rs.getInt("song_id");
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO user_playlist_songs(user_playlist_id, song_id) VALUES (?, ?)");
            insertStmt.setInt(1, playlistID);
            insertStmt.setInt(2, songId);
            insertStmt.executeUpdate();
            System.out.println("Song added to your Playlist");
        } else {
            System.out.println("Failed to add song to liked songs. Song not found.");
        }
    }

}