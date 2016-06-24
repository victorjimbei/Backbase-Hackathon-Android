package com.vjimbei.backbase_hackathon_android;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.vjimbei.backbase_hackathon_android.ui.utils.ApplicationPreferences;
import com.vjimbei.backbase_hackathon_android.ui.utils.DateUtils;

import java.util.Date;

import roboguice.RoboGuice;
import roboguice.receiver.RoboBroadcastReceiver;

/**
 * Created by vjimbei on 6/22/2016.
 */
public class PhoneUnlockedReceiver extends RoboBroadcastReceiver {

    private static final int MAX_COUNT = 3;

    ApplicationPreferences applicationPreferences;
    DateUtils dateUtils;

    @Override
    protected void handleReceive(Context context, Intent intent) {
        super.handleReceive(context, intent);
        Date currentDate = new Date(System.currentTimeMillis());
        dateUtils = new DateUtils();
        applicationPreferences = new ApplicationPreferences(context);
        RoboGuice.getInjector(context).injectMembers(applicationPreferences);

        String currentDateAsString = dateUtils.getDateAsString(currentDate);
        if (currentDateAsString.equals(applicationPreferences.getUnlockLastDate())) {
            int unlockCount = applicationPreferences.getUnlockCount() + 1;
            applicationPreferences.setUnlockCount(unlockCount);
            if (unlockCount < MAX_COUNT) {
                Toast.makeText(context, "Unlocked count : " + unlockCount, Toast.LENGTH_SHORT).show();
            } else {
                int difference = unlockCount - MAX_COUNT;
                Toast.makeText(context, "Unlock excited by : " + difference, Toast.LENGTH_SHORT).show();
            }
        } else {
            applicationPreferences.setUnlockLastDate(currentDateAsString);
        }
    }
}
