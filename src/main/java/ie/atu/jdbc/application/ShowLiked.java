package ie.atu.jdbc.application;


import ie.atu.jdbc.pool.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowLiked {
    public static void main(String[] args){
        String username = "";
        Scanner scanner = new Scanner(System.in);
        showLikedSongs(username, scanner);
    }
    public static void showLikedSongs(String username, Scanner scanner) {
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
            System.out.println("\nEdit Liked Songs (E) | Back to Menu (any key) ");
            char option = scanner.nextLine().charAt(0);

            if( option == 'E' || option == 'e'){
                EditLiked(username, scanner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void EditLiked(String username, Scanner scanner) {
        System.out.println("Edit Mode ");
        System.out.println("1. Add song\n2. Remove\n3. exit\n");
        int option =scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                ShowLiked.addSong(username,scanner);
                break;
            case 2:
                ShowLiked.removeLikedSong(username,scanner);

                break;
            case 3:
                System.out.println("");
                break;
            default:
                System.out.println("Invalid entry");
                break;
        }
    }
    public static void removeLikedSong(String username, Scanner scanner){
        String selectSongsSQL = "SELECT songs.song_id,songs.song_name, artists.name " +
                "FROM songs " +
                "JOIN UserLikes ON songs.song_id = UserLikes.song_id " +
                "JOIN users ON UserLikes.user_id = users.user_id " +
                "JOIN artists ON songs.artist_id = artists.artist_id " +
                "WHERE users.username = ?";

        ArrayList<Integer> songIds = new ArrayList<>();
        ArrayList<String> likedSongs = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSongsSQL)) {
            statement.setString(1, username);
            ResultSet resultSetSongs = statement.executeQuery();
            while (resultSetSongs.next()) {
                int songID = resultSetSongs.getInt("song_id");
                String songName = resultSetSongs.getString("song_name");
                String artistName = resultSetSongs.getString("name");
                likedSongs.add(songName + " - " + artistName);
                songIds.add(songID);
            }
            if (likedSongs.size() == 0) {
                System.out.println("No songs in playlist");
            } else {
                System.out.println("Liked songs:");
                for (int i = 0; i < likedSongs.size();i++) {
                    System.out.println((i+1)+". "+likedSongs.get(i));
                }
                System.out.println("Which song do you want to remove?(any key to exit)");

                int songChoice = scanner.nextInt();
                scanner.nextLine();
                if (songChoice > 0 && songChoice <= likedSongs.size()) {
                    int selectedSongId = songIds.get(songChoice - 1);

                    String deleteSongSQL = "DELETE FROM userlikes " +
                            "WHERE user_id = ? " +
                            "AND song_id = ?";
                    try (PreparedStatement deleteSongStatement = connection.prepareStatement(deleteSongSQL)) {
                        int userId = getUserId(username);
                        if(userId != -1){
                            deleteSongStatement.setInt(1, userId);
                            deleteSongStatement.setString(2, String.valueOf(selectedSongId));
                            int deletedSong = deleteSongStatement.executeUpdate();

                            if (deletedSong > 0) {
                                System.out.println("Song removed");
                            } else {
                                System.out.println("Failed to remove limed song");
                            }
                        }
                        else{System.out.println("User not found");
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

    public static void addSong(String username, Scanner scanner){
        System.out.println("What song would you like to add? ");
        String userSearch = scanner.nextLine();


        String searchSong = "SELECT songs.song_id, songs.song_name, artists.name " +
                "FROM songs " +
                "JOIN artists ON songs.artist_id = artists.artist_id " +
                "WHERE (songs.song_name LIKE ? OR artists.name LIKE ?)";

        ArrayList<Integer> songIds = new ArrayList<>();
        ArrayList<String> results = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(searchSong)) {

            statement.setString(1, "%" + userSearch + "%");
            statement.setString(2, "%" + userSearch + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int songID = resultSet.getInt("song_id");
                String songName = resultSet.getString("song_name");
                String artistName = resultSet.getString("name");
                results.add(songName + " - " + artistName);
                songIds.add(songID);
            }
            if (results.size()==0) {
                System.out.println("No Results");
            } else {
                System.out.println("Results:");
                for (int i = 0; i < results.size();i++) {
                    System.out.println((i+1)+". "+results.get(i));
                }
                System.out.println("Select song to add to liked songs");

                int songChoice = scanner.nextInt();
                scanner.nextLine();

                if (songChoice > 0 && songChoice <= results.size()) {
                    int selectedSongId = songIds.get(songChoice - 1);

                    String insertSongSQL = "INSERT INTO userlikes(user_id,song_id) values (?,?)" ;

                    try (PreparedStatement insertStatement = connection.prepareStatement(insertSongSQL)) {
                        int userId = getUserId(username);
                        if(userId != -1){
                            insertStatement.setInt(1, userId);
                            insertStatement.setString(2, String.valueOf(selectedSongId));
                            int addedSong = insertStatement.executeUpdate();

                            if (addedSong > 0) {
                                System.out.println("Song Added");
                            } else {
                                System.out.println("Failed to add song ");
                            }
                        }
                        else{System.out.println("User not found");
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

    private static int getUserId(String username) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "password");
        PreparedStatement stmt = conn.prepareStatement("SELECT user_id FROM users WHERE username = ?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("user_id");
        }
        return -1; // Return -1 if user not found
    }

}

