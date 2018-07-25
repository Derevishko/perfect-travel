package travel.avg.travel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import travel.avg.travel.Adapter.MyAdapter3;
import travel.avg.travel.entities.City;

public class TestActivity extends AppCompatActivity {

    ArrayList<City> city = new ArrayList<>();
    MyAdapter3 adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        fillData();
        adapter3 = new MyAdapter3(this, city);


        ListView lvMain = findViewById(R.id.lvMain);
        lvMain.setAdapter(adapter3);
    }

    private void fillData() {
        for (int i = 1; i <= 20; i++) {
            city.add(new City("City: " + 1, "This City: " + 1));
        }
    }
}
