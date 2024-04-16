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

}