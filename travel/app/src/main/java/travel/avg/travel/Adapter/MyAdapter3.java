package travel.avg.travel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import travel.avg.travel.R;
import travel.avg.travel.entities.City;

public class MyAdapter3 extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<City> objects;

    public MyAdapter3(Context ctx, ArrayList<City> objects) {
        this.ctx = ctx;
        this.objects = objects;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public City getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null)
            view = lInflater.inflate(R.layout.item3, parent, false);

        final Button btn = view.findViewById(R.id.magic_btn);
        final View finalView = view;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout findMagicLl = finalView.findViewById(R.id.magic_layout);
                if (findMagicLl.getVisibility() == View.VISIBLE) {
                    findMagicLl.setVisibility(View.GONE);
                    btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow, 0);
                } else {
                    findMagicLl.setVisibility(View.VISIBLE);
                    btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up_arrow, 0);
                }
            }
        });

        City cities = getProduct(position);

        ((TextView)view.findViewById(R.id.infoTime)).setText(cities.getCity_name());
//        ((TextView) view.findViewById(R.id.vname)).setText(cities.city_name);
//        ((TextView) view.findViewById(R.id.vdescription)).setText(cities.city_desc);

        return view;
    }

    City getProduct(int position) {
        return ( getItem(position));
    }
}
