package inlocomedia.com.methodologicapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;
import inlocomedia.com.methodologicapp.util.Constants;
import inlocomedia.com.methodologicapp.util.NetwokUtil;
import inlocomedia.com.methodologicapp.R;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener
{

    @Bind(R.id.search_button)
    Button searchButton;

    private double latitude = 0;
    private double longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        searchButton.setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        map.addMarker(new MarkerOptions()
                .draggable(true)
                .position(new LatLng(latitude, longitude)));

        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener()
        {
            @Override
            public void onMarkerDragStart(Marker marker) {}

            @Override
            public void onMarkerDrag(Marker marker) {}

            @Override
            public void onMarkerDragEnd(Marker marker)
            {
                LatLng location = marker.getPosition();
                latitude = location.latitude;
                longitude = location.longitude;
            }
        });

    }


    @Override
    public void onClick(View view)
    {
        if(NetwokUtil.hasConnection(this))
        {
            Intent intent = new Intent(this, CityListActivity.class);
            intent.putExtra(Constants.LAT_TAG, latitude);
            intent.putExtra(Constants.LONG_TAG, longitude);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, getString(R.string.no_network_connection_message), Toast.LENGTH_LONG).show();
        }
    }
}
