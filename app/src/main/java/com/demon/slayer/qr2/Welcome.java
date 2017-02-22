package com.demon.slayer.qr2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SUJOY GHOSH on 04-12-2016.
 */
public class Welcome extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    RequestQueue requestQueue;
static TextView badge;
DataBaseHandler db;
    String url="http://test.amylife.in/assist.php";
    table t;
String phoneNo;
    SharedPreferences prefs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
badge=(TextView)findViewById(R.id.textBadge);
        badge.setVisibility(View.INVISIBLE);
        db=new DataBaseHandler(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        viewPager=(ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));
        tabLayout=(TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        prefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        phoneNo = prefs.getString("phno", null);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.basket2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DataBase=new Intent(getApplicationContext(),ConnectingDatabase.class);
                startActivity(DataBase);
                        }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
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
        switch (item.getItemId())
        {
            case R.id.assistant_call:

                Toast.makeText(this, "Calling please wait....", Toast.LENGTH_SHORT).show();

                callForAssistance();
                return true;
            case R.id.conatct_us:
                Intent contactUs=new Intent(this,ContactUs.class);
                startActivity(contactUs);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
    private void callForAssistance() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Connection error ", Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override   //getParams Method
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                t= db.getTable();
                parameters.put("TableNo",t.getTableid());
                parameters.put("mnum",phoneNo);
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


    private class CustomAdapter extends FragmentPagerAdapter {
        private String fragments []= {"Soup","veg-starter","non-veg starter","Pasta","drinks"};
        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new fragment1();
                case 1:
                    return new fragment2();
                case 2:
                    return new fragment3();
                case 3:
                    return new fragment4();
                case 4:
                    return new fragment5();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }
}

