package travel.avg.travel.entities;

public class City {
    private String id_city;
    private String city_name;
    private String city_desc;
    private String city_photos;
    private int city_coords;
    private String date;
    private String time;
    private String city_status;

    public City() {
    }

    //all exept coords
    public City(String id_city, String city_name, String city_desc, String city_photos, String date, String time, String city_status) {
        this.id_city = id_city;
        this.city_name = city_name;
        this.city_desc = city_desc;
        this.city_photos = city_photos;
        this.date = date;
        this.time = time;
        this.city_status = city_status;
    }

    //all fields
    public City(String id_city, String city_name, String city_desc, String city_photos, int city_coords, String date, String time, String city_status) {
        this.id_city = id_city;
        this.city_name = city_name;
        this.city_desc = city_desc;
        this.city_photos = city_photos;
        this.city_coords = city_coords;
        this.date = date;
        this.time = time;
        this.city_status = city_status;
    }

    public String getId_city() {
        return id_city;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getCity_desc() {
        return city_desc;
    }

    public String getCity_photos() {
        return city_photos;
    }

    public int getCity_coords() {
        return city_coords;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCity_status() {
        return city_status;
    }
}
