package travel.avg.travel.entities;

public class Users {
    int id;
    String userName;
    StringBuilder user_tours;

    public Users(int id, String userName, StringBuilder user_tours) {
        this.id = id;
        this.userName = userName;
        this.user_tours = user_tours;
    }
}
