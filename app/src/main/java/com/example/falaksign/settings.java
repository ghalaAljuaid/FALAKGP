package com.example.falaksign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button Aboutus = findViewById(R.id.aboutus);
        Button Logout = findViewById(R.id.logout);
        ImageButton homepage=findViewById(R.id.imageButtonback);

        Aboutus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openaboutus();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openlogut();
            }
        });



        //bar buttons

        View home1 = findViewById(R.id.homeButton2);
        home1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openhome();
            }
        });

        View viewp = findViewById(R.id.profileButton3);
        viewp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openprofile();
            }
        });


        View more = findViewById(R.id.moreButton3);
        more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openmore();
            }
        });


    }


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


    public void openaboutus() {
        Intent intent = new Intent(this, aboutus.class);
        startActivity(intent);

    }

    public void openlogut() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}