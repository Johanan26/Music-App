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
                    showPlaylistSongs(selectedPlaylistID,scanner);
                } else {
                    System.out.println("Invalid Entry!!!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showPlaylistSongs(String playlistID, Scanner scanner) {
        String selectSongsSQL = "SELECT songs.song_name, artists.name " +
                "FROM user_playlist_songs " +
                "JOIN songs ON user_playlist_songs.song_id = songs.song_id " +
                "JOIN artists ON songs.artist_id = artists.artist_id " +
                "WHERE user_playlist_songs.user_playlist_id = ?";
        ArrayList<String> playlistSongs = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSongsSQL)) {
            statement.setString(1, playlistID);
            ResultSet resultSetSongs = statement.executeQuery();
            while (resultSetSongs.next()) {
                String songName = resultSetSongs.getString("song_name");
                String artistName = resultSetSongs.getString("name");
                playlistSongs.add("> "+songName + " - "+ artistName);
            }
            if(playlistSongs.size()==0){
                System.out.println("No songs in playlist");
            }else{
                System.out.println("Songs in the playlist:");
                for(String playlistSong : playlistSongs){
                    System.out.println(playlistSong);
                }
            }
            System.out.println("\nEdit playlist (E) | Back to Menu (any key) ");
            char option = scanner.nextLine().charAt(0);
            if( option == 'E' || option == 'e'){
                EditPlaylist(playlistID, scanner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void EditPlaylist(String playlistID, Scanner scanner) {
        System.out.println("Edit Mode ");
        System.out.println("1. Edit playlist Name\n2. add song maybe\n3. remove song\n4. delete playlist\n5. exit");
        int option =scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                ShowPlaylist.updateName(playlistID,scanner);
                break;
            case 2:
                break;
            case 3:
                removeSong(playlistID, scanner);
                break;
            case 4:
                deletePlaylist(playlistID,scanner);
                break;
            case 5:
                System.out.println("exiting edit mode!");
                return;
        }
    }
    public static void updateName(String playlistID, Scanner scanner){
// Prompt the user to input data
        System.out.println("Name playlist:");
        String name = scanner.nextLine();
        String updateSQL="UPDATE user_playlist SET playlist_name =? WHERE user_playlist_id =?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateSQL)){
            statement.setString(1,name);
            statement.setString(2,playlistID);
            int resultSetLiked = statement.executeUpdate();
            if (resultSetLiked > 0) {
                System.out.println("Playlist name updated .");
            } else {
                System.out.println("Failed to update playlist name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void removeSong(String playlistID, Scanner scanner){
        String selectSongsSQL = "SELECT songs.song_id, songs.song_name, artists.name " +
                "FROM user_playlist_songs " +
                "JOIN songs ON user_playlist_songs.song_id = songs.song_id " +
                "JOIN artists ON songs.artist_id = artists.artist_id " +
                "WHERE user_playlist_songs.user_playlist_id = ?";

        ArrayList<String> playlistSongs = new ArrayList<>();
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSongsSQL)) {
            statement.setString(1, playlistID);
            ResultSet resultSetSongs = statement.executeQuery();

            while (resultSetSongs.next()) {
                String songName = resultSetSongs.getString("song_name");
                String artistName = resultSetSongs.getString("name");
                playlistSongs.add(songName + " - " + artistName);
            }
            if (playlistSongs.size() == 0) {
                System.out.println("No songs in playlist");
            } else {
                System.out.println("Playlist songs:");
                for (int i = 0; i < playlistSongs.size();i++) {
                    System.out.println((i+1)+". "+playlistSongs.get(i));
                }
                System.out.println("Which song do you want to remove?(any number key to exit)");

                int songChoice = scanner.nextInt();
                scanner.nextLine();
                if (songChoice>0 && songChoice <= playlistSongs.size()) {
                    String selectedSong = playlistSongs.get(songChoice-1);
                    String songName = selectedSong.split(" - ") [0];
                    String deleteSongSQL = "DELETE FROM user_playlist_songs "+
                            "WHERE user_playlist_id =? "+
                            "AND song_id = (SELECT song_id FROM songs WHERE song_name =?)";
                    try (PreparedStatement deleteSongStatement = connection.prepareStatement(deleteSongSQL)){
                        deleteSongStatement.setString(1,playlistID);
                        deleteSongStatement.setString(2,songName);
                        int deletedSong = deleteSongStatement.executeUpdate();
                        if (deletedSong > 0) {
                            System.out.println("Song removed");
                        } else {
                            System.out.println("Failed to delete playlist song");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deletePlaylist(String playlistID, Scanner scanner) {
        String deleteSongsSQL = "DELETE FROM user_playlist_songs WHERE user_playlist_id =?";
        String deletePlaylistSQL = "DELETE FROM user_playlist WHERE user_playlist_id =?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement deleteSongsStatement = connection.prepareStatement(deleteSongsSQL);
             PreparedStatement deletePlaylistStatement = connection.prepareStatement(deletePlaylistSQL)){
            deleteSongsStatement.setString(1,playlistID);
            int deletedSongs = deleteSongsStatement.executeUpdate();
            deletePlaylistStatement.setString(1,playlistID);
            int deletedPlaylist =deletePlaylistStatement.executeUpdate();
            if (deletedPlaylist > 0) {
                System.out.println("Playlist Deleted");
            } else {
                System.out.println("Failed to delete playlist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static int getPlaylistID(String playlistID) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "password");
        PreparedStatement stmt = conn.prepareStatement("SELECT user_playlist_id FROM user_playlist WHERE user_id = ?");
        stmt.setString(1, playlistID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("user_playlist_id");
        }
        return -1; // Return -1 if user not found
    }
}