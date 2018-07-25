package travel.avg.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    static boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.logUser:
                Intent log = new Intent(LoginActivity.this, HomeActivity.class);
                finish();
                startActivity(log);
                break;
            case R.id.regUser:
                Intent reg = new Intent(LoginActivity.this, RegActivity.class);
                finish();
                startActivity(reg);
                break;
        }
    }

}
