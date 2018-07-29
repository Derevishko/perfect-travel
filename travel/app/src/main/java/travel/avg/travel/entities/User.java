package travel.avg.travel.entities;

public class User {
    private String id;
    private String name;
    String email;
    private String[] Tours;

    public User() {
    }

    public User(String id, String name, String email, String[] tours) {
        this.id = id;
        this.name = name;
        this.email = email;
        Tours = tours;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String[] getTours() {
        return Tours;
    }
}
