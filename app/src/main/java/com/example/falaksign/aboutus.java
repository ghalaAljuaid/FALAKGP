package com.example.falaksign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class aboutus extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ImageButton back=findViewById(R.id.imageButtonback6);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openmore();
            }
        });

        //bottom bar
        View home1 = findViewById(R.id.homeButton7);
        home1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openhome();
            }
        });

        View viewp = findViewById(R.id.profileButton7);
        viewp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openprofile();
            }
        });


        View more = findViewById(R.id.moreButton7);
        more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openmore();
            }
        });

    }
    ////bar buttons
    public void openprofile() {
        Intent intent = new Intent(this, viewprofile.class);
        startActivity(intent);

    }

    //open more page
    public void openmore() {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);

    }
    public void openhome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);

    }



}