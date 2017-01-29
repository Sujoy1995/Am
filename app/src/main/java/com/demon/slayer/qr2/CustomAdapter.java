package com.demon.slayer.qr2;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SUJOY GHOSH on 07-12-2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    Context context;
DataBaseHandler db;
   static  List<MyData> my_data;
    int incre=1;
    SharedPreferences prefs;

    RequestQueue requestQueue;
    String url="http://test.amylife.in/order.php";
    TextView pqty;
    String phoneNo;


    public CustomAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv,parent,false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        db=new DataBaseHandler(context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incre=1;
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Confirmation");
                final TextView text = (TextView) dialog.findViewById(R.id.food_display);
                text.setText(my_data.get(position).getFoodname());
/*
                Button dialogButtonCancel = (Button) dialog.findViewById(R.id.cancel);
*/
                Button dialogButtonConfirm = (Button) dialog.findViewById(R.id.confirm);
                Button plus=(Button) dialog.findViewById(R.id.plus);
                Button minus=(Button) dialog.findViewById(R.id.minus);
                pqty=(TextView) dialog.findViewById(R.id.qty);


                // if button is clicked, close the custom dialog
                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        incre++;
                        pqty.setText(""+incre);

                    }
                });
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        incre--;
                        pqty.setText(""+incre);
                                            }
                });
             /*   dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*/
                dialogButtonConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prefs = PreferenceManager
                                .getDefaultSharedPreferences(context);
                        phoneNo = prefs.getString("phno", null);
                        requestQueue = Volley.newRequestQueue(context);
                        db.addFood(new Food(my_data.get(position).getFoodname(),my_data.get(position).getCost(),Integer.parseInt(my_data.get(position).getCost())*incre));
                        send_data_to_server(my_data.get(position).foodname,my_data.get(position).getFood_id());
                       Welcome.badge.setVisibility(View.VISIBLE);
                        Welcome.badge.setText(""+db.countRows());
/*
                        welcome.badge.setText(""+db.countRows());
*/
                        dialog.dismiss();

                    }
                });

                dialog.show();


            }
        });
      holder.fn.setText(my_data.get(position).getFoodname());
        holder.fc.setText(my_data.get(position).getCost());
        holder.fd.setText(my_data.get(position).getDescription());

    }

   /* private void increase() {
        incre++;
        pqty.setText(incre);
    }
    private void decrease() {
incre--;
        pqty.setText(incre);
    }*/
    private void send_data_to_server(final String foodname,final String foodid) {
final table t=db.getTable();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response.toString());
                    Toast.makeText(context,jsonObject.getString("status")+jsonObject.getString("tableid")+jsonObject.getString("id"), Toast.LENGTH_SHORT).show();



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Connection error ", Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override   //getParams Method
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("foodname",foodname);
                parameters.put("id",foodid);
                parameters.put("TableNo", t.getTableid());
                parameters.put("Qty",""+incre);
                parameters.put("mnum",phoneNo);
                return parameters;
            }

        };
        requestQueue.add(stringRequest);

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView fn,fc,fd;
        public ViewHolder(View itemView) {
            super(itemView);
            fn=(TextView) itemView.findViewById(R.id.food_name);
            fc=(TextView) itemView.findViewById(R.id.cost);
            fd=(TextView) itemView.findViewById(R.id.food_desc);


        }
    }
/*public static void setFilter(List<MyData> newList)
{
    my_data=new ArrayList<>();
    my_data.add((MyData) newList);
    CustomAdapter.notifyDataSetChanged();
}*/
}
