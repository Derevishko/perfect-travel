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

        // создаем адаптер
        fillData();
        myAdapter2 = new MyAdapter2(this, city);

        // настраиваем список
        ListView lvMain = findViewById(R.id.lvMain);
        lvMain.setAdapter(myAdapter2);
    }

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 20; i++) {
            city.add(new City("City: " + 1, "This City: " + 1));
        }
    }
}

