package com.mind.simplelogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mind.simplelogin.Model.Admin;
import com.mind.simplelogin.Model.Teacher;

public class AdminLoginActivity extends AppCompatActivity {
    private EditText aemail, apassword;
    private Button loginbutton;
    private ProgressDialog loadingbar;
    private String parentdbname = "AdminLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        aemail=(EditText)findViewById(R.id.aEmail);
        apassword=(EditText) findViewById(R.id.aPassword);
        loadingbar = new ProgressDialog(this);
        loginbutton = (Button) findViewById(R.id.aLogin);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });
    }

    private void loginuser() {
        String email = aemail.getText().toString();
        String password = apassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            aemail.setError("Please enter your email");
            aemail.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            aemail.setError("Please enter a valid email");
            aemail.requestFocus();
            return;

        }
        else if (TextUtils.isEmpty(password)) {
            apassword.setError("Please enter your password");
            apassword.requestFocus();
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
                    Admin userdata = dataSnapshot.child(parentdbname).child(password).getValue(Admin.class);
                    if (userdata.getEmail().equals(email)) {
                        if (userdata.getPassword().equals(password)) {

                            Toast.makeText(AdminLoginActivity.this, "Admin Logged in successfully", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                            Intent intent = new Intent(AdminLoginActivity.this, AdminViewActivity.class);
                            startActivity(intent);
                        } else {
                            loadingbar.dismiss();
                            Toast.makeText(AdminLoginActivity.this, "password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "Account with this email do not exists", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                        Toast.makeText(AdminLoginActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    loadingbar.dismiss();
                    Toast.makeText(AdminLoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AdminLoginActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

