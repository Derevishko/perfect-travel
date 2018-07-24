package travel.avg.travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void OnClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.logUser:
                Intent log = new Intent(this, Home.class);
                finish();
                startActivity(log);
                break;
            case R.id.regUser:
                Intent reg = new Intent(this, RegActivity.class);
                finish();
                startActivity(reg);
                break;
        }
    }
}
