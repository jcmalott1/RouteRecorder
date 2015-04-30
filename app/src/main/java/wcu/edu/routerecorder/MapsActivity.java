package wcu.edu.routerecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * The Route Recorder application allows users to record and plot their journey while they are
 * either walking, driving, or cycling.
 *
 * @author Johnathon Malott, John Burrell
 * @version 4/30/2015
 */
public class MapsActivity extends ActionBarActivity implements GoogleMap.OnMarkerClickListener, View.OnClickListener {

    /** Google Map object used for all necessary google maps things */
    private GoogleMap mMap;
    TrackingService tracking;

    /** The button in the XML that is used for saving the current route */
    private Button save;

    /** The button in the XML that is used to load a previously saved route */
    private Button load;

    /** The button in the XML that is used to record a currently in progress route */
    private Button record;

    /** The button in the XML that is used to stop the current route from being recorded */
    private Button stop;

    /** The array list containing all of the LatLng objects */
    private ArrayList<LatLng> locations;

    /** The private instance of the service that monitors a users location */
    private TrackingService tracking;

    /** A boolean variable to keep track of whether or not teh app is recording */
    private Boolean recording;

    /** The broadcast receiver used to catch the current location from the location service */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        /**
         * The method fired when a broadcast is received.
         *
         * @param context The context from the fired broadcast
         * @param intent The intent that is used to send the broadcast
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(AppConstraints.LOCATION_BROADCAST)) {
                double lat = intent.getDoubleExtra(AppConstraints.LAT_KEY, AppConstraints.WCU_LAT);
                double lng = intent.getDoubleExtra(AppConstraints.LNG_KEY, AppConstraints.WCU_LNG);
                LatLng loc = new LatLng(lat, lng);

                locations.add(loc);
                addRouteToMap(loc);
            }
        }
    };

    /**
     * The on create method is used to initialize all of the buttons, and generally get this screen
     * ready for the user.
     *
     * @param savedInstanceState Most update map data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        tracking = new TrackingService(this);
<<<<<<< HEAD
        recording = false;
        locations = new ArrayList<>();

        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);
        record = (Button) findViewById(R.id.record);
        stop = (Button) findViewById(R.id.stop);

        save.setOnClickListener(this);
        load.setOnClickListener(this);
        record.setOnClickListener(this);
        stop.setOnClickListener(this);
=======
>>>>>>> b3a91ff57faba3aa7b271ae8c1602c3796a09a52
    }

    /** The on resume method is called after the activity is back on top of the android stack. */
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /** Sets up map if not already created. */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /** Sets up map to be displayed on screen. */
    private void setUpMap() {
        CameraUpdate center = CameraUpdateFactory.newLatLng(AppConstraints.WCU_LOCATION);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(AppConstraints.ZOOM_LEVEL);

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.addMarker(new MarkerOptions().position(AppConstraints.WCU_LOCATION).title(AppConstraints.CULLOWHEE));

        //Listens for map clicks
        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
    }

    /**
     * The on click method is called whenever any of our buttons are pressed, and the corresponding
     * code executes doing the desired function.
     *
     * @param v The view item that was pressed
     */
    @Override
    public void onClick(View v) {
        if(v == save) {
            if(recording) stopRecording();
        }

        if(v == load) {

        }

        if(v == record) {

        }

        if(v == stop) stopRecording();
    }

    /**
     * Called when a marker has been clicked or tapped.
     *
     * @param marker The marker that was clicked
     * @return true if the listener has consumed the event, false otherwise
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }

    /**
     * This method takes a given point and draws the current route to include it.
     *
     * @param loc The LatLng object to be added to the map
     */
    private void addRouteToMap(LatLng loc) {
        PolylineOptions line = new PolylineOptions();
        line.width(AppConstraints.POLY_WIDTH);
        line.color(Color.RED);

        line.add(loc);
        mMap.addPolyline(line);
    }

    /** This method stop recording the user's current location */
    private void stopRecording() {

    }
}
