package travel.avg.travel.entities;

import java.util.List;

public class Place {
    private String id;
    private String fId;
    private String pId;
    private String status;
    private String name;
    private String cId;
    private String description;
    private String coordinates;
    private String date;
    private String photo;

    public Place(String id, String fId, String pId, String status, String name, String cId, String description, String coordinates, String date, String photo) {
        this.id = id;
        this.fId = fId;
        this.pId = pId;
        this.status = status;
        this.name = name;
        this.cId = cId;
        this.description = description;
        this.coordinates = coordinates;
        this.date = date;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getfId() {
        return fId;
    }

    public String getpId() {
        return pId;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getcId() {
        return cId;
    }

    public String getDescription() {
        return description;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getDate() {
        return date;
    }

    public String getPhoto() {
        return photo;
    }
}
