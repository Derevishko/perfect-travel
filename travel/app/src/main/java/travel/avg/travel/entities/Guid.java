package travel.avg.travel.entities;

public class Guid {
    String id_guid;
    String id_tour;
    String guid_name;
    String guid_phone;
    //String guid_coord;

    public Guid() {
    }

    public Guid(String id_guid, String id_tour, String guid_name, String guid_phone) {
        this.id_guid = id_guid;
        this.id_tour = id_tour;
        this.guid_name = guid_name;
        this.guid_phone = guid_phone;
    }

    public String getId_guid() {
        return id_guid;
    }

    public String getId_tour() {
        return id_tour;
    }

    public String getGuid_name() {
        return guid_name;
    }

    public String getGuid_phone() {
        return guid_phone;
    }
}
