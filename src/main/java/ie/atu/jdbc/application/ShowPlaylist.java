package ie.atu.jdbc.application;

import ie.atu.jdbc.pool.DatabaseUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class ShowPlaylist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        ShowPlaylist(username,scanner);
    }

    public static void ShowPlaylist(String username, Scanner scanner) {
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
                System.out.println("Which playlist do you want to open:");

                int playlistChoice = Integer.parseInt(scanner.nextLine());

                //playlists is array list name
                if (playlistChoice > 0 && playlistChoice <= playlists.size()) {
                    String selectedPlaylistID = playlists.get(playlistChoice-1).split(" - ")[0];
                    showPlaylistSongs(selectedPlaylistID);
                } else {
                    System.out.println("Invalid Entry!!!");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showPlaylistSongs(String playlistID) {
        String selectSongsSQL = "SELECT songs.song_name " +
                "FROM user_playlist_songs " +
                "JOIN songs ON user_playlist_songs.song_id = songs.song_id " +
                "WHERE user_playlist_songs.user_playlist_id = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSongsSQL)) {
            statement.setString(1, playlistID);

            ResultSet resultSetSongs = statement.executeQuery();

            System.out.println("Songs in the playlist:");
            while (resultSetSongs.next()) {
                String songName = resultSetSongs.getString("song_name");
                System.out.println("- " + songName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}