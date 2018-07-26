package travel.avg.travel.entities;

public class Place {
    private int id_city;
    private String place_name;
    private String place_description;
    private int place_photo;
    private int place_coords;
    private String time;
    private String place_status;

    public Place() {
    }

    public Place(int id_city, String place_name, String place_description, int place_photo, int place_coords, String time, String place_status) {
        this.id_city = id_city;
        this.place_name = place_name;
        this.place_description = place_description;
        this.place_photo = place_photo;
        this.place_coords = place_coords;
        this.time = time;
        this.place_status = place_status;
    }

    public int getId_city() {
        return id_city;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getPlace_description() {
        return place_description;
    }

    public int getPlace_photo() {
        return place_photo;
    }

    public int getPlace_coords() {
        return place_coords;
    }

    public String getTime() {
        return time;
    }

    public String getPlace_status() {
        return place_status;
    }
}
