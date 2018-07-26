package travel.avg.travel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import travel.avg.travel.Adapter.MyAdapter1;
import travel.avg.travel.entities.Tour;

public class TourActivity extends AppCompatActivity {

    ArrayList<Tour> tours = new ArrayList<>();
    MyAdapter1 myAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tours);

        // создаем адаптер
        fillData();
        myAdapter1 = new MyAdapter1(this, tours);

            // настраиваем список
            ListView lvMain = findViewById(R.id.lvMain);
            lvMain.setAdapter(myAdapter1);

    }

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 20; i++) {
            tours.add(new Tour(i, "Tour "+i , "status", "yes", "Guid", 20, 1000, "22:40 - 21:00"));
        }
    }

//    // выводим информацию о турах
//    public void showResult(View v) {
//        String result = "Товары в корзине:";
//        for (Tour p : myAdapter1.getBox()) {
//            if (p.tour_status)
//                result += "\n" + p.tour_name;
//        }
//        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//    }

}
