package ie.atu.jdbc.application;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public interface Search {
ArrayList<String> searchSong(Connection conn, Scanner scanner,String userSearch) throws SQLException;
}
