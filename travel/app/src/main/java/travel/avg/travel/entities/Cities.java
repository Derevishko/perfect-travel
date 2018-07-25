package travel.avg.travel.entities;

public class Cities {
    public String city_name;
    public String city_desc;
    public String city_photos;
    public int city_coords;
    public int city_places;

    public Cities(String city_name, String city_desc) {
        this.city_name = city_name;
        this.city_desc = city_desc;
    }
}
