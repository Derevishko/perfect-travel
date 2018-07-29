package travel.avg.travel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import travel.avg.travel.MapsActivity;
import travel.avg.travel.R;
import travel.avg.travel.RoutActivity;
import travel.avg.travel.entities.City;
import travel.avg.travel.entities.Tour;

public class MyAdapter2 extends ArrayAdapter<City> {
    Context context;
    private List<City> values;

    int id_tour;

    public MyAdapter2(Context context, List<City> values) {
        super(context, R.layout.item2);

        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if(view == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item2, parent, false);
        }

        LinearLayout linearLayout = view.findViewById(R.id.city);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                context.startActivity(intent);
            }
        });

        City item = values.get(position);
        ((TextView) view.findViewById(R.id.vname)).setText(item.getName());
        ((TextView) view.findViewById(R.id.vdescription)).setText(item.getDescription());

        return view;
    }
}
