package travel.avg.travel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import travel.avg.travel.R;
import travel.avg.travel.entities.Cities;

public class MyAdapter2 extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Cities> objects;

    public MyAdapter2(Context context, ArrayList<Cities> cities) {
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
    public Object getItem(int position) {
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
            view = lInflater.inflate(R.layout.item2, parent, false);

        Cities cities = getProduct(position);
        // заполняем View в пункте списка данными из туров: заголовки, информация
        // и картинка
        ((TextView) view.findViewById(R.id.vname)).setText(cities.city_name);
        ((TextView) view.findViewById(R.id.vdescription)).setText(cities.city_desc);
//        ((TextView) view.findViewById(R.id.vdate)).setText(cities.get);
//        ((TextView) view.findViewById(R.id.varrival)).setText();
//        ((TextView)view.findViewById(R.id.vdeparture)).setText();

        return view;
    }

    Cities getProduct(int position) {
        return ((Cities) getItem(position));
    }

//
//    // обработчик для чекбоксов
//    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {
//        public void onCheckedChanged(CompoundButton buttonView,
//                                     boolean isChecked) {
//            // меняем данные товара (в корзине или нет)
//            getProduct((Integer) vname.getTag()).tour_status = isChecked;
//        }
//    };

}
