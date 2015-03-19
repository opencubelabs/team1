package app.sacnorth.ml.starme;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Sachin on 3/18/2015.
 */
public class GPSLocation extends Activity implements LocationListener {

        private LocationManager locationManager;
        private String provider;
        int[] posdata;


/** Called when the activity is first created. */

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

  /* Request updates at startup */
        @Override
        protected void onResume() {
            super.onResume();
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }

  /* Remove the locationlistener updates when Activity is paused */
        @Override
        protected void onPause() {
            super.onPause();
            locationManager.removeUpdates(this);
        }

        @Override
        public void onLocationChanged(Location location) {
            int lat = (int) (location.getLatitude());
            int lng = (int) (location.getLongitude());
            int[] ret={lat,lng};
            posdata= ret;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //  TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(this, "Enabled new provider " + provider,
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(this, "Disabled provider " + provider,
                    Toast.LENGTH_SHORT).show();
        }
    }