package com.example.falaksign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class posts extends AppCompatActivity {
    public Button home;
    private Button addposts;

    RecyclerView postsRecyclerView;

    List<PostItem> postItemList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        postsRecyclerView = findViewById(R.id.postsRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        postsRecyclerView.setLayoutManager(manager);

        home = (Button) findViewById(R.id.backhome1);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openhome();
            }
        });

        addposts = (Button) findViewById(R.id.addimage);
        addposts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Homepage.user.getTypeUser().equals("هاوي")) {
                    openaddpost();
                } else {
                    Toast.makeText(posts.this, "أنت مستطلع لا يمكنك إضافة منشور", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getAllUsers();

    }

    public void openhome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);

    }

    public void openaddpost() {
        Intent intent = new Intent(this, addp.class);
        startActivity(intent);

    }

    private void getAllUsers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }

                getAllPost();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getAllPost() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postItemList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PostItem postItem = dataSnapshot.getValue(PostItem.class);
                    postItemList.add(postItem);
                }

                PostAdapter adapter = new PostAdapter(posts.this, postItemList, userList);
                postsRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}