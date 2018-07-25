package travel.avg.travel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import java.util.ArrayList;

import travel.avg.travel.R;
import travel.avg.travel.RoutActivity;
import travel.avg.travel.entities.Tour;

public class MyAdapter1 extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Tour> objects;

    public MyAdapter1(Context context, ArrayList<Tour> products) {
        this.ctx = context;
        this.objects = products;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Tour getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;

        if(view == null)
            view = lInflater.inflate(R.layout.item, parent, false);

        Tour tour = getProduct(position);

        ((TextView) view.findViewById(R.id.header)).setText(tour.tour_name);
        ((TextView)view.findViewById(R.id.textInfo)).setText(tour.tourDescription);

        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, RoutActivity.class);
                ctx.startActivity(intent);
            }
        });
        CheckBox cbBuy = view.findViewById(R.id.cbBox);
        // присваиваем чекбоксу обработчик
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        // пишем позицию
        cbBuy.setTag(position);
        // заполняем данными из товаров: в корзине или нет
        cbBuy.setChecked(tour.tour_status);

        return view;
    }

    // tour по позиции
    Tour getProduct(int position) {
        return (getItem(position));
    }

    // содержимое корзины
    public ArrayList<Tour> getBox() {
        ArrayList<Tour> box = new ArrayList<Tour>();
        for (Tour p : objects) {
            // если в корзине
            if (p.tour_status)
                box.add(p);
        }
        return box;
    }

    // обработчик для чекбоксов
    OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // меняем данные товара (в корзине или нет)
            getProduct((Integer) buttonView.getTag()).tour_status = isChecked;
        }
    };
}
