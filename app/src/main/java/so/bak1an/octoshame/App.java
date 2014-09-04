package so.bak1an.octoshame;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import generated.DaoMaster;
import generated.DaoSession;
import retrofit.RestAdapter;
import so.bak1an.octoshame.rest.PointsApi;

public class App extends Application {

    private DaoSession daoSession;
    private PointsApi pointsApi;

    public PointsApi getPointsApi() {
        return pointsApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupDB();
        pointsApi = buildApi();
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
