package com.vjimbei.backbase_hackathon_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.vjimbei.backbase_hackathon_android.ui.utils.ApplicationPreferences;
import com.vjimbei.backbase_hackathon_android.ui.utils.DateUtils;

import java.util.Date;

/**
 * Created by vjimbei on 6/22/2016.
 */
public class PhoneUnlockedReceiver extends BroadcastReceiver {

    ApplicationPreferences applicationPreferences;
    DateUtils dateUtils;

    @Override
    public void onReceive(Context context, Intent intent) {
        Date currentDate = new Date(System.currentTimeMillis());
        dateUtils = new DateUtils();
        applicationPreferences = new ApplicationPreferences(context);

        String currentDateAsString = dateUtils.getDateAsString(currentDate);
        if (currentDateAsString.equals(applicationPreferences.getUnlockLastDate())) {
            int unlockCount = applicationPreferences.getUnlockCount() + 1;
            applicationPreferences.setUnlockCount(unlockCount);
        } else {
            applicationPreferences.setUnlockLastDate(currentDateAsString);
        }
    }
}
