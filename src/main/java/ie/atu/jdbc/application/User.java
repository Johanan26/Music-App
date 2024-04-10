package ie.atu.jdbc.application;

//reference code lab 9
public class User {
    private String name;
    private String username;
    private String email;
    private String subscriptionId;
    private String gender;
    private String country;


    //constructor
    public User(String name, String username, String email, String subscriptionId, String gender, String country){
        this.name=name;
        this.username=username;
        this.email=email;
        this.subscriptionId=subscriptionId;
        this.gender=gender;
        this.country=country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
