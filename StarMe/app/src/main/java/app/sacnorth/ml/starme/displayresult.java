package app.sacnorth.ml.starme;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sachin on 3/18/2015.
 */
public class displayresult extends Activity implements SensorEventListener , LocationListener {
    SensorManager sensorManager;
    Sensor orientationSensor;
    int[] gpsdata;
    float rotate;
    TextView tv;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultdisplay);
       tv=(TextView) findViewById(R.id.tv);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
        }
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //Toast.makeText(getApplicationContext(),orientationSensor.getVendor()+"",Toast.LENGTH_SHORT).show();
        GPSLocation gl= new GPSLocation();

        Toast.makeText(getApplicationContext(),gl.posdata+"---"+this.gpsdata+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_UI);
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }


    @Override
    public void onSensorChanged(SensorEvent se) {
        rotate = -se.values[0];
       tv.setText(rotate+"");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
        sensorManager.unregisterListener(this, orientationSensor);
    }

    @Override
    public void onLocationChanged(Location location) {
        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        int[] gpsdata={lat,lng};

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
