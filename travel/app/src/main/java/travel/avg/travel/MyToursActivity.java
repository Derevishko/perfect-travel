package travel.avg.travel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.Touch;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import travel.avg.travel.Adapter.MyAdapter1;
import travel.avg.travel.Adapter.MyAdapter4;
import travel.avg.travel.api.ApiService;
import travel.avg.travel.api.Helper;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.Tour;
import travel.avg.travel.entities.User;

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

        ServerApi serverApi2 = retrofit.create(ServerApi.class);
        Call<User> user = serverApi2.getUser(Tours.getIdUser());
        user.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            User item = response.body();
                            Tours.setList(item.getTours());
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error!" + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<List<Tour>> call = serverApi.getTour();
        call.enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                if(response.isSuccessful()){
                    values = response.body();
                    ArrayList<Tour> arrayList = new ArrayList();
                    arrayList.addAll(values);
                    for(int i = 0; i < Tours.getList().size(); i++){
                        for(int j = 0; j < arrayList.size(); j++){
                            if(Tours.getList().get(i).equals(arrayList.get(j).getId())){
                                ArrayList<Tour> array = new ArrayList();
                                array.add(arrayList.get(j));
                                MyAdapter4 myAdapter4 = new MyAdapter4(MyToursActivity.this, array);
                                ListView lvMain = findViewById(R.id.lvMain);
                                lvMain.setAdapter(myAdapter4);
                            }
                        }
                    }
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
