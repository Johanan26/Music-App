package ie.atu.jdbc.application;
import com.sun.source.tree.CaseTree;
import ie.atu.jdbc.pool.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class UserMenu {
    public static void main(String[] args) throws SQLException {
        String continuing = "y";
        int y=0;
        int x=0;
        int logout = 0;

        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "password");
        Scanner scanner = new Scanner(System.in);
        char log;

        System.out.println("Log in or Sign up (L or S):");
        log = scanner.nextLine().charAt(0);

        //if a user clicks s, they can sign up and create an account.
        if (log == 'S' || log == 's') {
            signUp(conn,scanner);
        }

        //when a user enters L, they enter their username and password.
        //if it's in database, login is successful
        else if(log == 'L' || log== 'l'){
            try {
                // Prompt the user to input data
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                // Check if the provided credentials exist in the database
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Login successful.\n");
                    x=1;
                } else {
                    System.out.println("Invalid username or password.");
                }

                if (x==1){
                    while(continuing.equalsIgnoreCase("y")){
                        System.out.println("Welcome back, " + username);

                    String[] menu = Menu.displayMenu();
                    for (String menuHeadings : menu) {
                        System.out.println(menuHeadings);
                    }

                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.println("Home");//shows recent playlist or a random liked song and also following
                            Home home = new Home();
                            home.showSongs();
                            System.out.println();//shows recent playlist or a random liked song and also following
                            home.showAlbums();
                            break;
                        case 2:
                            System.out.println("Search");//search for songs and artists
                            break;
                        case 3:
                            System.out.println("Library");//choose to see playlists and liked songs
                            System.out.println("Liked Songs (1) | Playlists (2)");//choose to see playlists and liked songs
                            int option = scanner.nextInt();
                            scanner.nextLine();
                            if(option==1){
                                ShowLiked.showLikedSongs(username);
                            }
                            else if(option ==2){
                                ShowPlaylist.ShowPlaylist(username);
                            }
                            else{
                                System.out.println("Invalid Entry, Try again");
                            }

                            break;
                        case 4:
                            System.out.println("Settings");//account information, change plan
                            System.out.println("Update Name (1) | Update Username (2) | Update Passwords (3) | Update Email (4) | Update Subscription (5) | DELETE ACCOUNT (6)");//choose to see playlists and liked songs
                            int setting = scanner.nextInt();
                            scanner.nextLine();

                                switch (setting) {
                                    case 1:
                                        System.out.println("Change name");
                                        System.out.println("Enter your new name:");
                                        String updatedName = scanner.nextLine();
                                        Settings.updateName(conn,updatedName,username);
                                        break;
                                    case 2:
                                        System.out.println("Change username");
                                        System.out.println("Enter your new username:");
                                        String updatedUsername = scanner.nextLine();
                                        Settings.updateUsername(conn,updatedUsername,username);
                                        break;
                                    case 3:
                                        System.out.println("Change Password");
                                        System.out.println("Enter your new Password:");
                                        String updatedPassword = scanner.nextLine();
                                        Settings.updatePassword(conn,updatedPassword,username);
                                        break;
                                    case 4:
                                        System.out.println("Change Email");
                                        System.out.println("Enter your new Email:");
                                        String updatedEmail = scanner.nextLine();
                                        Settings.updateEmail(conn,updatedEmail,username);
                                        break;
                                    case 5:
                                        System.out.println("Change Subscription");
                                        System.out.println("Enter your Subscription_id:");
                                        String updatedSubscription_id = scanner.nextLine();
                                        Settings.updateSubscription_id(conn,updatedSubscription_id,username);
                                        break;
                                    case 6:
                                        System.out.println("Delete Account!");
                                        Settings.deleteAccount(conn,username);
                                        break;
                                    default:
                                        System.out.println("Invalid option");
                                        break;
                                }

                            break;
                        case 5:
                            System.out.println("Logging out...");//ends program
                            return;

                        default:
                            System.out.println("INVALID ENTRY");//search for songs and artists
                            break;
                    }
                        System.out.print("Continue? (y/n): ");
                        continuing = scanner.nextLine();
                        System.out.println();
                    }
                }

            } catch (SQLException ex) {
                System.out.println("Login failed.");
                ex.printStackTrace();
            }
        }
        //if a user does not enter L or S when asked
        else{
            System.out.println("Invalid option. 'L' for login, or 'S' for sign up.");
        }
    }

    //increments the user_id for signing up
    //this way it creates another user after the last one in the database and doesn't start at id=0
    private static int getLastInsertId(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Retrieve the maximum value of the user_id column from the users table
            ResultSet rs = stmt.executeQuery("SELECT MAX(user_id) FROM users");
            rs.next();
            int maxId = rs.getInt(1);
            // Increment the maximum value by 1 to get the next available user_id
            return maxId + 1;
        }
    }
    public static void signUp(Connection conn, Scanner scanner){
        ArrayList<String> details = new ArrayList<String>();

        try {
            // Prompt the user to input data
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            details.add("Name:"+name);
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            details.add("Username:"+username);
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            details.add("Email:"+email);
            System.out.println("Enter subscription_id:");
            String subscriptionId = scanner.nextLine();
            details.add("Subscription:"+subscriptionId);
            System.out.println("Enter gender:");
            String gender = scanner.nextLine();
            details.add("Gender:"+ gender);
            System.out.println("Enter country:");
            String country = scanner.nextLine();
            details.add("Country:"+ country);

            System.out.println("------------------------");

            for(String detail : details){
                System.out.println(detail);

            }

            // Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(user_id,name, username, email, subscription_id, gender, country) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, getLastInsertId(conn));
            stmt.setString(2, name);
            stmt.setString(3, username);
            stmt.setString(4, email);
            stmt.setString(5, subscriptionId);
            stmt.setString(6, gender);
            stmt.setString(7, country);
            stmt.executeUpdate();
            System.out.println("------------------------");
            System.out.println("Creating user...");
        } catch (SQLException ex) {
            System.out.println("Failed to create user!");
            ex.printStackTrace();
        }
    }
    }


