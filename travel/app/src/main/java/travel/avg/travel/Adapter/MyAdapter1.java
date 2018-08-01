package travel.avg.travel.Adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import travel.avg.travel.R;
import travel.avg.travel.RoutActivity;
import travel.avg.travel.Tours;
import travel.avg.travel.api.ApiService;
import travel.avg.travel.entities.Guid;
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
//        final ImageButton imageView = view.findViewById(R.id.tourStatus);
        final TextView name_guid = view.findViewById(R.id.name_guid);
        final TextView place = view.findViewById(R.id.place);
        final TextView price = view.findViewById(R.id.price);
        final Button bron = view.findViewById(R.id.bron);
        final Button btn = view.findViewById(R.id.info);

        //date.setText(new SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(new Date(item.getDate())));

        final Tour item = values.get(position);

        header.setText(item.getName());
        description.setText(item.getDescription());
        goneVisible.setText(item.getId());
        name_guid.setText("Гид: " + item.getGuide());
        place.setText("Свободных мест: " + String.valueOf(item.getFree()) + "$");
        price.setText("Стоимость тура: " + String.valueOf(item.getPrice()) + "$");

        name_guid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] phone = new String[1];
                new ApiService(context).getGuid(item.getId()).enqueue(new Callback<Guid>() {
                    @Override
                    public void onResponse(Call<Guid> call, Response<Guid> response) {
                        if (response.isSuccessful()){
                            Guid guid = response.body();
                            phone[0] = guid.getName();
                        }
                        else {
                            Toast.makeText(context, "Error!" + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Guid> call, Throwable t) {
                        Toast.makeText(context, "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(item.getGuide())
                        .setMessage("Имя: " + item.getGuide() + "\nНомер телефона: " + phone[0])
                        .setIcon(0)
                        .setNegativeButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
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

        bron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiService(context).Bron(item.getId(), Tours.getIdUser())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.code() == 200){
                                    Toast.makeText(context, "Вы забронировали тур", Toast.LENGTH_LONG).show();
                                }
                                else {
//                                    Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                                    Toast.makeText(context, "Вы не можете забронировать тур", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(context, "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return view;
    }
}
