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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import travel.avg.travel.R;
import travel.avg.travel.RoutActivity;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.City;
import travel.avg.travel.entities.Tour;

public class MyAdapter1 extends ArrayAdapter<Tour> {

    Context context;
    private List<Tour> values;
    Retrofit retrofit;
    static String HOST="";

    public MyAdapter1(Context context, List<Tour> values) {
        super(context, R.layout.item, values);

        this.context = context;
        this.values = values;

        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if(view == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, parent, false);
        }

        final TextView header = view.findViewById(R.id.header);
        final CheckBox checkBox = view.findViewById(R.id.cbBox);
        final TextView description = view.findViewById(R.id.textInfo);
        final Button btn = view.findViewById(R.id.btn);
        final ImageButton imageView = view.findViewById(R.id.tourStatus);
        final TextView goneVisible = view.findViewById(R.id.goneVisible);

        final Tour tour = values.get(position);
        header.setText(tour.getTour_name());
        description.setText(tour.getTourDescription());
        //date.setText(new SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(new Date(item.getDate())));

        final Intent intent = new Intent(context, RoutActivity.class);

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<List<Tour>> call = serverApi.getTour();
        call.enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        List<Tour> list= response.body();
                        for (Tour item: list) {
                            header.setText(item.getTour_name());
                            description.setText(item.getTourDescription());
                            goneVisible.setText(item.getId_Tour());
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if(isChecked==true){
                                        //будет вызов метода, который отслылает запрос адмену
                                    }
                                }
                            });
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    intent.putExtra("id_Tour", goneVisible.getText().toString());
                                    context.startActivity(intent);
                                }
                            });
                            switch (item.getTour_status()){
                                case "passed":
                                    imageView.setImageResource(R.drawable.ic_time);
                                    break;
                                case "present":
                                    imageView.setImageResource(R.drawable.ic_present);
                                    break;
                                case "future":
                                    imageView.setImageResource(R.drawable.ic_past);
                                    break;
                            }
                        }
                    }
                }
                Toast.makeText(context.getApplicationContext(), "faild code : " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "faild : " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
