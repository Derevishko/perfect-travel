package travel.avg.travel;

import android.annotation.SuppressLint;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import travel.avg.travel.Adapter.MyAdapter3;
import travel.avg.travel.api.Helper;
import travel.avg.travel.api.ServerApi;
import travel.avg.travel.entities.Place;
import travel.avg.travel.entities.Tour;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Place> values = new ArrayList<>();
    Retrofit retrofit;

    String NameCity;
    String CityId;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_time);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        CityId = getIntent().getStringExtra("CityId");
        NameCity =getIntent().getStringExtra("CityName");

        retrofit= new Retrofit.Builder()
                .baseUrl(Helper.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Tours.coord = null;

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<List<Place>> call = serverApi.getPlaces(Tours.getId_Tour(), CityId);
        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if(response.isSuccessful()){
                    values = response.body();
                    ArrayList<Place> arrayList = new ArrayList<>();
                    arrayList.addAll(values);

                    for (Place place: values) {
                     Tours.setCoord(place.getPlace_coords());
                    }
                    MyAdapter3 myAdapter3 = new MyAdapter3(MapsActivity.this, arrayList);
                    ListView lvMain = findViewById(R.id.map);
                    lvMain.setAdapter(myAdapter3);
                }
                else {
                    Toast.makeText(getApplicationContext(), "faild code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "faild : " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Tours.getCoord();
        //setUpMap();
    }

//    private void setUpMap() {
//        List<Double[]> item = null;
//        Double item2[] = {10.10, 11.11};
//        item.add(item2);
//        Toast.makeText(getApplicationContext(), item2.length, Toast.LENGTH_LONG).show();
//
//        for (Double[] qwert : item) {
//            mMap.addMarker(new MarkerOptions().position(new LatLng(0, 1)).title("Marker"));
//        }
//    }
}
