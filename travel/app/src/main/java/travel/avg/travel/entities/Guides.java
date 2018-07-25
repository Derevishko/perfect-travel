package travel.avg.travel.entities;

public class Guides {
    int id;
    String guide_name;
    int guide_phone;
    String guide_schedule;

    public Guides(int id, String guide_name, int guide_phone, String guide_schedule) {
        this.id = id;
        this.guide_name = guide_name;
        this.guide_phone = guide_phone;
        this.guide_schedule = guide_schedule;
    }
}
