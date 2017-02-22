package com.demon.slayer.qr2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by SUJOY GHOSH on 08-12-2016.
 */
public class ConnectingDatabase extends Activity {
    EditText dis,l;
Button b;
EditText qtyp;
    TextView dis_total;
    DataBaseHandler db;


    List<Food> foods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_oders);
         db = new DataBaseHandler(this);
        dis = (EditText) findViewById(R.id.food_all);
        l=(EditText)findViewById(R.id.cost);
        qtyp=(EditText)findViewById(R.id.qtyprice);
        foods = db.getAllFoods();
        dis.setText("");
        l.setText("");
        qtyp.setText("");
        b=(Button)findViewById(R.id.order_button);

        for (Food cn : foods) {
            dis.append( cn.getFood() );
            l.append( cn.getCost());
            qtyp.append(""+(cn.getQtyprice()));
            // Writing food to log
            dis.append("\n");
            l.append("\n");
            qtyp.append("\n");


        }

        dis.setEnabled(false);
l.setEnabled(false);
        qtyp.setEnabled(false);
        dis_total=(TextView) findViewById(R.id.display_total);
        dis_total.setText("Total Cost is "+db.addCost());
    }
public void order(View v){
    Toast.makeText(getApplicationContext(), "Order Sent", Toast.LENGTH_SHORT).show();
    Intent pay=new Intent(getApplicationContext(),Status.class);
    startActivity(pay);

    }



}