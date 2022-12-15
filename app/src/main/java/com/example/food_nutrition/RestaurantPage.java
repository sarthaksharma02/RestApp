package com.example.food_nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestaurantPage extends AppCompatActivity {

    ArrayList<HashMap<String,String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_page);

        String a = "";

        TextView recievetxt = (TextView) findViewById(R.id.resttextid);
        Intent intent = getIntent();

        String str = intent.getStringExtra("message_key");
        recievetxt.setText(str.toUpperCase());

        String al = recievetxt.getText().toString();
        al = al.trim();

        ListView listView = (ListView)findViewById(R.id.List1);

        arrayList=new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        if(al.toUpperCase().equals("JABALPUR")){
            a = "101";
            System.out.println(a);
        }
        else if(al.toUpperCase().equals("BHOPAL")){
            a = "102";
            System.out.println(a);
        }
        else if(al.toUpperCase().equals("INDORE")){
            a = "103";
            System.out.println(a);
        }
        else if(al.toUpperCase().equals("MUMBAI")){
            a = "104";
            System.out.println(a);
        }

        Request request = new Request.Builder().url("ec2-13-234-239-93.ap-south-1.compute.amazonaws.com/list/"+a).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(response.isSuccessful()){
                            String myRespose = null;
                            try {
                                myRespose = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String finalMyRespose = myRespose;
                            RestaurantPage.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject reader = new JSONObject(finalMyRespose);
                                        JSONArray restaurant = reader.getJSONArray("Restaurant");

                                        for(int i=0; i<restaurant.length(); i++){
                                            JSONObject Name = restaurant.getJSONObject(i);
                                            String name = Name.getString("Name");

                                            HashMap<String,String> data = new HashMap<>();

                                            data.put("Name",name);

                                            arrayList.add(data);

                                            ListAdapter adapter = new SimpleAdapter(RestaurantPage.this, arrayList, R.layout.linear_layout,new String[]{"Name"},new int[]{R.id.name});
                                            listView.setAdapter(adapter);

                                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    if(position == 0){
                                                        Intent intent = new Intent(view.getContext(), Menu_Item.class);
                                                        startActivity(intent);
                                                    }
                                                    if(position == 1){
                                                        Intent intent = new Intent(view.getContext(), Menu_Item.class);
                                                        startActivity(intent);
                                                    }
                                                    if(position == 2){
                                                        Intent intent = new Intent(view.getContext(), Menu_Item.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });


            }
        });

    }
}
