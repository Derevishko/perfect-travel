package travel.avg.travel.entities;

public class Guid {
    String id;
    String phone;
    String tId;
    String name;
    //String guid_coord;

    public Guid() {
    }

    public Guid(String id, String phone, String tId, String name) {
        this.id = id;
        this.phone = phone;
        this.tId = tId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String gettId() {
        return tId;
    }

    public String getName() {
        return name;
    }
}
