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
import travel.avg.travel.Adapter.AdapterTest;
import travel.avg.travel.api.Helper;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.ClassTest;

public class TestActivity extends AppCompatActivity {
    List<ClassTest> values = new ArrayList<>();
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        retrofit = new Retrofit.Builder()
                .baseUrl(Helper.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<List<ClassTest>> call = serverApi.getTest();

        call.enqueue(new Callback<List<ClassTest>>() {
            @Override
            public void onResponse(Call<List<ClassTest>> call, Response<List<ClassTest>> response) {
                if(response.isSuccessful()){
                    values = response.body();
                    ArrayList<ClassTest> l = new ArrayList();
                    l.addAll(values);
                    AdapterTest testadapter = new AdapterTest(TestActivity.this, l);
                    ListView lvMain = findViewById(R.id.listTest);
                    lvMain.setAdapter(testadapter);
                }
                else {
                    Toast.makeText(getApplicationContext(), "faild code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ClassTest>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "faild : " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
