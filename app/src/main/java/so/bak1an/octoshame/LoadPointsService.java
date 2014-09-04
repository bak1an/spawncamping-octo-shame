package so.bak1an.octoshame;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.List;

import de.greenrobot.event.EventBus;
import generated.Point;
import so.bak1an.octoshame.event.PointsLoaded;
import so.bak1an.octoshame.rest.PointsApi;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
*/
public class LoadPointsService extends IntentService {

    private static final String ACTION_LOAD_POINTS = "so.bak1an.octoshame.action.LOAD_POINTS";


    public static void startActionLoadPoints(Context context) {
        if (amIRunning(context)) {
            Log.d("PointsService", "Already running!");
            return;
        }
        Intent intent = new Intent(context, LoadPointsService.class);
        intent.setAction(ACTION_LOAD_POINTS);
        context.startService(intent);
    }

    public LoadPointsService() {
        super("LoadPointsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LOAD_POINTS.equals(action)) {
                handleActionLoadPoints();
            }
        }
    }

    private void handleActionLoadPoints() {
        PointsApi api = ((App)getApplicationContext()).getPointsApi();
        List<Point> points = api.listPoints().getPoints();
        EventBus.getDefault().post(new PointsLoaded(points));
    }

    private static boolean amIRunning(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (LoadPointsService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
