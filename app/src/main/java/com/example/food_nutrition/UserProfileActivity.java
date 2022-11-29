package com.example.food_nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

    EditText txt = (EditText) findViewById(R.id.usrprofiletxt);
    Button btn = (Button) findViewById(R.id.searchbtn);
    
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = txt.getText().toString();
            Intent intent = new Intent(UserProfileActivity.this, RestaurantPage.class);
            intent.putExtra("message_key", str);
            startActivity(intent);
        }
    });

    Button btn1 = (Button) findViewById(R.id.usr_logout_btn);

    TextView result = (TextView)findViewById(R.id.txt1);

    // SharedPreferences prf = getSharedPreferences("user_details",MODE_PRIVATE);
    Intent intent = new Intent(UserProfileActivity.this,LoginActivity.class);
    //result.setText("Welcome "+prf.getString("email",null));
    btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            firebaseAuth.signOut();

            startActivity(intent);
            finish();
        }
    });
    }

}