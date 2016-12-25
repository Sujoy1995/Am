package com.demon.slayer.qr2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mscanner;
DataBaseHandler db;

/* String url="http://192.168.43.26/AMY-R/showFood.php";
RequestQueue requestQueue;*/
SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
mscanner.startCamera();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public void handleResult(Result result) {
db=new DataBaseHandler(getApplicationContext());
        Intent welcome = new Intent(getApplicationContext(), Welcome.class);
        Toast.makeText(getApplicationContext(), "Welcome to FOOD CLUB you table no. is "+result.toString(),
                Toast.LENGTH_LONG).show();
        db.addTable(new table(result.toString()));
        /*send_Table_no(tableNo);*/
        startActivity(welcome);
        mscanner.resumeCameraPreview(this);
    }
/*
    private void send_Table_no(final String tableNo) {
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
                parameters.put("TableNo",tableNo );
                return parameters;
            }
        };
        requestQueue.add(stringRequest);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().deleteDatabase("foodManager");


    }

}



