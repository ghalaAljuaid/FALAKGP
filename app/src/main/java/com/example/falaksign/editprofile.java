package com.example.falaksign;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class editprofile extends AppCompatActivity {

    CircleImageView imageView;
    EditText firstname2, lastname3,
            email3, username2,
            password2;

    RadioGroup radioGroup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseUser user;
    StorageReference storageReference;

    RadioButton radioButton;

    Button savechanges;

    String photoUri;
    private Uri selectedImage;

    private static final int READ_STORAGE_PERMISSON_CODE = 144;
    private static final int WRITE_STORAGE_PERMISSON_CODE = 144;

    ActivityResultLauncher<Intent> gallaryLauncher;

    RadioButton amateur, explorer;

    String fname, lname,
            mail, username,
            pass,
            userEmail, userPassword;

    Button signnin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        imageView = findViewById(R.id.imageView3);
        firstname2 = findViewById(R.id.firstname2);
        lastname3 = findViewById(R.id.lastname3);
        email3 = findViewById(R.id.email3);
        username2 = findViewById(R.id.username2);
        password2 = findViewById(R.id.password2);
        radioGroup = (RadioGroup) findViewById(R.id.rgroupp);
        amateur = findViewById(R.id.amateur);
        explorer = findViewById(R.id.explorer);

        signnin2 = findViewById(R.id.signnin2);
        signnin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent storageIntent = new Intent();
                storageIntent.setType("image/*");
                storageIntent.setAction(Intent.ACTION_GET_CONTENT);
                gallaryLauncher.launch(storageIntent);
            }
        });

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

        getUserData();

        gallaryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        try {
                            selectedImage = data.getData();
                            imageView.setImageURI(data.getData());
                        } catch (Exception e) {

                        }
                    }
                });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            }
        });

        View home1 = findViewById(R.id.homeButton6);
        home1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openhome();
            }
        });

        savechanges = findViewById(R.id.savechanges);
        savechanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });

        //bottom bar
        View home = findViewById(R.id.homeButton6);
        home1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openhome();
            }
        });

        View viewp = findViewById(R.id.profileButton6);
        viewp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openprofile();
            }
        });


        View more = findViewById(R.id.moreButton6);
        more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openmore();
            }
        });

    }
    ////button bar
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

    private void getUserData() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if (user.getImage().isEmpty() || user.getImage() == null) {
                    imageView.setImageResource(R.drawable.proicon);
                    photoUri = "";
                } else {
                    Picasso.get().load(user.getImage()).into(imageView);
                    photoUri = user.getImage();
                }

                firstname2.setText(user.getFname());
                lastname3.setText(user.getFname());
                email3.setText(user.getMail());
                username2.setText(user.getUser());
                password2.setText(user.getPassword());

                userEmail = user.getMail();
                userPassword = user.getPassword();

                if (user.getTypeUser().equals("هاوي")) {
                    amateur.setChecked(true);
                    explorer.setChecked(false);
                } else {
                    amateur.setChecked(false);
                    explorer.setChecked(true);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void PerforAuth() {

        fname = firstname2.getText().toString();
        lname = lastname3.getText().toString();
        mail = email3.getText().toString();
        username = username2.getText().toString();
        pass = password2.getText().toString();
        int isSelected = radioGroup.getCheckedRadioButtonId();

        if (fname.isEmpty()) {
            firstname2.setError("فضلاً ادخل الاسم الاول");
        } else if (lname.isEmpty()) {
            lastname3.setError("فضلاً ادخل الاسم الأخير");
        } else if (!mail.matches(emailPattern)) {
            email3.setError("فضلاً ادخل البريد الالكتروني بشكلٍ صحيح");
        } else if (username.isEmpty()) {
            username2.setError("فضلاً ادخل اسم المستخدم");
        } else if (pass.isEmpty() || pass.length() < 6) {
            password2.setError("فضلاً ادخل الرمز السري المناسب");
        } else if (isSelected == -1) {
            Toast.makeText(editprofile.this, "لم تختر كونك مستطلع أو هاوي", Toast.LENGTH_LONG).show();
        } else {

            progressDialog.setMessage("فضلاً انتظر..");
            progressDialog.setTitle("إنشاء حساب");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            AuthCredential credential = EmailAuthProvider.getCredential(userEmail, userPassword);

            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                user.updatePassword(pass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            if (selectedImage != null) {
                                                uploadImage();
                                            } else {
                                                updateUserData();
                                            }
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(editprofile.this, "هنالك خطأ حاول مرة أخرى", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(editprofile.this, "هنالك خطأ حاول مرة أخرى", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }

    private void uploadImage() {
        String s = UUID.randomUUID().toString();
        StorageReference sReference = storageReference.child("User Images").child(s);
        sReference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                sReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        photoUri = String.valueOf(uri);
                        updateUserData();
                    }
                });
            }
        });

    }

    private void updateUserData() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(auth.getCurrentUser().getUid());

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("fname", fname);
        hashMap.put("lname", lname);
        hashMap.put("mail", mail);
        hashMap.put("password", pass);
        hashMap.put("image", photoUri);
        hashMap.put("user", username);
        hashMap.put("typeUser", radioButton.getText().toString() + "");
        hashMap.put("id", auth.getCurrentUser().getUid());

        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(editprofile.this, "تم تعديل الحساب بنجاح", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(editprofile.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(editprofile.this, permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(editprofile.this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == READ_STORAGE_PERMISSON_CODE) {
            if (!(grantResults.length > 0 && grantResults[0]
                    == PackageManager.PERMISSION_DENIED)) {
                Toast.makeText(editprofile.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            } else {
                checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, WRITE_STORAGE_PERMISSON_CODE);
            }
        } else if (requestCode == WRITE_STORAGE_PERMISSON_CODE) {
            if (!(grantResults.length > 0 && grantResults[0]
                    == PackageManager.PERMISSION_DENIED)) {
                Toast.makeText(editprofile.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            } else {
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_STORAGE_PERMISSON_CODE);
            }
        }
    }

    /////bar buttons


}
//homeButton3