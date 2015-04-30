package wcu.edu.routerecorder;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

import com.google.android.gms.maps.model.LatLng;

/**
 *
 * @author Johnathon Malott, John Burrell
 * @version 4/30/2015
 */
public class TrackingService extends Service implements LocationListener {
    Context context;
    private static final long MIN_DISTANCE_UPDATE = 10;
    private static final long MIN_TIME_UPDATE = 60000;
    Location location;
    double lat, lng;

    protected LocationManager locMang;

    public TrackingService(Context context){
        this.context = context;
        getLocation();
    }

    public Location getLocation(){
        boolean isGPSEnabled = false;
        boolean isNetworkEnabled = false;

        try{
            locMang = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locMang.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locMang.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(!isGPSEnabled && !isNetworkEnabled){

            } else {
                if(isGPSEnabled){
                    getPoints(LocationManager.GPS_PROVIDER);
                } else if(isNetworkEnabled){
                    getPoints(LocationManager.NETWORK_PROVIDER);
                }
            }
        }catch(Exception e){}

        return location;
    }

    public void getPoints(String provider){
        if(location == null){
            locMang.requestLocationUpdates(provider,
                    MIN_TIME_UPDATE, MIN_DISTANCE_UPDATE, this);
        }
        if(locMang != null){
            location = locMang.getLastKnownLocation(provider);
            if(location != null){
                doBroadcast(new LatLng(location.getLatitude(), location.getLongitude()));
            }
        }
    }

    public void showSettingAlert(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("GPS settings")
                .setMessage("GPS is not enabled. Go to Settings?")
        .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).show();
    }

    public void doBroadcast(LatLng userLocation){
        Intent i = new Intent();
        i.setAction(AppConstraints.LOCATION_BROADCAST);
        i.putExtra(AppConstraints.LAT_KEY, userLocation.latitude);
        i.putExtra(AppConstraints.LNG_KEY, userLocation.longitude);
        sendBroadcast(i);
    }

    /***/
    @Override
    public void onLocationChanged(Location location){
        doBroadcast(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
