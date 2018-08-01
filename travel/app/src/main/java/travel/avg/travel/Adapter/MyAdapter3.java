package travel.avg.travel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import travel.avg.travel.MapsActivity;
import travel.avg.travel.R;
import travel.avg.travel.Tours;
import travel.avg.travel.api.ApiService;
import travel.avg.travel.entities.City;
import travel.avg.travel.entities.Photo;
import travel.avg.travel.entities.Place;
import travel.avg.travel.entities.Tour;

public class MyAdapter3 extends ArrayAdapter<Place>{

    Context context;
    private ArrayList<Place> values;
    Photo photo;

    public MyAdapter3(Context contextn, ArrayList<Place> values) {
        super(contextn, R.layout.item3, values);

        this.context = contextn;
        this.values = values;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item3, parent, false);
        }

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

        Place item = values.get(position);
        ((TextView)view.findViewById(R.id.infoTime)).setText("Время и дата пребывания: " + item.getDate() + "\n"
                                                + "Описание: " + item.getDescription() + "\n"
                                                + "Статус тура: " + item.getStatus());
        btn.setText(item.getName());

//        final View finalView1 = view;
//        new ApiService(context).getPhoto(item.getPhoto())
//                .enqueue(new Callback<Photo>() {
//                    @Override
//                    public void onResponse(Call<Photo> call, Response<Photo> response) {
//                        if(response.isSuccessful()){
//                            photo = response.body();
//                            ImageView img = finalView1.findViewById(R.id.image);
//                            img.setImageBitmap(photo.getPhoto());
//                        }
//                        else {
//                            Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Photo> call, Throwable t) {
//                        Toast.makeText(context, "faild code2: " + t.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });

        return view;
    }
}