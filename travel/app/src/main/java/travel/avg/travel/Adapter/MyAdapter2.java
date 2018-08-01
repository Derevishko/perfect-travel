package travel.avg.travel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import travel.avg.travel.MapsActivity;
import travel.avg.travel.R;
import travel.avg.travel.entities.City;

public class MyAdapter2 extends ArrayAdapter<City> {
    Context context;
    private ArrayList<City> values;

    public MyAdapter2(Context context, ArrayList<City> values) {
        super(context, R.layout.item2, values);

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

        final City item = values.get(position);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("CityName", item.getName());
                intent.putExtra("CityId", item.getId());
                context.startActivity(intent);
            }
        });
        ((TextView) view.findViewById(R.id.vname)).setText(item.getName());
        ((TextView) view.findViewById(R.id.vdescription)).setText(item.getDescription());
//        ImageView img = view.findViewById(R.id.photoCity);
//        URL url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
//        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        img.setImageBitmap(bmp);
        return view;
    }
}
