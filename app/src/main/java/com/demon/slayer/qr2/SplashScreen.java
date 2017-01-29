package com.demon.slayer.qr2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by SUJOY GHOSH on 10-01-2017.
 */
public class SplashScreen extends Activity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);
        prefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!prefs.getBoolean("UserLoggedIn", false)) {

            startActivity(new Intent(this,PhoneNo.class));
            finish();

        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

}
