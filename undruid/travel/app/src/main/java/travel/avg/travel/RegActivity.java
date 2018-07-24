package travel.avg.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        btn = (Button)findViewById(R.id.regUser) ;
        btn.setOnClickListener(this);
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
