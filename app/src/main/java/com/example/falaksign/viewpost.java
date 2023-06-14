package com.example.falaksign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class viewpost extends AppCompatActivity {

    ImageView postImage, likeIV, commentIV;
    EditText commentET;

    boolean issetLikeOrNot = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpost);

        getLikeStatus();

        postImage = findViewById(R.id.imageView9);
        likeIV = findViewById(R.id.imageView10);
        commentIV = findViewById(R.id.imageView11);
        commentET = findViewById(R.id.editTextTextPersonName2);
        TextView titleForPost = findViewById(R.id.titleForPost);
        titleForPost.setText(PostAdapter.postItem.getComment() + "");
        Picasso.get().load(PostAdapter.postItem.getImage()).into(postImage);

        likeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                issetLikeOrNot = !issetLikeOrNot;
                changeLikeStatus();
            }
        });

        commentIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewpost.this, ComentsecActivity.class));
            }
        });

    }

    private void changeLikeStatus() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PostsLike").child(PostAdapter.postItem.getPostID());
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
        hashMap.put("likeStatus", issetLikeOrNot);

        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (issetLikeOrNot) {
                            FirebaseDatabase.getInstance().getReference("Posts")
                                    .child(PostAdapter.postItem.getPostID()).child("likeNumber")
                                    .setValue(PostAdapter.postItem.getLikeNumber() + 1);
                            likeIV.setImageDrawable(getResources().getDrawable(R.drawable.like_2));
                        } else {
                            FirebaseDatabase.getInstance().getReference("Posts")
                                    .child(PostAdapter.postItem.getPostID()).child("likeNumber")
                                    .setValue(PostAdapter.postItem.getLikeNumber() - 1);
                            likeIV.setImageDrawable(getResources().getDrawable(R.drawable.like));
                        }
                    }
                });

    }

    private void getLikeStatus() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PostsLike").child(PostAdapter.postItem.getPostID());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Like like = dataSnapshot.getValue(Like.class);
                        if (like.getUserId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                && like.isLikeStatus()) {
                            issetLikeOrNot = true;
                        }
                    }

                    if (issetLikeOrNot) {
                        likeIV.setImageDrawable(getResources().getDrawable(R.drawable.like_2));
                    } else {
                        likeIV.setImageDrawable(getResources().getDrawable(R.drawable.like));
                    }
                } else {
                    likeIV.setImageDrawable(getResources().getDrawable(R.drawable.like));
                }
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

        reference.push()
                .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(viewpost.this, "تم إضافة التعليق", Toast.LENGTH_SHORT).show();

                        FirebaseDatabase.getInstance().getReference("Posts")
                                .child(PostAdapter.postItem.getPostID()).child("commentNumber")
                                .setValue(PostAdapter.postItem.getCommentNumber() + 1);
                    }
                });
    }

}