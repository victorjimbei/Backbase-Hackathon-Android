package com.vjimbei.backbase_hackathon_android;

import android.app.Application;

import roboguice.RoboGuice;

/**
 * Created by vjimbei on 6/23/2016.
 */
public class BackbaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRoboGuice(this);
    }

    public static void initRoboGuice(Application application) {
        RoboGuice.setUseAnnotationDatabases(false);
        RoboGuice.overrideApplicationInjector(application, RoboGuice.newDefaultRoboModule(application), new BackbaseModule());
    }
}
