package travel.avg.travel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import travel.avg.travel.MapsActivity;
import travel.avg.travel.R;
import travel.avg.travel.entities.City;

public class MyAdapter2 extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<City> objects;

    public MyAdapter2(Context context, ArrayList<City> cities) {
        this.ctx = context;
        this.objects = cities;
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
    public City getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null)
            view = lInflater.inflate(R.layout.item2, parent, false);

        LinearLayout linearLayout = view.findViewById(R.id.city);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, MapsActivity.class);
                ctx.startActivity(intent);
            }
        });

        City cities = getProduct(position);

        ((TextView) view.findViewById(R.id.vname)).setText(cities.city_name);
        ((TextView) view.findViewById(R.id.vdescription)).setText(cities.city_desc);
//        ((TextView) view.findViewById(R.id.vdate)).setText(cities.get);
//        ((TextView) view.findViewById(R.id.varrival)).setText();
//        ((TextView)view.findViewById(R.id.vdeparture)).setText();

        return view;
    }

    City getProduct(int position) {
        return ( getItem(position));
    }
}
