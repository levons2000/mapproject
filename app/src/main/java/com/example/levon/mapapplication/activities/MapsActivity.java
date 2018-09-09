package com.example.levon.mapapplication.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.example.levon.mapapplication.R;
import com.example.levon.mapapplication.models.MarkModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final List<MarkModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng vanadzor = new LatLng(40.808754, 44.493151);
        mMap.addMarker(new MarkerOptions().position(vanadzor).title("Vanadzor"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vanadzor));
        initList();
        addMarks();
    }

    private void initList() {
        list.add(new MarkModel("Tashir", MarkModel.Types.CAFFE,
                40.809680, 44.491143));

        list.add(new MarkModel("ArcaxBusStop", MarkModel.Types.BUSSTOP,
                40.808392, 44.489166));

        list.add(new MarkModel("HotelKirovakan", MarkModel.Types.HOTEL,
                40.804891, 44.499845));

        list.add(new MarkModel("InecoBank", MarkModel.Types.BANK,
                40.808779, 44.493911));

    }

    private void addMarks() {
        for (int i = 0; i < list.size(); i++) {
            int resource = 0;
            LatLng mark = new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude());
            switch (list.get(i).getType()) {
                case BANK:
                    resource = R.drawable.ic_card_travel_black_24dp;
                    break;
                case CAFFE:
                    resource = R.drawable.ic_room_service_black_24dp;
                    break;
                case HOTEL:
                    resource = R.drawable.ic_hotel_black_24dp;
                    break;
                case BUSSTOP:
                    resource = R.drawable.ic_directions_bus_black_24dp;
                    break;
            }
            mMap.addMarker(new MarkerOptions().position(mark).title(list.get(i).getName())).setIcon(bitmapDescriptorFromVector(MapsActivity.this, resource));
        }

    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {
        final Drawable background = ContextCompat.getDrawable(context, R.drawable.ic_launcher_background);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        final Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
        final Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
