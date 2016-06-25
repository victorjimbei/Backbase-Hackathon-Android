package com.vjimbei.backbase_hackathon_android;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.vjimbei.backbase_hackathon_android.ui.activity.LoginActivity;
import com.vjimbei.backbase_hackathon_android.ui.utils.ApplicationPreferences;

/**
 * Created by vjimbei on 6/22/2016.
 */
public class PhoneUnlockedReceiver extends BroadcastReceiver {

    ApplicationPreferences applicationPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        applicationPreferences = new ApplicationPreferences(context);
        int unlockCount = applicationPreferences.getUnlockCount() + 1;
        applicationPreferences.setUnlockCount(unlockCount);
        if (applicationPreferences.isUnlockTaskActive()) {
            if (unlockCount > applicationPreferences.getUnlockLimit()) {
                sendNotification(context);
            }
        }
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     */
    private void sendNotification(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("You crossed the line!")
                .setContentText("Sorry, you didn't completed your goal. Try again tomorrow")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
