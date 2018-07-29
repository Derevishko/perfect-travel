package travel.avg.travel.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import travel.avg.travel.R;
import travel.avg.travel.RoutActivity;
import travel.avg.travel.entities.Tour;

public class MyAdapter1 extends ArrayAdapter<Tour> {

    Context context;
    private ArrayList<Tour> values;

    public MyAdapter1(Context context, ArrayList<Tour> values) {
        super(context, R.layout.item, values);

        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if(view == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, parent, false);
        }

        final TextView goneVisible = view.findViewById(R.id.goneVisible);
        final TextView header = view.findViewById(R.id.header);
        final TextView description = view.findViewById(R.id.textInfo);
        final ImageButton imageView = view.findViewById(R.id.tourStatus);
        final TextView name_guid = view.findViewById(R.id.name_guid);
        final TextView place = view.findViewById(R.id.place);
        final TextView price = view.findViewById(R.id.price);
        final Button bron = view.findViewById(R.id.bron);
        final Button btn = view.findViewById(R.id.info);

        //date.setText(new SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(new Date(item.getDate())));

        Tour item = values.get(position);

        header.setText(item.getName());
        description.setText(item.getDescription());
        goneVisible.setText(item.getId());
        name_guid.setText("Гид: " + item.getGuide());
        place.setText("Свободных мест: " + String.valueOf(item.getFree()) + "$");
        price.setText("Стоимость тура: " + String.valueOf(item.getPrice()) + "$");


        bron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RoutActivity.class);
                intent.putExtra("id_Tour", goneVisible.getText().toString());
                context.startActivity(intent);
            }
        });

        int a = 0;
        if(item.getStatus().equals("Past")){
            a = -1;
        }
        else if(item.getStatus().equals("Present")){
            a=0;
        }
        else if(item.getStatus().equals("Future"))
            a=1;

        switch (a){
            case -1 :
                imageView.setImageResource(R.drawable.ic_time);
                break;
            case 0:
                imageView.setImageResource(R.drawable.ic_present);
                break;
            case 1:
                imageView.setImageResource(R.drawable.ic_past);
                break;
        }

        return view;
    }
}
