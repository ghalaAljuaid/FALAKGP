package com.example.falaksign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.falaksign.Planets.Earth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Homepage extends AppCompatActivity {

    private Button button;
    private ImageButton image;
    private ImageButton imagechat;
    private Button postt, pollution;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        getUserData();

        image = (ImageButton) findViewById(R.id.imageButtonBack);
        image.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openSignin();
            }
        });

        imagechat = (ImageButton) findViewById(R.id.imageButtonchatbot);
        imagechat.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openChatbot();
            }
        });


        postt = (Button) findViewById(R.id.postbutton);
        postt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openposts();
            }
        });
        View eventsb = findViewById(R.id.eventsb);
        eventsb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openevents();
            }
        });

        View viewp = findViewById(R.id.profileButton4);
        viewp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openprofile();
            }
        });

        pollution = findViewById(R.id.Pollution);
        pollution.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Earth.class);
                startActivity(intent);
            }
        });

        //more page linking
        ImageButton moree = findViewById(R.id.moreButton4);
        moree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openmore();
            }
        });

    }

    public void openSignin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void openChatbot() {
        Intent intent = new Intent(this, chatbot.class);
        startActivity(intent);

    }


    public void openposts() {
        Intent intent = new Intent(this, posts.class);
        startActivity(intent);

    }

    public void openevents() {
        Intent intent = new Intent(this, events.class);
        startActivity(intent);

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


    private void getUserData() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(auth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}