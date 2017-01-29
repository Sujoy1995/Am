package com.demon.slayer.qr2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

/**
 * Created by SUJOY GHOSH on 10-01-2017.
 */
public class PhoneNo extends Activity {
    SharedPreferences prefs;
    EditText phno;
    String ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_no);
        phno=(EditText)findViewById(R.id.phno);


    }
    public void RegisterNumber(View v){
        prefs = PreferenceManager.getDefaultSharedPreferences(PhoneNo.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("UserLoggedIn", true);
        editor.putString("phno", String.valueOf(phno.getText()));
        editor.commit();
        Intent scan=new Intent(this,MainActivity.class);
        startActivity(scan);
    }
}
