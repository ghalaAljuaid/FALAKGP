package com.example.falaksign;

import static com.example.falaksign.R.id.backhome1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class events extends AppCompatActivity {



    ImageView ssc ;
    ImageView nasa ;
    ImageView spacex ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ssc = findViewById(R.id.ssc);
        nasa = findViewById(R.id.nasa);
        spacex = findViewById(R.id.spacex);
        ssc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://saudispace.gov.sa/news/ju2322/");
            }


        });

        nasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.nasa.gov/");
            }


        });





        spacex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.spacex.com/");
            }
        });





      View home1 = findViewById(R.id.homeButton);
       home1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              openhome();
            }
        });

        View viewp = findViewById(R.id.profileButton);
        viewp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openprofile();
            }
        });


        View more = findViewById(R.id.moreButton);
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

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


}
//homeButton7