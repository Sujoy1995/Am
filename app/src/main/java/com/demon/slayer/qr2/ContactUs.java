package com.demon.slayer.qr2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by SUJOY GHOSH on 06-01-2017.
 */
public class ContactUs extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

setTheme(R.style.contact);
        this.setContentView(R.layout.contact_us);
    }
}
