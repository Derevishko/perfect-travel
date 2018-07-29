package travel.avg.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import travel.avg.travel.api.ApiService;
import travel.avg.travel.api.Helper;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.Token;
import travel.avg.travel.entities.User;
import travel.avg.travel.entities.UserRegister;

public class RegActivity extends AppCompatActivity{

    Button btn;
    Retrofit retrofit;
    EditText editName, editLogin, editPas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        btn = findViewById(R.id.regUser);
        editName = findViewById(R.id.editName);
        editLogin = findViewById(R.id.editLogin);
        editPas = findViewById(R.id.editPas);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editName!=null && editLogin!=null && editPas!=null){
                    SignUp(editName.getText().toString(), editLogin.getText().toString(), editPas.getText().toString());
                }
                else {
                    Toast.makeText(getApplication(), "Error pole: ", Toast.LENGTH_LONG ).show();
                }
            }
        });
    }

    public void SignUp(String name, String email, String password){
        new ApiService(RegActivity.this).SignUp(name, email, password)
                .enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error : " + t.toString(), Toast.LENGTH_SHORT).show();
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
