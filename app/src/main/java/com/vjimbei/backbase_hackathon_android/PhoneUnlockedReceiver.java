package com.vjimbei.backbase_hackathon_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.inject.Inject;
import com.vjimbei.backbase_hackathon_android.ui.utils.ApplicationPreferences;
import com.vjimbei.backbase_hackathon_android.ui.utils.DateUtils;

import java.util.Date;

/**
 * Created by vjimbei on 6/22/2016.
 */
public class PhoneUnlockedReceiver extends BroadcastReceiver {

    private static final int MAX_COUNT = 3;

    @Inject
    ApplicationPreferences applicationPreferences;
    @Inject
    DateUtils dateUtils;

    @Override
    public void onReceive(Context context, Intent intent) {
//        Date currentDate = new Date(System.currentTimeMillis());
//        String currentDateAsString = dateUtils.getDateAsString(currentDate);
//        if (currentDateAsString.equals(applicationPreferences.getUnlockLastDate())) {
//            int unlockCount = applicationPreferences.getUnlockCount() + 1;
//            if (unlockCount < MAX_COUNT) {
//                Toast.makeText(context, "Unlocked count : " + unlockCount, Toast.LENGTH_SHORT).show();
//            } else {
//                int difference = unlockCount - MAX_COUNT;
//                Toast.makeText(context, "Unlock excited by : " + difference, Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            applicationPreferences.setUnlockLastDate(currentDateAsString);
//        }
//        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//        if (keyguardManager.isKeyguardSecure()) {


        //phone was unlocked, do stuff here

//        }
    }
}
