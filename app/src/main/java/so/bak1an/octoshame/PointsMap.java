package so.bak1an.octoshame;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import de.greenrobot.event.EventBus;
import generated.DaoSession;
import generated.Point;
import generated.PointDao;
import so.bak1an.octoshame.event.PointsLoaded;
import so.bak1an.octoshame.rest.PointsApi;

public class PointsMap extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_map);
        setUpMapIfNeeded();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


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


    private void setUpMap() {

        mMap.setMyLocationEnabled(true);

        LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);
        String provider = lm.getBestProvider(new Criteria(), true);
        Location loc = lm.getLastKnownLocation(provider);

        if (provider == null) {
            Toast.makeText(getApplicationContext(), "Lol, gps disabled", Toast.LENGTH_SHORT).show();
        }

        if (loc!=null){
            onLocationChanged(loc);
        }

        App context = ((App)getApplicationContext());
        PointsApi api = context.getPointsApi();
        DaoSession session = context.getDaoSession();
        PointDao dao = session.getPointDao();
        List<Point> points = dao.loadAll();
        for (Point p : points) {
            Log.w("MapActivity", "Adding point from database: + " + p.getTitle());
            mMap.addMarker(new MarkerOptions().position(new LatLng(p.getLat(), p.getLng())).title(p.getTitle()));
        }
        LoadPointsService.startActionLoadPoints(this);
    }

    public void onLocationChanged(Location location) {
        LatLng latlng=new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,(float) 14.6));
    }

    public void onEventMainThread(PointsLoaded event) {
        List<Point> points = event.getPoints();
        for (Point p : points) {
            Log.w("MapActivity", "Adding point from REST: + " + p.getTitle());
            mMap.addMarker(new MarkerOptions().position(new LatLng(p.getLat(), p.getLng())).title(p.getTitle()));
        }
    }
}
