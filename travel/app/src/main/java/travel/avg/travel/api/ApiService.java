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
import travel.avg.travel.entities.Token;
import travel.avg.travel.entities.UserRegister;

public class ApiService {
    Retrofit builder;
    ServerApi serverApi;
    Context context;

    public ApiService(Context context) {
        this.context =context;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        builder = new Retrofit.Builder()
                .baseUrl(Helper.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverApi = builder.create(ServerApi.class);
    }

    public Call<String> Bron(String tour_id, String user_id){
        return serverApi.bronirovanie(new Bronirovanie(tour_id, user_id));
    }
}
