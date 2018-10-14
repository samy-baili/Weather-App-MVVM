package android.apps.hbmsu.com.weatherapp.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.apps.hbmsu.com.weatherapp.R;
import android.apps.hbmsu.com.weatherapp.ui.activity.MainActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationUtils {

    public static void createLocalNotification(Context context, String title, String text) {
        String channelID = "notify_001";

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelID)
                .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(title).setSummaryText(text));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationManager != null) {
            NotificationChannel channel = new NotificationChannel(channelID, title, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        if (notificationManager != null)
            notificationManager.notify(0, mBuilder.build());
    }
}
