package com.mirea.lavrenov.mireaproject.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mirea.lavrenov.mireaproject.R;

public class MapFragment extends Fragment implements OnMapClickListener{
    protected OnMapClickListener parentMapInstance = this;
    protected static GoogleMap mMap;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap mMap) {
            MapFragment.mMap = mMap;
            LatLng mirea78 = new LatLng(55.670005, 37.479894);
            mMap.addMarker(new MarkerOptions().title("РТУ МИРЭА, просп. Вернадского, 78")
                    .snippet("Дата основания:1947 г., 55.670005, 37.479894").position(mirea78));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mirea78));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    mirea78).zoom(15).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

            LatLng mirea86 = new LatLng(55.662034, 37.477918);
            mMap.addMarker(new MarkerOptions().title("РТУ МИРЭА, просп. Вернадского, 86")
                    .snippet("Дата основания: 1 июля 1900 г., 55.662034, 37.477918").position(mirea86));

            LatLng mirea20 = new LatLng(55.793733, 37.701398);
            mMap.addMarker(new MarkerOptions().title("РТУ МИРЭА, ул. Стромынка, 20")
                    .snippet("Дата основания:1936 г., 55.793733, 37.701398").position(mirea20));

            LatLng mireafr = new LatLng(55.967484, 38.051160);
            mMap.addMarker(new MarkerOptions().title("РТУ МИРЭА, Вокзальная ул., 2А, корп. 61, Фрязино")
                    .snippet("Дата основания: 1962 г., 55.967484, 38.051160").position(mireafr));

            LatLng mireast = new LatLng(45.052104, 41.912494);
            mMap.addMarker(new MarkerOptions().title("РТУ МИРЭА, пр-т Кулакова, 8, Ставрополь, Ставропольский край")
                    .snippet("Дата основания: 18 декабря 1996 года, 45.052104, 41.912494").position(mireast));


            // кнопоки изменнеия масштаба
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            mMap.setOnMapClickListener(parentMapInstance);

        }
    };

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Toast.makeText(getContext(), latLng.toString(), Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}