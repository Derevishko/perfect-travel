package travel.avg.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.User;
import travel.avg.travel.entities.UserRegister;

public class RegActivity extends AppCompatActivity{

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        btn = findViewById(R.id.regUser);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(RegActivity.this, HomeActivity.class);
                finish();
                startActivity(log);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerApi serverApi = retrofit.create(ServerApi.class);

        int[] asd = new int[10];
        for (int i:asd) {
            asd[i] = i;
        }

        Call<List<UserRegister>> users = serverApi.singUp(new UserRegister("email", "name", "pass"));

        users.enqueue(new Callback<List<UserRegister>>() {
            @Override
            public void onResponse(Call<List<UserRegister>> call, Response<List<UserRegister>> response) {
                if(response.isSuccessful()){
                    List<UserRegister> list = response.body();
                    //Log.d("response :" , String.valueOf(list.get(1).getId_user()));
                    //Log.d("response :", String.valueOf(response.body()));
                }
                else
                    Log.d("responce cod: ", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<List<UserRegister>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "faild: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
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
