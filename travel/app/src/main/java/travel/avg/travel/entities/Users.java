package travel.avg.travel.entities;

public class Users {
    private int id;
//    String userName;
//    StringBuilder user_tours;
    private long time;
    private String text;
    //private String image;

    public Users() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public Users(int id, long time, String text) {
        this.id = id;
        this.time = time;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public StringBuilder getUser_tours() {
//        return user_tours;
//    }
//
//    public void setUser_tours(StringBuilder user_tours) {
//        this.user_tours = user_tours;
//    }
//
//    public Users(int id, String userName, StringBuilder user_tours) {
//        this.id = id;
//        this.userName = userName;
//        this.user_tours = user_tours;
//    }
}
