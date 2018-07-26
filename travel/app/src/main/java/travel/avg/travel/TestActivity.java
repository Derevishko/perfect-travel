package travel.avg.travel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import travel.avg.travel.Adapter.MyAdapter3;
import travel.avg.travel.entities.City;

public class TestActivity extends AppCompatActivity {

    int count = 1;

    Button btn;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        textView = findViewById(R.id.id);
        btn = findViewById(R.id.id1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("hi " + count);
                Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                textView.setId(count);
                count++;
            }
        });
    }
}
