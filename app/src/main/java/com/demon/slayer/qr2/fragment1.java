package com.demon.slayer.qr2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SUJOY GHOSH on 04-12-2016.
 */
public class fragment1 extends Fragment {
    RequestQueue requestQueue;


    String url = "http://192.168.1.106/menu.php";
    RecyclerView recyclerView;
    CardView cardView;
    RecyclerView.LayoutManager layoutManager;

    CustomAdapter adapter;
    List<MyData> data_list;
  //  private static final String TAG = "MyActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        data_list=new ArrayList<>();
        load_from_server();


    }

    private void load_from_server() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray foods = jsonObject.getJSONArray("menu");
                    for (int i = 0; i < foods.length(); i++) {
                        JSONObject food = foods.getJSONObject(i);
                       MyData data=new MyData(food.getString("name"),food.getString("price"),food.getString("description"),food.getString("id"));
                        data_list.add(data);
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Connection error ", Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override   //getParams Method
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();

                parameters.put("foodid", "1");
                return parameters;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag1, container, false);
       // re=(EditText) root.findViewById(R.id.menuVeg);
        recyclerView=(RecyclerView) root.findViewById(R.id.rv1);
        layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new CustomAdapter(getActivity().getApplicationContext(),data_list);
        recyclerView.setAdapter(adapter);

        return root;
    }
}
