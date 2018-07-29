package travel.avg.travel.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import travel.avg.travel.entities.Bronirovanie;
import travel.avg.travel.entities.Guid;
import travel.avg.travel.entities.Token;
import travel.avg.travel.entities.User;
import travel.avg.travel.entities.UserRegister;

public class ApiService {
    Retrofit builder;
    ServerApi serverApi;
    Context context;

    public ApiService(Context context) {
        this.context =context;

        builder = new Retrofit.Builder()
                .baseUrl(Helper.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverApi = builder.create(ServerApi.class);
    }

//    Helper helper = new Helper(context);
    public Call<Token> LogIn(String email, String password){
        return serverApi.authorization(email, password);
    }

    public Call<User> getUser(String user_id){
        return serverApi.getUser(user_id);
        //return serverApi.getUser(helper.getToken(), user_id);
    }

    public Call<Void> Bron(String tour_id, String user_id){
        return serverApi.bronirovanie(new Bronirovanie(tour_id, user_id));
        //return serverApi.bronirovanie(helper.getToken(), new Bronirovanie(tour_id, user_id));
    }

    public Call<Guid> getGuid(String id_tour){
        return serverApi.getGuid(id_tour);
        //return serverApi.getGuid(helper.getToken(), id_tour);
    }

    public Call<Void> SignUp(String name, String email, String password){
        return serverApi.singUp(new UserRegister(name, email, password));
    }
}
