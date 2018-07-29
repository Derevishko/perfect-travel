package travel.avg.travel.entities;

import java.util.List;

public class User {
    private String id;
    private String name;
    String email;
    private List<String> Tours;

    public User() {
    }

    public User(String id, String name, String email, List<String> tours) {
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

    public List<String> getTours() {
        return Tours;
    }
}
