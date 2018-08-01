package travel.avg.travel;

import java.util.List;

import travel.avg.travel.entities.Tour;

public class Tours{
    public static String idUser;
    public static List<String> list;
    public static String id_Tour;
    public static List<String> coord;
    public static List<String> placeName;

    public static String getIdUser() {
        return idUser;
    }

    public static void setIdUser(String idUser) {
        Tours.idUser = idUser;
    }

    public static List<String> getList() {
        return list;
    }

    public static void setList(List<String> list) {
        Tours.list = list;
    }

    public static String getId_Tour() {
        return id_Tour;
    }

    public static void setId_Tour(String id_Tour) {
        Tours.id_Tour = id_Tour;
    }

    public static List<String> getCoord() {
        return coord;
    }

    public static void setCoord(String coord) {
        Tours.coord.add(coord);
    }

    public static void setPlaceName(String placeName) {
        Tours.placeName.add(placeName);
    }

    public static List<String> getPlaceName() {
        return placeName;
    }
}
