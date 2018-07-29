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
import travel.avg.travel.Adapter.MyAdapter4;
import travel.avg.travel.api.Helper;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.Tour;

public class MyToursActivity extends AppCompatActivity {
    List<Tour> values = new ArrayList<>();
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytours);

        retrofit= new Retrofit.Builder()
                .baseUrl(Helper.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<List<Tour>> call = serverApi.getTour();
        call.enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                if(response.isSuccessful()){
                    values = response.body();
                    ArrayList<Tour> arrayList = new ArrayList();
                    arrayList.addAll(values);
                    MyAdapter4 myAdapter4 = new MyAdapter4(MyToursActivity.this, arrayList);
                    ListView lvMain = findViewById(R.id.lvMain);
                    lvMain.setAdapter(myAdapter4);
                }
                else {
                    Toast.makeText(getApplicationContext(), "faild code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "faild : " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
