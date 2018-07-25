package travel.avg.travel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ListView;

import java.util.ArrayList;

import travel.avg.travel.Adapter.MyAdapter2;
import travel.avg.travel.entities.Cities;

public class Routs extends AppCompatActivity {

    ArrayList<Cities> cities = new ArrayList<>();
    MyAdapter2 myAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routs);

        // создаем адаптер
        fillData();
        myAdapter2 = new MyAdapter2(this, cities);

        // настраиваем список
        ListView lvMain = findViewById(R.id.lvMain);
        lvMain.setAdapter(myAdapter2);
    }

    void inputData(){

    }

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 20; i++) {
            cities.add(new Cities("City: " + 1, "This City: " + 1));
        }
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

