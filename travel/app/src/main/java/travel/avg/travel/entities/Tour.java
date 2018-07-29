package travel.avg.travel.entities;

public class Tour {

    private String id;
    private String name;
    private String description;
    private String status;
    private String photo;
    private int free;
    private int price;
    private String date;
    private String guide;

    public Tour() {
    }

    public Tour(String id, String name, String description, String status, String photo, int free, int price, String date, String guide) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.photo = photo;
        this.free = free;
        this.price = price;
        this.date = date;
        this.guide = guide;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getPhoto() {
        return photo;
    }

    public int getFree() {
        return free;
    }

    public int getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getGuide() {
        return guide;
    }
}
