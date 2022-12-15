package com.example.food_nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NutritionCheck extends AppCompatActivity {

    ArrayList<HashMap<String,String>> arrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_check);

        arrayList = new ArrayList<HashMap<String,String>>();
        listView = (ListView)findViewById(R.id.list3);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("ec2-13-234-239-93.ap-south-1.compute.amazonaws.com/menu").build();

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
                            NutritionCheck.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject reader = new JSONObject(finalMyRespose);
                                        JSONArray restaurant = reader.getJSONArray("Menulist");

                                        for (int i = 0; i < restaurant.length(); i++) {
                                            JSONObject jsondata = restaurant.getJSONObject(i);
                                            String name = jsondata.getString("Name");

                                            HashMap<String, String> data = new HashMap<>();

                                            data.put("Name", name);

                                            arrayList.add(data);

                                            ListAdapter adapter = new SimpleAdapter(NutritionCheck.this, arrayList, R.layout.linear_layout1, new String[]{"Name"}, new int[]{R.id.Itemname});
                                            listView.setAdapter(adapter);

                                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                    if(position==0){
                                                        Intent intent = new Intent(NutritionCheck.this, Nutritional_Value.class);
                                                        startActivity(intent);
                                                    }

                                                    if(position==1){
                                                        Intent intent = new Intent(NutritionCheck.this, Nutritional_activity1.class);
                                                        startActivity(intent);
                                                    }

                                                    if(position==2){
                                                        Intent intent = new Intent(NutritionCheck.this, Nutritional_Activity2.class);
                                                        startActivity(intent);
                                                    }

                                                    if (position==3){
                                                        Intent intent = new Intent(NutritionCheck.this, Nutritional_activity1.class);
                                                        startActivity(intent);
                                                    }

                                                    if (position==4){
                                                        Intent intent = new Intent(NutritionCheck.this, Nutritional_Activity2.class);
                                                        startActivity(intent);
                                                    }

                                                    if (position==5){
                                                        Intent intent = new Intent(NutritionCheck.this, Nutritional_Value.class);
                                                        startActivity(intent);
                                                    }

                                                    if (position==6){
                                                        Intent intent = new Intent(NutritionCheck.this, Nutritional_activity1.class);
                                                        startActivity(intent);
                                                    }

                                                    if(position == 7){
                                                        Intent intent = new Intent(NutritionCheck.this, Nutri1.class);
                                                        startActivity(intent);
                                                    }


                                                    if(position == 8){
                                                        Intent intent = new Intent(NutritionCheck.this, Nutri1.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                    catch (JSONException e){
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