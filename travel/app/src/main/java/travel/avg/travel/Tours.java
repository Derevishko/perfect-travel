package travel.avg.travel;

import java.util.List;

public class Tours{
    public static String idUser;
    public static List<String> list;
    public static String id_Tour;
    public static List<Double[]> coord;

    public static String getIdUser() {
        return idUser;
    }

    public static void setIdUser(String idUser) {
        Tours.idUser = idUser;
    }

    public static void setCoord(List<Double[]> coord) {
        Tours.coord = coord;
    }

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
}
