package com.demon.slayer.qr2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by SUJOY GHOSH on 10-12-2016.
 */
public class Payment extends Activity {
    RadioGroup rg;
    RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
    rg=(RadioGroup)findViewById(R.id.rgrp);

    }
    public void call(View v)
    {
        int seid=rg.getCheckedRadioButtonId();
        rb=(RadioButton)findViewById(seid);
        String nam= (String) rb.getText();
if(nam.equals("paytm"))
{
    Intent n=new Intent(this,WbViewAct.class);
    startActivity(n);

}
        else
{
    Intent n=new Intent(this,ebill.class);
    startActivity(n);

}
    }

}
