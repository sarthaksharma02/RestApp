package com.example.food_nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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

public class Menu_Item extends AppCompatActivity {

    ArrayList<HashMap<String,String>> arrayList;
    DemoAdapter demoAdpater;

    ListView listView;
    //Handlerclass handlerclass;

    //SparseBooleanArray sparseBooleanArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__item);

        Button button = (Button)findViewById(R.id.btn);
        arrayList = new ArrayList<HashMap<String,String>>();
        listView = (ListView)findViewById(R.id.List2);
        ArrayList<String> Order = new ArrayList<String>();

        Order.add("Apple Juice");
        Order.add("Orange Juice");
        Order.add("Pizza");
/*
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        Order = b.getStringArrayList("list");*/

        //handlerclass = new Handlerclass();
        //handlerclass.execute();

        Intent intent = new Intent(getApplicationContext(), Order_Summary.class);
        ArrayList<String> finalOrder = Order;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putStringArrayList("list", finalOrder);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        Button nutrition = (Button)findViewById(R.id.nutri_btn);

        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Menu_Item.this, NutritionCheck.class);
                startActivity(intent1);
            }
        });

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://192.168.43.19:5000/menu").build();

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
                                                        if (response.isSuccessful()) {
                                                            String myRespose = null;
                                                            try {
                                                                myRespose = response.body().string();
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                            String finalMyRespose = myRespose;
                                                            Menu_Item.this.runOnUiThread(new Runnable() {
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
                                                                            //System.out.println(new Integer(a));

                                                                            arrayList.add(data);
                                                                        }
                                                                        demoAdpater = new DemoAdapter(Menu_Item.this, arrayList);
                                                                        listView.setAdapter(demoAdpater);
                                                                    } catch (JSONException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    }
                                                });
                                            }

        /*

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://192.168.43.19:5000/menu").build();

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
                            Menu_Item.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject reader = new JSONObject(finalMyRespose);
                                        JSONArray restaurant = reader.getJSONArray("Menulist");

                                        for(int i=0; i<restaurant.length(); i++){
                                            JSONObject jsondata = restaurant.getJSONObject(i);
                                            String name = jsondata.getString("Name");

                                            HashMap<String,String> data = new HashMap<>();

                                            data.put("Name",name);
                                            //System.out.println(new Integer(a));

                                            arrayList.add(data);

                                            ListAdapter adapter = new SimpleAdapter(Menu_Item.this, arrayList, R.layout.test1,new String[]{"Name"},new int[]{R.id.checkBox});
                                            listView.setAdapter(adapter);
*/

                                            /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                    if(position==0){
                                                        Intent intent = new Intent(Menu_Item.this, Nutritional_Value.class);
                                                        startActivity(intent);
                                                    }

                                                    if(position==1){
                                                        Intent intent = new Intent(Menu_Item.this, Nutritional_activity1.class);
                                                        startActivity(intent);
                                                    }

                                                    if(position==2){
                                                        Intent intent = new Intent(Menu_Item.this, Nutritional_Activity2.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });*/
                                        });
/*
                                        String[] outputStrArr = new String[arrayList.size()];

                                        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.Layout);
                                         for(int i = 0; i<arrayList.size(); i++){
                                             //HashMap<String, String> a = arrayList.get(i);
                                             //System.out.println(a);
                                             int finalI = i;
                                             checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                     if(isChecked){
                                                         System.out.println("Checked");
                                                         ArrayList<String> output = new ArrayList<>();
                                                         output.add("");
                                                     }
                                                 }
                                             });
                                         }
                                          Intent intent = new Intent(getApplicationContext(), Order_Summary.class);
                                          button.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Bundle b = new Bundle();
                                                  b.putStringArray("arraylist", outputStrArr);
                                                  intent.putExtras(b);
                                                  startActivity(intent);
                                              }
                                          });*/

                                            //checkBox.setOnClickListener(new View.OnClickListener() {
                                              //  @Override
                                                //public void onClick(View v) {
                                                  //  System.out.println("point"+ finalI);
                                                    //Toast.makeText(Menu_Item.this,"Checked",Toast.LENGTH_SHORT).show();
                                                //}
                                            //});


/*                                    }
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
*/



   /* public void onClick(View v) {

        ListView listView = (ListView)findViewById(R.id.List2);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://192.168.43.19:5000/menu").build();

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

                            sparseBooleanArray = new SparseBooleanArray();
                            sparseBooleanArray = listView.getCheckedItemPositions();
                            System.out.println(sparseBooleanArray);
                            for (int i = 0; i < sparseBooleanArray.size(); i++) {
                                System.out.println(i);
                                // Item position in adapter
                                int position = sparseBooleanArray.keyAt(i);
                                // Add sport if it is checked i.e.) == TRUE!

                                ListAdapter adapter = new SimpleAdapter(Menu_Item.this, arrayList, R.layout.linear_layout1,new String[]{"Name"},new int[]{R.id.Itemname});
                                if (sparseBooleanArray.valueAt(i))
                                    arrayList.add((HashMap<String, String>) adapter.getItem(position));
                                String[] outputStrArr = new String[arrayList.size()];

                                for (i = 0; i < arrayList.size(); i++) {
                                    outputStrArr[i] = String.valueOf(arrayList.get(i));
                                }
                                Intent intent = new Intent(getApplicationContext(), Order_Summary.class);

                                // Create a bundle object
                                Bundle b = new Bundle();
                                b.putStringArray("arraylist", outputStrArr);

                                // Add the bundle to the intent.
                                intent.putExtras(b);

                                // start the ResultActivity
                                startActivity(intent);
                            }

                    }
                });
            }
        });

    }*/


/*public class Handlerclass extends AsyncTask<String, String, String> {

    String myRespose = null;
    HashMap<String, String> data = new HashMap<>();

    @Override
    protected String doInBackground(String... strings) {

        okhttp3.OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://192.168.43.19:5000/menu").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        myRespose = response.body().string();
                    } else {
                        myRespose = "error";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return myRespose;
    }

    @Override
    public void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject reader = new JSONObject(s);
            JSONArray restaurant = reader.getJSONArray("Menulist");
            for (int i = 0; i < restaurant.length(); i++) {
                JSONObject jsondata = restaurant.getJSONObject(i);
                String name = jsondata.getString("Name");

                data.put("Name", name);
                arrayList.add(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }*/
    }
}