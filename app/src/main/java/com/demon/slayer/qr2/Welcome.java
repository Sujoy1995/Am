package com.demon.slayer.qr2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.view.View;

import com.android.volley.RequestQueue;

/**
 * Created by SUJOY GHOSH on 04-12-2016.
 */
public class Welcome extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        viewPager=(ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));
        tabLayout=(TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        
    /*    Intent intent = getIntent();
        String value = (String) intent.getSerializableExtra("welcome");
        TextView random = (TextView) findViewById(R.id.textview);
        random.setText("Welcome " + value);*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    private class CustomAdapter extends FragmentPagerAdapter {
        private String fragments []= {"Veg","Non veg"};
        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position)
        {
         switch (position)
         {
             case 0:
                 return new fragment1();
             case 1:
                 return new fragment2();
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

