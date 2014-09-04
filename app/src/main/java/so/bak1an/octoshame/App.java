package so.bak1an.octoshame;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import generated.DaoMaster;
import generated.DaoSession;
import generated.Point;
import retrofit.RestAdapter;
import so.bak1an.octoshame.rest.PointsApi;

public class App extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDB();
        AsyncTask<Void, Void, Void> at = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                List<Point> points = buildApi().listPoints().getPoints();
                Log.e("APP", String.valueOf(points.size()));
                for (Point p : points) {
                    Log.e("POINT", p.getTitle());
                }
                return null;
            }
        };
        at.execute();
    }

    private void setupDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "octoshame_points", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private PointsApi buildApi() {
        return new RestAdapter.Builder()
                .setEndpoint("http://octo-shame.bak1an.so/")
                .build()
                .create(PointsApi.class);
    }

}
