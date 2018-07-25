package travel.avg.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.Users;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        btn = (Button) findViewById(R.id.regUser);
        btn.setOnClickListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);

        final Call<List<Users>> users = serverApi.users();

        users.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.isSuccessful()){
                    List<Users> list = response.body();
                    Log.d("response :" , String.valueOf(list.get(1).getId()));
                    //Log.d("response :", String.valueOf(response.body()));
                }
                else
                    Log.d("responce cod: ", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.d("faild: ", t.toString());
            }
        });
    }


    @Override
    public void onClick(View view) {

        Intent log = new Intent(this, Home.class);
        finish();
        startActivity(log);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent log1 = new Intent(this, LoginActivity.class);
            finish();
            startActivity(log1);
        }
        return true;
    }
}
