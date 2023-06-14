package com.example.falaksign;

import static com.example.falaksign.R.id.btnGallery;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.UUID;

public class addp extends AppCompatActivity {

    ImageView imgGallery;
    EditText comment;
    Button sharePost;

    String commentText;

    String photoUri;
    private Uri selectedImage;

    private static final int READ_STORAGE_PERMISSON_CODE = 144;
    private static final int WRITE_STORAGE_PERMISSON_CODE = 144;

    ActivityResultLauncher<Intent> gallaryLauncher;

    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseUser user;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addp);

        comment = findViewById(R.id.comment);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

        sharePost = findViewById(R.id.button5);
        sharePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("فضلاً انتظر..");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                commentText = comment.getText().toString().trim();
                if (!commentText.isEmpty() && commentText != null) {
                    if (selectedImage != null) {
                        uploadImage();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(addp.this, "أدخل صورة للمنشور", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(addp.this, "أدخل وصف للمنشور", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imgGallery = findViewById(R.id.imgGallery);
        Button btnGallery = findViewById(R.id.btnGallery);
        View bposts = findViewById(R.id.backposts);
        bposts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openposts();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent storageIntent = new Intent();
                storageIntent.setType("image/*");
                storageIntent.setAction(Intent.ACTION_GET_CONTENT);
                gallaryLauncher.launch(storageIntent);
            }
        });

        gallaryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        try {
                            selectedImage = data.getData();
                            imgGallery.setImageURI(data.getData());
                        } catch (Exception e) {

                        }
                    }
                });

    }

    public void openposts() {
        super.onBackPressed();
    }

    private void uploadImage() {
        String s = UUID.randomUUID().toString();
        StorageReference sReference = storageReference.child("Post Images").child(s);
        sReference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                sReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        photoUri = String.valueOf(uri);
                        savePostData();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(addp.this, "هنالك خطأ حاول مرة أخرى", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void savePostData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");

        String postID = UUID.randomUUID().toString();

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("image", photoUri);
        hashMap.put("comment", commentText);
        hashMap.put("likeNumber", 0);
        hashMap.put("commentNumber", 0);
        hashMap.put("id", auth.getCurrentUser().getUid());
        hashMap.put("postID", postID);

        reference.child(postID).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(addp.this, "تم إضافة المنشور بنجاح", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(addp.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(addp.this, "هنالك خطأ حاول مرة أخرى", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(addp.this, permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(addp.this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == READ_STORAGE_PERMISSON_CODE) {
            if (!(grantResults.length > 0 && grantResults[0]
                    == PackageManager.PERMISSION_DENIED)) {
                Toast.makeText(addp.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            } else {
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_STORAGE_PERMISSON_CODE);
            }
        } else if (requestCode == WRITE_STORAGE_PERMISSON_CODE) {
            if (!(grantResults.length > 0 && grantResults[0]
                    == PackageManager.PERMISSION_DENIED)) {
                Toast.makeText(addp.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }

}