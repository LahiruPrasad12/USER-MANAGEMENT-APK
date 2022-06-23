package com.example.mad_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_final.DBHandler.Users.UserHandler;
import com.example.mad_final.DBHandler.Users.Users;

public class OneUser extends AppCompatActivity {

    TextView tx1,tx2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_user);

        tx1 = findViewById(R.id.userName);
        tx2 = findViewById(R.id.passwords);
        tx1.setVisibility(View.INVISIBLE);
        tx2.setVisibility(View.INVISIBLE);
        String username = getIntent().getStringExtra("username");
        UserHandler user = new UserHandler(this);
        try{
            Users result = user.getSpecificUser(username);
            if(result.getUsername().length()>0){
                tx1.setText(result.getUsername().toString());
                tx2.setText(result.getPassword().toString());
                tx1.setVisibility(View.VISIBLE);
                tx2.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            Toast.makeText(this, "No Such User", Toast.LENGTH_SHORT).show();
        }
    }
}