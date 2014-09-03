package so.bak1an.octoshame;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import generated.DaoMaster;
import generated.DaoSession;

public class App extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDB();
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

}
