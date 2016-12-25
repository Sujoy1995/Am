package com.demon.slayer.qr2;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by SUJOY GHOSH on 10-12-2016.
 */
public class WbViewAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        WebView m=(WebView) findViewById(R.id.web);
        m.getSettings().setJavaScriptEnabled(true);
        m.loadUrl("https://paytm.com");
    }
}
