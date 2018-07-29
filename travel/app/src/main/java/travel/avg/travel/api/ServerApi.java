package travel.avg.travel.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import travel.avg.travel.entities.Bronirovanie;
import travel.avg.travel.entities.City;
import travel.avg.travel.entities.ClassTest;
import travel.avg.travel.entities.Guid;
import travel.avg.travel.entities.Place;
import travel.avg.travel.entities.Token;
import travel.avg.travel.entities.Tour;
import travel.avg.travel.entities.User;
import travel.avg.travel.entities.UserRegister;

public interface ServerApi {

    @Headers("Content-Type: application/json")
    @POST("user")
    Call<Token> singUp(@Body UserRegister userRegister);

    @GET("user")
    Call<Void> authorization(@Field("Email") String email, @Field("Password") String password);

    @POST("tour")
    Call<String> bronirovanie(@Body Bronirovanie bronirovanie);

    @GET("user/{user_id}")
    Call<List<User>> getUser(@Path("user_id") String id);

    @GET("tour")
    Call<List<Tour>> getTour();

    @GET("tour/{tour_id}/city")
    Call<List<City>> getCities(@Path("tour_id") String id_tour);

    @GET("tour/{tour_id}/city/{city_id}")
    Call<List<Place>> getPlaces(@Path("id_place") int id_place);

    @GET("tour/{tour_id}/guide")
    Call<List<Guid>> getGuid(@Path("id_guid") String id_guid);


    //test
    @GET("")
    Call<List<ClassTest>> getTest();

}
