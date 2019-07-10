package com.mind.simplelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    ImageView applogo;
    TextView start1,start2;
    Button teacherbtn,studentbtn,adminbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        applogo=(ImageView) findViewById(R.id.app_logo);
        start1=(TextView) findViewById(R.id.app_slogan);
        start2=(TextView) findViewById(R.id.loginas);
        teacherbtn=(Button) findViewById(R.id.teacher_login_btn);
        studentbtn=(Button) findViewById(R.id.student_login_btn);
        adminbtn=(Button) findViewById(R.id.admin_login_btn);

        teacherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActivity.this, TeacherLoginActivity.class);
                startActivity(intent);
             }
        });
        studentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActivity.this, StudentLoginActivity.class);
                startActivity(intent);
            }
        });
        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
