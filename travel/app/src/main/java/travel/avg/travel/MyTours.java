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

public class MyTours extends AppCompatActivity {

    ArrayList<Tour> tours = new ArrayList<Tour>();
    MyAdapter1 myAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytours);

        // создаем адаптер
        fillData();
        myAdapter1 = new MyAdapter1(this, tours);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(myAdapter1);
    }

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 20; i++) {
            tours.add(new Tour("Tour: " + 1, "This Tour: " + 1, true));
        }
    }

    // выводим информацию о турах
    public void showResult(View v) {
        String result = "Товары в корзине:";
        for (Tour p : myAdapter1.getBox()) {
            if (p.tour_status)
                result += "\n" + p.tour_name;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish();
        }
        return true;
    }
}
