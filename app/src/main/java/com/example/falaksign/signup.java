package com.example.falaksign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class signup extends AppCompatActivity {
    private Button button;
    EditText firstname, lastname, email, username, password;
    RadioGroup radioGroup;
    Button signuup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    //database connection ..
    FirebaseAuth auth;
    FirebaseUser user;

    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button = (Button) findViewById(R.id.signnin);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openmainactivity();
            }
        });


        //Initializing variables
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        radioGroup = (RadioGroup) findViewById(R.id.rgroupp);
        signuup = findViewById(R.id.signuup);
        progressDialog = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            }
        });

        signuup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  radiobutton_validation();
                PerforAuth();
            }
        });


    }

    private void PerforAuth() {

        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String mail = email.getText().toString();
        String user = username.getText().toString();
        String pass = password.getText().toString();
        int isSelected = radioGroup.getCheckedRadioButtonId();

        if (fname.isEmpty()) {
            firstname.setError("فضلاً ادخل الاسم الاول");
        } else if (lname.isEmpty()) {
            lastname.setError("فضلاً ادخل الاسم الأخير");
        } else if (!mail.matches(emailPattern)) {
            email.setError("فضلاً ادخل البريد الالكتروني بشكلٍ صحيح");
        } else if (user.isEmpty()) {
            username.setError("فضلاً ادخل اسم المستخدم");
        } else if (pass.isEmpty() || pass.length() < 6) {
            password.setError("فضلاً ادخل الرمز السري المناسب");
        } else if (isSelected == -1) {
            Toast.makeText(signup.this, "لم تختر كونك مستطلع أو هاوي", Toast.LENGTH_LONG).show();
        } else {

            progressDialog.setMessage("فضلاً انتظر..");
            progressDialog.setTitle("إنشاء حساب");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            auth.createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        //login page
                        auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                saveUserData(fname, lname, mail, user, pass, radioButton.getText().toString(), auth.getCurrentUser().getUid());
                            }
                        });

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(signup.this, "هنالك خطأ, حاول مرة أخرى", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void saveUserData(String fname, String lname, String mail, String user, String pass, String radioB,
                              String id) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("fname", fname);
        hashMap.put("lname", lname);
        hashMap.put("mail", mail);
        hashMap.put("password", pass);
        hashMap.put("image", "");
        hashMap.put("user", user);
        hashMap.put("typeUser", radioB);
        hashMap.put("id", id);

        reference.child(id).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                openmainactivity();
                Toast.makeText(signup.this, "تم إنشاء الحساب بنجاح, تحقق من إيميلك", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void radiobutton_validation() {
        int isSelected = radioGroup.getCheckedRadioButtonId();
        if (isSelected == -1) {
            Toast.makeText(signup.this, "لم تختر كونك مستطلع أو هاوي", Toast.LENGTH_LONG).show();
            return;
        }
    }

    //open log in page
    public void openmainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}