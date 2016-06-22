package com.vjimbei.backbase_hackathon_android;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by vjimbei on 6/22/2016.
 */
public class PhoneUnlockedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

//        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//        if (keyguardManager.isKeyguardSecure()) {

            Toast.makeText(context, "Unlocked", Toast.LENGTH_SHORT).show();
            //phone was unlocked, do stuff here

//        }
    }
}
