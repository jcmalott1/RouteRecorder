package wcu.edu.routerecorder;

import com.google.android.gms.maps.model.LatLng;

/**
 * The app constraints class simply holds all of the static information of our project in one easy
 * to get at, easy to change location.
 *
 * @author Johnathon Malott, John Burrell
 * @version 4/30/2015
 */
public class AppConstraints {
    /** The string containing the name of Cullowhee */
    public static final String CULLOWHEE = "Cullowhee";

    /** The LatLng object that contains the geo-position of WCU */
    public static final LatLng WCU_LOCATION = new LatLng(35.3102901, -83.1858488);

    /** The latitude component of WCU's location */
    public static final double WCU_LAT = 35.3102901;

    /** The longitude component of WCU's location */
    public static final double WCU_LNG = -83.1858488;

    /** The zoom level the map will default to */
    public static final int ZOOM_LEVEL = 19;

    /** The key LAT used in passing intent extras */
    public static final String LAT_KEY = "LAT";

    /** The key LNG used in passing intent extras */
    public static final String LNG_KEY = "LNG";

    /** The key used for the location broadcasts in our application */
    public static final String LOCATION_BROADCAST = "edu.wcu.location_broadcast";

    /** This value holds the width of the Polyline */
    public static final int POLY_WIDTH = 5;
}