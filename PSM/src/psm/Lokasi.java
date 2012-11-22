package psm;

import java.io.IOException;
import java.util.List;

import psm.taxi.R;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import extension.LapisanMap;

public class Lokasi extends MapActivity implements LocationListener {
	
	private static final String TAG = "Lokasi";

	LocationManager locationManager;
	Geocoder geocoder;
	TextView locationText;
	MapView map;	
	MapController mapController;
	Button btnMap, btnTerrain;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posisimap);
        /*
        btnMap = (Button) findViewById(R.id.imageButtonMaps);
        btnMap = (Button) findViewById(R.id.imageButtonTerrain);  
		locationText = (TextView)this.findViewById(R.id.lblLocationInfo);
        */
        map = (MapView)this.findViewById(R.id.mapview);
        map.setBuiltInZoomControls(true);
        mapController = map.getController();
        mapController.setZoom(17);
        
        locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);

        geocoder = new Geocoder(this);
        
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
        	Log.d(TAG, location.toString());
        	this.onLocationChanged(location);	
        }
    }

    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
	}
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

//	@SuppressLint("DefaultLocale")
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
/*		Log.d(TAG, "onLocationChanged with location " + location.toString());
		Displays lat, long, altitude and bearing
		String text = String.format("Lat:\t %f\nLong:\t %f\nAlt:\t %f\nBearing:\t %f", location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getBearing());
		this.locationText.setText(text);
		
		Papar map dalam betuk Street View
		try {

			  Intent streetView = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("google.streetview:cbll=" + (int)location.getLatitude() + ","+ (int) location.getLongitude()+ "&cbp=1,99.56,,1,-5.27&mz=21"));
			  streetView.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			  startActivity(streetView);

		} catch ( Exception ex ) {

			  Toast.makeText(getBaseContext(), "Could not launch Goole Street View. Are you sure it's installed? Launching market...", Toast.LENGTH_LONG).show();
			  Intent intent = new Intent(Intent.ACTION_VIEW, 
			  Uri.parse("market://details?id=com.google.android.street"));
			  startActivity(intent);    

		}*/
		
		try {
			// This gets a list of addresses 
			List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
			for (Address address : addresses) {
				this.locationText.append("\n" + address.getAddressLine(0));
			}
			
			// Convert latitude and longitude into int that the GeoPoint constructor can understand
			int latitude = (int)(location.getLatitude() * 1E6);
			int longitude = (int)(location.getLongitude() * 1E6);

			GeoPoint point = new GeoPoint(latitude,longitude);
			mapController.animateTo(point);
			LapisanMap marker = new LapisanMap(point) ;
			List listOfOverLays = map.getOverlays();
			listOfOverLays.clear();
			listOfOverLays.add(marker);

			map.invalidate();
			
		} catch (IOException e) {
			Log.e("LocateMe", "Could not get Geocoder data", e);

		}
	}
	
	// Toggle On/Off Map View Street/Satellite
/*	public boolean onCreateOptionsMenu(Menu menu){
	    menu.add(0, 0, 0, "imageButtonMaps");
	    menu.add(0, 1, 1, "imageButtonTerrain");
	    return true;
	}

	public boolean onOptionsItemSelected (MenuItem item){
	    switch (item.getItemId()){
	        case 0:
	            map.setStreetView(true);
	        return true;
	        case 1 :
	            map.setSatellite(true);
	        return true;
	    }
	    return false;
	}*/

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}