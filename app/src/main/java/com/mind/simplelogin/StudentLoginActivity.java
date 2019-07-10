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

public class StudentLoginActivity extends AppCompatActivity{
    private EditText stemail, stpassword;
    private Button loginbutton;
    private ProgressDialog loadingbar;
    private String parentdbname = "StudentLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        stemail=(EditText)findViewById(R.id.stEmail);
        stpassword=(EditText) findViewById(R.id.stPassword);
        loadingbar = new ProgressDialog(this);
        loginbutton = (Button) findViewById(R.id.stbtLogin);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });
    }

    private void loginuser() {
        String email = stemail.getText().toString();
        String password = stpassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            stemail.setError("Please enter your email");
            stemail.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            stemail.setError("Please enter a valid email");
            stemail.requestFocus();
            return;

        }
        else if (TextUtils.isEmpty(password)) {
            stpassword.setError("Please enter your password");
            stpassword.requestFocus();
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
                    Student userdata = dataSnapshot.child(parentdbname).child(password).getValue(Student.class);
                    if (userdata.getEmail().equals(email)) {
                        if (userdata.getPassword().equals(password)) {

                                Toast.makeText(StudentLoginActivity.this, "Student Logged in successfully", Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                                Intent intent = new Intent(StudentLoginActivity.this, StudentViewActivity.class);
                                Prevalent.currentonlineuser=userdata;
                                startActivity(intent);
                        } else {
                            loadingbar.dismiss();
                            Toast.makeText(StudentLoginActivity.this, "password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(StudentLoginActivity.this, "Account with this email do not exists", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                        Toast.makeText(StudentLoginActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    loadingbar.dismiss();
                    Toast.makeText(StudentLoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentLoginActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

