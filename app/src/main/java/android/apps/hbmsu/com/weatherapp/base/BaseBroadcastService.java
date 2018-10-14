package android.apps.hbmsu.com.weatherapp.base;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public abstract class BaseBroadcastService extends Service {

    private Handler handler = new Handler();
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            sendBroadCast(actionBroadCast());
            handler.postDelayed(runnableCode, handlerDuration() * 1000);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(runnableCode);
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnableCode);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void sendBroadCast(String action) {
        Intent broadCastIntent = new Intent();
        broadCastIntent.setAction(action);
        sendBroadcast(broadCastIntent);
    }

    public abstract String actionBroadCast();
    public abstract int handlerDuration();
}
