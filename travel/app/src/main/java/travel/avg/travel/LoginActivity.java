package travel.avg.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import travel.avg.travel.api.ApiService;
import travel.avg.travel.api.Helper;
import travel.avg.travel.entities.Token;
import travel.avg.travel.entities.Tour;

public class LoginActivity extends AppCompatActivity {

    public static boolean status = false;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        retrofit = new Retrofit.Builder()
                .baseUrl(Helper.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void onClick(View view) {
        final String email = ((EditText) findViewById(R.id.editLogin)).getText().toString();
        final String pass = ((EditText) findViewById(R.id.editPas)).getText().toString();
        switch (view.getId()) {
            case R.id.logUser:
                if (!email.equals("") && !pass.equals("")) {
                    LogIn(email, pass);
                }
                break;
            case R.id.regUser:
                Intent reg = new Intent(LoginActivity.this, RegActivity.class);
                finish();
                startActivity(reg);
                break;
        }
    }

    public void LogIn(final String email, String pass) {
        new ApiService(this).LogIn(email, pass).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.code() == 200) {
                    status = true;
                    Token values = response.body();
                    Tours.setIdUser(values.getUsername());
                    Helper asd = new Helper(LoginActivity.this);
                    asd.setToken(values.getAccess_token());

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("email_user", email);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error code!" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
