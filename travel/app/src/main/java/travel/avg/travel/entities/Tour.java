package travel.avg.travel.entities;

import java.util.ArrayList;

public class Tour {

    int id;
    public String tour_name;
    public boolean tour_status;
    public String tourDescription;
    public String tourGuide1;
    public ArrayList<String> tour_cities;
    public boolean tour_freeSeats;
    public int tour_cost;

    public Tour(String tour_name, String tourDescription, boolean tour_status) {
        this.tour_name = tour_name;
        this.tourDescription = tourDescription;
        this.tour_status = tour_status;
    }
}
