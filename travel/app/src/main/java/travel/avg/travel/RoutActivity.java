package travel.avg.travel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import travel.avg.travel.Adapter.MyAdapter1;
import travel.avg.travel.Adapter.MyAdapter2;
import travel.avg.travel.api.Helper;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.City;

public class RoutActivity extends AppCompatActivity {

    List<City> values = new ArrayList<>();
    Retrofit retrofit;
    String asd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routs);

//        int id_tour = Integer.parseInt(getIntent().getStringExtra("id_Tour"));
        retrofit= new Retrofit.Builder()
                .baseUrl(Helper.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<List<City>> call = serverApi.getCities("5b5a3fc7a4a2bf38d8c310a7");
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if(response.isSuccessful()){
                    values = response.body();
                    MyAdapter2 myAdapter2 = new MyAdapter2(RoutActivity.this, values);
                    ListView lvMain = findViewById(R.id.lvMain);
                    lvMain.setAdapter(myAdapter2);
                }
                else {
                    Toast.makeText(getApplicationContext(), "faild code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                asd = t.toString();
                Toast.makeText(getApplicationContext(), "Error : " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

