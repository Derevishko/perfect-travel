package travel.avg.travel.entities;

public class Tour {

    private String id_Tour;
    private String tour_name;
    private String tour_status;
    private String tourDescription;
    private String tourGuid;
    private int tour_freeSeats;
    private int tour_cost;
    private String time;

    public Tour() {
    }

    public Tour(String id_Tour, String tour_name, String tour_status, String tourDescription, String tourGuid, int tour_freeSeats, int tour_cost, String time) {
        this.id_Tour = id_Tour;
        this.tour_name = tour_name;
        this.tour_status = tour_status;
        this.tourDescription = tourDescription;
        this.tourGuid = tourGuid;
        this.tour_freeSeats = tour_freeSeats;
        this.tour_cost = tour_cost;
        this.time = time;
    }

    public String getId_Tour() {
        return id_Tour;
    }

    public String getTour_name() {
        return tour_name;
    }

    public String getTour_status() {
        return tour_status;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public String getTourGuid() {
        return tourGuid;
    }

    public int getTour_freeSeats() {
        return tour_freeSeats;
    }

    public int getTour_cost() {
        return tour_cost;
    }

    public String getTime() {
        return time;
    }
}
