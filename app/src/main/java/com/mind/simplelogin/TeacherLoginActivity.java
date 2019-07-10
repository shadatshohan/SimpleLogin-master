package com.mind.simplelogin;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mind.simplelogin.Model.Prevalent;
import com.mind.simplelogin.Model.Student;
import com.mind.simplelogin.Model.Teacher;

public class TeacherLoginActivity extends AppCompatActivity{

    private EditText temail, tpassword;
    private Button loginbutton;
    private ProgressDialog loadingbar;
    private String parentdbname = "TeacherLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        temail=(EditText)findViewById(R.id.tetEmail);
        tpassword=(EditText) findViewById(R.id.tetPassword);
        loadingbar = new ProgressDialog(this);
        loginbutton = (Button) findViewById(R.id.tbtLogin);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });
    }

    private void loginuser() {
        String email = temail.getText().toString();
        String password = tpassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            temail.setError("Please enter your email");
            temail.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            temail.setError("Please enter a valid email");
            temail.requestFocus();
            return;

        }
        else if (TextUtils.isEmpty(password)) {
            tpassword.setError("Please enter your password");
            tpassword.requestFocus();
            return;
        }
        else {
            loadingbar.setTitle("Login Account");
            loadingbar.setMessage("please wait untill the process is running");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            Allowaccesstoaccount(email, password);
        }
    }

    private void Allowaccesstoaccount(final String email, final String password) {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentdbname).child(password).exists()) {
                    Teacher userdata = dataSnapshot.child(parentdbname).child(password).getValue(Teacher.class);
                    if (userdata.getEmail().equals(email)) {
                        if (userdata.getPassword().equals(password)) {

                            Toast.makeText(TeacherLoginActivity.this, "Teacher Logged in successfully", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                            Intent intent = new Intent(TeacherLoginActivity.this, TeacherViewActivity.class);
                            startActivity(intent);
                        } else {
                            loadingbar.dismiss();
                            Toast.makeText(TeacherLoginActivity.this, "password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TeacherLoginActivity.this, "Account with this email do not exists", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                        Toast.makeText(TeacherLoginActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    loadingbar.dismiss();
                    Toast.makeText(TeacherLoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TeacherLoginActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

