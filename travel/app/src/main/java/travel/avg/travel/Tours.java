package travel.avg.travel;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Tours{
    public static List<String> list;
    public static String id_Tour;
    public static List<Double[]> coord;

    public static List<String> getList() {
        return list;
    }

    public static void setList(List<String> list) {
        Tours.list = null;
        Tours.list = list;
    }

    public static String getId_Tour() {
        return id_Tour;
    }

    public static void setId_Tour(String id_Tour) {
        Tours.id_Tour = id_Tour;
    }

    public static List<Double[]> getCoord() {
        return coord;
    }

    public static void setCoord(Double[] coord) {
        Tours.coord.add(coord);
    }

    public static void getCoord(GoogleMap googleMap, String name){
        for (Double[] item : Tours.coord) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(item[0], item[1])).title(name));
        }
    }
}
