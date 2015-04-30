package wcu.edu.routerecorder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 *
 * @author Johnathon Malott, John Burrell
 * @version 4/30/2015
 */
public class MapsActivity extends ActionBarActivity implements GoogleMap.OnMarkerClickListener, View.OnClickListener {

    /**
     * Google Map
     */
    private GoogleMap mMap;

    /**
     * @param savedInstanceState Most update map data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up map if not already created.
     */
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

    /**
     * Sets up map to be displayed on screen.
     */
    private void setUpMap() {
        LatLng wcu = new LatLng(35.3102901, -83.1858488);
        CameraUpdate center = CameraUpdateFactory.newLatLng(wcu);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(19);

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.addMarker(new MarkerOptions().position(wcu).title("Cullowhee"));

        //Listens for map clicks
        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }
}
