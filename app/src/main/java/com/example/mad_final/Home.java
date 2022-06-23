package com.example.mad_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_final.DBHandler.Users.UserHandler;

public class Home extends AppCompatActivity {

    EditText username;
    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = findViewById(R.id.userN);
        btn1 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, OneUser.class);
                intent.putExtra("username", username.getText().toString());
                startActivity(intent);
            }
        });

    }

    public void deleteUser(View view) {
        try {
            UserHandler db = new UserHandler(Home.this);
            if (username.getText().toString().length() > 0) {
                db.deleteUser(username.getText().toString());
                Toast.makeText(this, "Delete success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}