package travel.avg.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import travel.avg.travel.api.ApiService;
import travel.avg.travel.api.Helper;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.ClassTest;
import travel.avg.travel.entities.Token;

public class LoginActivity extends AppCompatActivity {

    public static boolean status = false;
    Retrofit retrofit;
    EditText email1, pass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        retrofit= new Retrofit.Builder()
                .baseUrl(Helper.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public void onClick(View view){
        final String email = ((EditText)findViewById(R.id.editLogin)).getText().toString();
        final String pass = ((EditText)findViewById(R.id.editPas)).getText().toString();
        switch (view.getId()){
            case R.id.logUser:
                SignIn(email, pass);
                if(email!=null && pass!=null){
                    try {
                        ServerApi serverApi = retrofit.create(ServerApi.class);
                        Call<Void> item = serverApi.authorization(email, pass);
                        item.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.code()==200){
                                    status=true;

                                    //String list = response.body();
                                    String id = null;

                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    intent.putExtra("email_user", email);
                                    intent.putExtra("id_user", id);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Error!" + response.code(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    catch (Exception e){
                        Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                finish();
                break;
            case R.id.regUser:
                Intent reg = new Intent(LoginActivity.this, RegActivity.class);
                finish();
                startActivity(reg);
                break;
        }
    }

    private void SignIn(final String email, String password){


    }

}
