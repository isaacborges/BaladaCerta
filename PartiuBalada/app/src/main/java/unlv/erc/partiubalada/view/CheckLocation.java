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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationServices;


import helper.GPSTracker;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class CheckLocation extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int INITIAL_ZOOM_LEVEL = 12;

    private GoogleMap map = null;
    private Circle circle = null;
    private GoogleApiClient mGoogleApiClient = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                    .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);

        Intent intent = getIntent();
        Party party = (Party) intent.getSerializableExtra(Party.PARTY_SERIALIZABLE_KEY);

        float latitude = party.getLatitude();
        float longitude = party.getLongitude();
        String name = party.getPartyName();
        LatLng eventLocation = new LatLng(latitude, longitude);

        map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(name));

        circle = map.addCircle(new CircleOptions().center(eventLocation).radius(500));
        circle.setFillColor(Color.argb(30, 102, 163, 194));
        circle.setStrokeColor(Color.argb(30, 0, 0, 0));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLocation, INITIAL_ZOOM_LEVEL));

        this.map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location usrLocation) {
                float[] distance = new float[2];

                Location.distanceBetween(usrLocation.getLatitude(), usrLocation.getLongitude(),
                        circle.getCenter().latitude, circle.getCenter().longitude, distance);

                Dialog locationDialog = new Dialog(CheckLocation.this, R.style.FullHeightDialog);

                locationDialog = new Dialog(CheckLocation.this, R.style.FullHeightDialog);
                locationDialog.setContentView(R.layout.location_checker_dialog);
                locationDialog.setCancelable(true);
                TextView text = (TextView) locationDialog.findViewById(R.id.checker_dialog_text);

                Log.i("usrLocation.getLatitude()", usrLocation.getLatitude()+"");

                if (distance[0] <= circle.getRadius()) {
                    text.setText("Successfully rated. Have an awesome party! ");
                } else {
                    text.setText("You need to be inside the party to rank it.");
                }

                Button updateButton = (Button) locationDialog.findViewById(R.id.rank_dialog_button);
                final Dialog finalRankDialog = locationDialog;
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalRankDialog.dismiss();

                        Intent intent = new Intent(CheckLocation.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                locationDialog.show();
            }
        });
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            Log.i("My Latitude", String.valueOf(mLastLocation.getLatitude()));
            Log.i("My Longitude", String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

}
