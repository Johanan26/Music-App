package ie.atu.jdbc.application;
import ie.atu.jdbc.pool.DatabaseUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchMenu implements Search{
    @Override
    public ArrayList<String> searchSong(Connection conn, Scanner scanner, String userSearch) throws SQLException {
        ArrayList<String> results = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT songs.song_name, artists.name " +
                "FROM songs " +
                "JOIN artists ON songs.artist_id = artists.artist_id " +
                "WHERE (songs.song_name LIKE ? OR artists.name LIKE ?)");
        stmt.setString(1,"%"+userSearch+"%");
        stmt.setString(2,"%"+userSearch+"%");

        ResultSet resultSet = stmt.executeQuery();
    while (resultSet.next()){
        String songName = resultSet.getString("song_name");
        String artistName = resultSet.getString("name");
        results.add(songName + " - "+ artistName);
         }
    return results;
    }

}
