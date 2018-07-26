package travel.avg.travel.entities;

public class User {
    private String id_user;
    String email;
    private String userName;
    private int[] userTours;

    public User(String id_user, String email, String userName, int[] userTours) {
        this.id_user = id_user;
        this.email = email;
        this.userName = userName;
        this.userTours = userTours;
    }

    public User() {
    }

    public String getId_user() {
        return id_user;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public int[] getUserTours() {
        return userTours;
    }
}
