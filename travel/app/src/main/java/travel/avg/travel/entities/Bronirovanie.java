package travel.avg.travel.entities;

import com.google.gson.annotations.SerializedName;

public class Bronirovanie {

    @SerializedName("tour_id")
    String tour_id;

    @SerializedName("user_id")
    String user_id;

    public Bronirovanie(String tour_id, String user_id) {
        this.tour_id = tour_id;
        this.user_id = user_id;
    }

    public void setTour_id(String tour_id) {
        this.tour_id = tour_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
