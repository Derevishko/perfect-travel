package travel.avg.travel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import travel.avg.travel.Adapter.MyAdapter2;
import travel.avg.travel.entities.City;

public class RoutActivity extends AppCompatActivity {

    ArrayList<City> city = new ArrayList<>();
    MyAdapter2 myAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routs);

        int id_tour = Integer.parseInt(getIntent().getStringExtra("id_Tour"));
        myAdapter2 = new MyAdapter2(this, city, id_tour);

        // настраиваем список
        ListView lvMain = findViewById(R.id.lvMain);
        lvMain.setAdapter(myAdapter2);
    }
}

