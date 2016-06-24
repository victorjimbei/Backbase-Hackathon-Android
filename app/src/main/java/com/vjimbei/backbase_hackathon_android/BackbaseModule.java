package com.vjimbei.backbase_hackathon_android;

import com.google.inject.AbstractModule;
import com.vjimbei.backbase_hackathon_android.ui.utils.ApplicationPreferences;

import roboguice.inject.SharedPreferencesName;

/**
 * Created by vjimbei on 6/23/2016.
 */
public class BackbaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bindConstant().annotatedWith(SharedPreferencesName.class).to(ApplicationPreferences.PREFS_NAME);

    }
}
