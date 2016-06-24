package com.vjimbei.backbase_hackathon_android.ui.utils;

import android.content.SharedPreferences;

import com.google.inject.Inject;
import com.vjimbei.backbase_hackathon_android.BuildConfig;

/**
 * Created by vjimbei on 6/23/2016.
 */
public class ApplicationPreferences {
    public static final String PREFS_NAME = "BackbasePreferences";
    private static final String APP_PACKAGE = BuildConfig.APPLICATION_ID;
    private static final String UNLOCK_LAST_DATE = APP_PACKAGE + "LAST_UNLOCK_DATE";
    private static final String UNLOCK_COUNT = APP_PACKAGE + "UNLOCK_COUNT";


    @Inject
    private SharedPreferences mPreferences;

    @Inject
    public ApplicationPreferences() {
    }

    public String getUnlockLastDate() {
        return mPreferences.getString(UNLOCK_LAST_DATE, "");
    }

    public void setUnlockLastDate(String date) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(UNLOCK_LAST_DATE, date);
        editor.apply();
    }

    public void setUnlockCount(int count) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(UNLOCK_LAST_DATE, count);
        editor.apply();
    }

    public int getUnlockCount() {
        return mPreferences.getInt(UNLOCK_COUNT, 0);
    }
}
