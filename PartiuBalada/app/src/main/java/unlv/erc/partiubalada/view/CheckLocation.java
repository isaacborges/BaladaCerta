package unlv.erc.partiubalada.view;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class CheckLocation extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int INITIAL_ZOOM_LEVEL = 12;
    private GoogleMap map = null;
    private Circle circle = null;
    private GoogleApiClient mGoogleApiClient = null;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i("onConnected", "ENTROOOOOU");

        // Request permissions to support Android Marshmallow and above devices
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions();
        }

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

        if (mLastLocation != null) {
            String userLatitude = String.valueOf(mLastLocation.getLatitude());

            Log.i("LATITUDEEEE", userLatitude);
            Toast.makeText(this, userLatitude, Toast.LENGTH_LONG).show();

            float[] distance = new float[2];

            Location.distanceBetween(mLastLocation.getLatitude(), mLastLocation.getLongitude(),
                    circle.getCenter().latitude, circle.getCenter().longitude, distance);

            Dialog locationDialog = new Dialog(CheckLocation.this, R.style.FullHeightDialog);

            locationDialog = new Dialog(CheckLocation.this, R.style.FullHeightDialog);
            locationDialog.setContentView(R.layout.location_checker_dialog);
            locationDialog.setCancelable(true);
            TextView text = (TextView) locationDialog.findViewById(R.id.checker_dialog_text);

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
        } else {
            Toast.makeText(this, "Habilite sua localização, não foi possível verificá-la", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Request permissions to support Android Marshmallow and above devices
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions();
        }

        map = googleMap;
    }

    // START PERMISSION CHECK
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    private void checkPermissions() {
        List<String> permissions = new ArrayList<>();
        String message = "osmdroid permissions:";
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            message += "\nLocation to show user location.";
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            message += "\nStorage access to store map tiles.";
        }
        if (!permissions.isEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            String[] params = permissions.toArray(new String[permissions.size()]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(params, REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
        } // else: We already have permissions, so handle as normal

        this.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initial
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION and WRITE_EXTERNAL_STORAGE
                Boolean location = perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                Boolean storage = perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                if (location && storage) {
                    // All Permissions Granted
                    Toast.makeText(CheckLocation.this, "All permissions granted", Toast.LENGTH_SHORT).show();
                } else if (location) {
                    Toast.makeText(this, "Storage permission is required to store map tiles to reduce data usage and for offline usage.", Toast.LENGTH_LONG).show();
                } else if (storage) {
                    Toast.makeText(this, "Location permission is required to show the user's location on map.", Toast.LENGTH_LONG).show();
                } else { // !location && !storage case
                    // Permission Denied
                    Toast.makeText(CheckLocation.this, "Storage permission is required to store map tiles " +
                            "to reduce data usage and for offline usage." +
                            "\nLocation permission is required to show the user's location on map.", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    // END PERMISSION CHECK

}
