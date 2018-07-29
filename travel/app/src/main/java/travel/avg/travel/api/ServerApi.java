package travel.avg.travel.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

    //@Headers("Content-Type: application/json")
    @POST("user")
    Call<Void> singUp(@Body UserRegister userRegister);

    @GET("user")
    Call<Token> authorization(@Query("Email") String email, @Query("Password") String password);

    @POST("tour")
    Call<Void> bronirovanie(@Body Bronirovanie bronirovanie);
    //Call<Void> bronirovanie(@Header("Authorization") String authorization, @Body Bronirovanie bronirovanie);

    @GET("user/{user_id}")
    Call<User> getUser(@Path("user_id") String id);
    //Call<User> getUser(@Header("Authorization") String authorization, @Path("user_id") String id);

    @GET("tour")
    Call<List<Tour>> getTour();
    //Call<List<Tour>> getTour(@Header("Authorization") String authorization);

    @GET("tour/{tour_id}/city")
    Call<List<City>> getCities(@Path("tour_id") String id_tour);
    //Call<List<City>> getCities(@Header("Authorization") String authorization, @Path("tour_id") String id_tour);

    @GET("tour/{tour_id}/city/{city_id}")
    Call<List<Place>> getPlaces(@Path("tour_id") String tour_id, @Path("city_id") String city_id);
    //Call<List<Place>> getPlaces(@Header("Authorization") String authorization, @Path("tour_id") String tour_id, @Path("city_id") String city_id);

    @GET("tour/{tour_id}/guide")
    Call<Guid> getGuid(@Path("tour_id") String tour_id);
    //Call<Guid> getGuid(@Header("Authorization") String authorization, @Path("tour_id") String tour_id);


    //test
    @GET("")
    Call<List<ClassTest>> getTest();

}
