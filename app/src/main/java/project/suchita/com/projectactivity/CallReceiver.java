package project.suchita.com.projectactivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Date;

public class CallReceiver extends PhonecallReceiver {

    @Override
    protected void onIncomingCallReceived(Context ctx, String number, Date start) {
        //
        Log.i("CallReceiver", "onIncomingCallReceived");
    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start) {
        //
        Log.i("CallReceiver", "onIncomingCallAnswered");

    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        //
        Log.i("CallReceiver", "onIncomingCallEnded");

    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        //
        Log.i("CallReceiver", "onOutgoingCallStarted");

    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        //
        Log.i("CallReceiver", "onOutgoingCallEnded");

    }


    public static final int MIN_TIME_REQUEST = 5 * 1000;
    private static Context _context;
    private String provider = LocationManager.GPS_PROVIDER;
    private static LocationManager locationManager;
    private static LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            try {
                String strStatus = "";
                switch (status) {
                    case GpsStatus.GPS_EVENT_FIRST_FIX:
                        strStatus = "GPS_EVENT_FIRST_FIX";
                        break;
                    case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                        strStatus = "GPS_EVENT_SATELLITE_STATUS";
                        break;
                    case GpsStatus.GPS_EVENT_STARTED:
                        strStatus = "GPS_EVENT_STARTED";
                        break;
                    case GpsStatus.GPS_EVENT_STOPPED:
                        strStatus = "GPS_EVENT_STOPPED";
                        break;
                    default:
                        strStatus = String.valueOf(status);
                        break;
                }
                Toast.makeText(_context, "Status: " + strStatus,
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            try {
                Toast.makeText(_context, "***new location***" + location.getLatitude()+","+location.getLongitude(),
                        Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
            }
        }
    };


    @Override
    protected void onMissedCall(Context context, String number, Date start) {
        Toast.makeText(context, "new request received by receiver",
                Toast.LENGTH_SHORT).show();
        _context = context;
        //_intent = intent;
        locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(provider)) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(provider,
                    MIN_TIME_REQUEST, 5, locationListener);
            Location gotLoc = locationManager
                    .getLastKnownLocation(provider);

        } else {
            Toast t = Toast.makeText(context, "please turn on GPS",
                    Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
            Intent settinsIntent = new Intent(
                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            settinsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(settinsIntent);


        }

    }

}