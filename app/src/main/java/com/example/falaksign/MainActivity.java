package com.example.falaksign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;

    FirebaseAuth auth;
    EditText emailET, passwordET;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        button1 = (Button) findViewById(R.id.login);
        button2 = (Button) findViewById(R.id.sinup);

        emailET = findViewById(R.id.editTextTextPersonName);
        passwordET = findViewById(R.id.editTextPassword);

        progressDialog = new ProgressDialog(this);

        //homepage page
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                progressDialog.setMessage("فضلاً انتظر..");
                // progressDialog.setTitle("إنشاء حساب");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                String email = emailET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();

                if (!email.isEmpty() && email != null) {
                    if (!password.isEmpty() && password != null) {
                        openhome(email, password);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "أدخل كلمة السر", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "أدخل إسم المستخدم", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //sign up page
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                opensignup();
            }
        });


    }

    public void opensignup() {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

    public void openhome(String email, String password) {

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (auth.getCurrentUser().isEmailVerified()) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(MainActivity.this, Homepage.class);
                                startActivity(intent);
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "تحقق من الإيميل أولا", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "هنالك خطأ تأكد من البيانات وحاول مرة أخرى", Toast.LENGTH_SHORT).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "هنالك خطأ تأكد من البيانات وحاول مرة أخرى", Toast.LENGTH_SHORT).show();

                    }
                });


    }


}