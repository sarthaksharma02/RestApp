package com.example.food_nutrition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn = (Button) findViewById(R.id.btn_login);
        EditText email = (EditText)findViewById(R.id.logemail);
        EditText pass = (EditText)findViewById(R.id.logpassword);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
/*
        SharedPreferences pref;

        EditText email = (EditText)findViewById(R.id.logemail);
        EditText pass = (EditText)findViewById(R.id.logpassword);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);

        if(pref.contains("email") && pref.contains("password")){
            startActivity(intent);
        }

        String uemail = email.getText().toString();
        String upass = pass.getText().toString();

        Button btn = (Button) findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.43.19:5000/login").build();
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
                                String myResponse = null;
                                try {
                                    myResponse = response.body().string();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                String finalMyResponse = myResponse;
                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            JSONObject object = new JSONObject(finalMyResponse);
                                            JSONArray user = object.getJSONArray("USER");

                                            for(int i = 0; i<user.length(); i++){
                                                JSONObject USER = user.getJSONObject(i);
                                                String Email = USER.getString("EMAIL");
                                                String Pass = USER.getString("PASSWORD");

                                                if((Email != null) && (Pass != null)){
                                                    SharedPreferences.Editor editor = pref.edit();
                                                    editor.putString("email",Email);
                                                    editor.putString("password",Pass);
                                                    editor.commit();
                                                    Toast.makeText(getApplicationContext(), "Login Successful",Toast.LENGTH_SHORT).show();
                                                    startActivity(intent);
                                                }
                                                else {
                                                    Toast.makeText(getApplicationContext(),"Credentials are not valid",Toast.LENGTH_SHORT).show();
                                                }
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
    });
*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regemail = email.getText().toString().trim();
                String regpass = pass.getText().toString().trim();

                if(TextUtils.isEmpty(regemail)){
                    email.setError("Email is requested");
                    return;
                }
                if(TextUtils.isEmpty(regpass)){
                    pass.setError("Password is requested");
                    return;
                }
                if(regpass.length() < 6){
                    pass.setError("Password must be of length 6");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(regemail, regpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login Unsuccessful"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
            finish();
        }

    TextView txt = (TextView) findViewById(R.id.txt_login);
    txt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    });
    }



}