package travel.avg.travel.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import travel.avg.travel.entities.City;
import travel.avg.travel.entities.Guid;
import travel.avg.travel.entities.Place;
import travel.avg.travel.entities.Tour;
import travel.avg.travel.entities.User;
import travel.avg.travel.entities.UserRegister;

public interface ServerApi {

    @POST("users/{id}")
    Call<List<UserRegister>> singUp(@Body UserRegister userRegister);

    //    @GET("messages1.json")
//    Call<List<User>> users();

    @GET("users/{id}")
    Call<List<User>> getUser(@Path("id") int id);

    @GET("Tour")
    Call<List<Tour>> getTour();

    @GET("Cities/{id_tour}")
    Call<List<City>> getCities(@Path("id_tour") int id_tour);

    @GET("Place/{id_place}")
    Call<List<Place>> getPlaces(@Path("id_place") int id_place);

    @GET("Guid/{id_tour}")
    Call<List<Guid>> getGuid(@Path("id_guid") int id_guid);

}
