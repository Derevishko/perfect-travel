package travel.avg.travel.entities;

public class Place {
    int id;
    String place_name;
    String place_desc;
    int[] place_photos;
    int [] place_coords;

    public Place(int id, String place_name, String place_desc, int[] place_photos, int[] place_coords) {
        this.id = id;
        this.place_name = place_name;
        this.place_desc = place_desc;
        this.place_photos = place_photos;
        this.place_coords = place_coords;
    }
}
