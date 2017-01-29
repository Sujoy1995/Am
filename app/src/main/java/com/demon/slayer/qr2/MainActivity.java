package com.demon.slayer.qr2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mscanner;
DataBaseHandler db;
SharedPreferences prefs;
 String url="http://test.amylife.in/submit.php";
RequestQueue requestQueue;
String phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        phoneNo=prefs.getString("phno",null);
        requestQueue = Volley.newRequestQueue(getApplicationContext());



    }
public void open(View v)
{
    Uri adress= Uri.parse("http://amylife.in/#contact");
    Intent browser= new Intent(Intent.ACTION_VIEW, adress);

    startActivity(browser);
    System.exit(0);
}
    public void scan(View v) {
       /* requestQueue = Volley.newRequestQueue(getApplicationContext().getApplicationContext());*/
        mscanner = new ZXingScannerView(this);
        setContentView(mscanner);
        mscanner.setResultHandler(this);
        mscanner.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
/*
        mscanner.startCamera();
*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.assistant_call:
                Toast.makeText(this, "Calling please wait....", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.conatct_us:
                Intent contactUs = new Intent(this, ContactUs.class);
                startActivity(contactUs);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void handleResult(Result result) {
db=new DataBaseHandler(getApplicationContext());
        db.addTable(new table(result.toString()));
        send_Table_no(result.toString(),phoneNo);
        Toast.makeText(getApplicationContext(), "Welcome to FOOD CLUB you table no. is "+result.toString(),
                Toast.LENGTH_SHORT).show();
        Intent welcome = new Intent(getApplicationContext(), Welcome.class);
        startActivity(welcome);
        mscanner.resumeCameraPreview(this);
    }

    public void send_Table_no(final String tableNo, final String ph) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext().getApplicationContext(), "Connection error ", Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override   //getParams Method
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("TableNo",tableNo);
                parameters.put("mnum",ph);
                return parameters;
            }
        };
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().deleteDatabase("foodManager");


    }

    @Override
    public void onBackPressed() {
        getApplicationContext().deleteDatabase("foodManager");
        System.exit(0);
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}



