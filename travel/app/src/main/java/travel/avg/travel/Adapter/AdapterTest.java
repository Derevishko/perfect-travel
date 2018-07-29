package travel.avg.travel.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import travel.avg.travel.R;
import travel.avg.travel.entities.ClassTest;

public class AdapterTest extends ArrayAdapter {
    private Context context;
    ArrayList<ClassTest> values;

    public AdapterTest(Context context, ArrayList<ClassTest> values){
        super(context, R.layout.item_test, values);

        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_test, parent, false);
        }

        TextView id = view.findViewById(R.id.test1);
        TextView time = view.findViewById(R.id.test2);
        TextView text = view.findViewById(R.id.test3);

        ClassTest item = values.get(position);

        id.setText(String.valueOf(item.getId()));
        time.setText(String.valueOf( item.getTime()));
        text.setText(item.getText());


        return view;
    }
}
