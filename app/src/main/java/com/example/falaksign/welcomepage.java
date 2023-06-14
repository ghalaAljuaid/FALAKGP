package com.example.falaksign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        Button signup = findViewById(R.id.signuup2);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {openlogin ();
            }
        });
        Button login = findViewById(R.id.login2);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                opensignup();
            }
        });
    }
    public void opensignup() {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);

    }
    public void openlogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}