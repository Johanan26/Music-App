package ie.atu.jdbc.test_interface;

public class Songs implements Music {
    private String name;
    private String artist;
    public Songs(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getArtist() {
        return artist;
    }
}