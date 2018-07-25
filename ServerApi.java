package travel.avg.travel.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import travel.avg.travel.entities.Cities;
import travel.avg.travel.entities.Places;
import travel.avg.travel.entities.Tour;
import travel.avg.travel.entities.Users;

public interface ServerApi {
    @GET("messages1.json")
    Call<List<Users>> users();

    @POST("users/{id}")
    Call<List<Users>> singUp(@Body Users users);

    @GET("users/{id}")
    Call<List<Users>> getUser(@Path("id") int id);

    @GET("Tour")
    Call<List<Tour>> getTour();

    @GET("Cities/{id_tour}")
    Call<List<Cities>> getCities(@Path("id_tour") int id_tour);

    @GET("Places/{id_place}")
    Call<Places> getPlaces();

    @GET("Guides/{id_guid}")
    Call<List<Places>> getPlaces(@Path("id_guid") int id);

}
