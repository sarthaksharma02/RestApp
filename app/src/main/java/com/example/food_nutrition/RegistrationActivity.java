package com.example.food_nutrition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistrationActivity extends AppCompatActivity {

    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        EditText name = (EditText) findViewById(R.id.reg_name);
        EditText email = (EditText) findViewById(R.id.reg_email);
        EditText pass = (EditText) findViewById(R.id.reg_pass);
        EditText phone = (EditText) findViewById(R.id.reg_phone);

        Button btn = (Button) findViewById(R.id.regbtn);
/*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name = (EditText) findViewById(R.id.reg_name);
                EditText email = (EditText) findViewById(R.id.reg_email);
                EditText pass = (EditText) findViewById(R.id.reg_pass);
                EditText phone = (EditText) findViewById(R.id.reg_phone);
                EditText confpass = (EditText) findViewById(R.id.reg_confpass);

                String regname = name.getText().toString();
                String regemail = email.getText().toString();
                String regpass = pass.getText().toString();
                String regphone = phone.getText().toString();
                String regconf = confpass.getText().toString();

if(regname != null  && regemail!=null && regpass!=null && regphone!=null && regconf!=null) {


    OkHttpClient okHttpClient = new OkHttpClient();

    RequestBody formbody = new FormBody.Builder().add("NAME", regname).add("EMAIL", regemail).add("PHONE", regphone).add("PASSWORD", regpass).build();

    Request request = new Request.Builder().url("http://192.168.43.19:5000/register").post(formbody).build();
    okHttpClient.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegistrationActivity.this, "Server Not Found", Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegistrationActivity.this, "Server is working fine....",Toast.LENGTH_LONG).show();
                }
            });

        }
    });
}

        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
            }
        });
*/



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regname = name.getText().toString().trim();
                String regemail = email.getText().toString().trim();
                String regpass = pass.getText().toString().trim();
                String regphone = phone.getText().toString();

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

                firebaseAuth.createUserWithEmailAndPassword(regemail, regpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            String userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("users").document(userID);
                            Map<String , Object> user = new HashMap<>();
                            user.put("Fullname",regname);
                            user.put("Email",regemail);
                            user.put("Password",regpass);
                            user.put("Phone",regphone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"Onsuccess: User Profile is created"+ userID);
                                }
                            });

                            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "User not Created"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

                TextView txt = (TextView) findViewById(R.id.reg_text);
                txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }

        }