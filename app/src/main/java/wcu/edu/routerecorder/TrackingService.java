package wcu.edu.routerecorder;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

/**
 *
 * @author Johnathon Malott, John Burrell
 * @version 4/30/2015
 */
public class TrackingService extends Service {

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    /**
     *
     */
    //@Override
    public void onLocationChanged(Location location){

    }

    private void broadcastLocation(Location location){

    }
}
