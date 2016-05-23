package unlv.erc.partiubalada.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class LocationChecker extends FragmentActivity implements OnMapReadyCallback {
    private static final int INITIAL_ZOOM_LEVEL = 12;
    private Circle circle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_checker);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent intent = getIntent();
        Party party = (Party) intent.getSerializableExtra(Party.PARTY_SERIALIZABLE_KEY);

        LatLng partyLocation = new LatLng(party.getLatitude(), party.getLongitude());
        MarkerOptions partyMarker = new MarkerOptions().position(partyLocation).title(party
                .getPartyName());
        GoogleMap map = googleMap;

        map.addMarker(partyMarker);

        circle = map.addCircle(new CircleOptions().center(partyLocation).radius(500));
        circle.setFillColor(Color.argb(30, 102, 163, 194));
        circle.setStrokeColor(Color.argb(30, 0, 0, 0));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(partyLocation, INITIAL_ZOOM_LEVEL));

    }
}

