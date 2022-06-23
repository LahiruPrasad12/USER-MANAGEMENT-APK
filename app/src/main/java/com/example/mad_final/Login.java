package com.example.mad_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_final.DBHandler.Users.UserHandler;
import com.example.mad_final.DBHandler.Users.Users;

import java.util.List;

public class Login extends AppCompatActivity {

    Button loginBTN;
    TextView registerLink;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBTN = findViewById(R.id.button);
        registerLink = findViewById(R.id.registerLink);

        username = findViewById(R.id.email);
        password = findViewById(R.id.pass);


        Intent intent = new Intent(this, Register.class);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    UserHandler db = new UserHandler(Login.this);
                    Boolean result = db.login(username.getText().toString(),password.getText().toString());
                    if(result == true){
                        Toast.makeText(Login.this, "Login success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,Home.class));
                    }else {
                        Toast.makeText(Login.this, "Login Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }

        });
    }
}