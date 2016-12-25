package com.demon.slayer.qr2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by SUJOY GHOSH on 10-12-2016.
 */
public class Payment extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
    }
    public void call(View v)
    {
        Intent n=new Intent(this,WbViewAct.class);
        startActivity(n);
    }

}
