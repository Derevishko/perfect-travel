package travel.avg.travel.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import travel.avg.travel.entities.City;
import travel.avg.travel.entities.Place;
import travel.avg.travel.entities.Tour;
import travel.avg.travel.entities.User;

public interface ServerApi {
    @GET("messages1.json")
    Call<List<User>> users();

    @POST("users/{id}")
    Call<List<User>> singUp(@Body User users);

    @GET("users/{id}")
    Call<List<User>> getUser(@Path("id") int id);

    @GET("Tour")
    Call<List<Tour>> getTour();

    @GET("Cities/{id_tour}")
    Call<List<City>> getCities(@Path("id_tour") int id_tour);

    @GET("Place/{id_place}")
    Call<Place> getPlaces();

    @GET("Guid/{id_guid}")
    Call<List<Place>> getPlaces(@Path("id_guid") int id);

}
