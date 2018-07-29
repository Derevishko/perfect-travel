package travel.avg.travel.entities;

public class City {
    private String id;
    private String tId;
    private String cId;
    private String status;
    private String name;
    private String description;
    private String[] photos;
    private float[] coordinates;

    public City(String id, String tId, String cId, String status, String name, String description, String[] photos, float[] coordinates) {
        this.id = id;
        this.tId = tId;
        this.cId = cId;
        this.status = status;
        this.name = name;
        this.description = description;
        this.photos = photos;
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public String gettId() {
        return tId;
    }

    public String getcId() {
        return cId;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getPhotos() {
        return photos;
    }

    public float[] getCoordinates() {
        return coordinates;
    }
}
