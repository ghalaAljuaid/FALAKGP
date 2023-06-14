package com.example.falaksign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComentsecActivity extends AppCompatActivity {

    EditText commentET;
    RecyclerView commentRecyclerView;

    List<Comment> commentList = new ArrayList<>();
    List<User> userList = new ArrayList<>();
    CommentAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentsec);

        commentET = findViewById(R.id.editTextTextPersonName);

        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        commentRecyclerView.setLayoutManager(manager);

        getAllUsers();

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

                getAllComments();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getAllComments() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PostsComment").child(PostAdapter.postItem.getPostID());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Comment comment = dataSnapshot.getValue(Comment.class);
                    commentList.add(comment);
                }

                adapter = new CommentAdapter(ComentsecActivity.this, commentList, userList);
                commentRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goBackOnClick(View view) {
        super.onBackPressed();
    }

    public void sendCommentOnClick(View view) {

        String comment = commentET.getText().toString().trim();
        if (!comment.isEmpty() && comment != null) {
            saveNewComment(comment);
        } else {
            Toast.makeText(this, "أدخل تعليق قبل الإرسال", Toast.LENGTH_SHORT).show();
        }

    }

    private void saveNewComment(String comment) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PostsComment").child(PostAdapter.postItem.getPostID());
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
        hashMap.put("comment", comment);

        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ComentsecActivity.this, "تم إضافة التعليق", Toast.LENGTH_SHORT).show();

                        FirebaseDatabase.getInstance().getReference("Posts")
                                .child(PostAdapter.postItem.getPostID()).child("commentNumber")
                                .setValue(PostAdapter.postItem.getCommentNumber() + 1);
                    }
                });
    }

}