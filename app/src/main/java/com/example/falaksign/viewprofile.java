package com.example.falaksign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class viewprofile extends AppCompatActivity {

    int postNumber, likeNumber = 0;
    TextView likesrecord, postsrecord, username;
    CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewprofile);

        imageView = findViewById(R.id.imageView13);
        likesrecord = findViewById(R.id.likesrecord);
        postsrecord = findViewById(R.id.postsrecord);
        username = findViewById(R.id.username);

        username.setText(Homepage.user.getFname() + " " + Homepage.user.getLname());

        getCommentAndLikeNumber();

        View editp = findViewById(R.id.editbutton);
        editp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openedit();
            }
        });

        if (Homepage.user.getImage().isEmpty() || Homepage.user.getImage() == null) {
            imageView.setImageResource(R.drawable.proicon);
        } else {
            Picasso.get().load(Homepage.user.getImage()).into(imageView);
        }
        ///bottom bar

        View home1 = findViewById(R.id.homeButton8);
        home1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openhome();
            }
        });

        View viewp = findViewById(R.id.profileButton8);
        viewp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openprofile();
            }
        });


        View more = findViewById(R.id.moreButton8);
        more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openmore();
            }
        });

    }
    //////buttons bar
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

    public void openedit() {
        Intent intent = new Intent(this, editprofile.class);
        startActivity(intent);

    }

    private void getCommentAndLikeNumber() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PostItem postItem = dataSnapshot.getValue(PostItem.class);
                    if (postItem.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        postNumber = postNumber + 1;
                        likeNumber = likeNumber + postItem.getLikeNumber();
                    }
                }

                likesrecord.setText(likeNumber + "");
                postsrecord.setText(postNumber + "");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}