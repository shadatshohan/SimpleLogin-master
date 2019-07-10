package com.mind.simplelogin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mind.simplelogin.Adapter.ViewHolder;
import com.mind.simplelogin.Model.Teacher;
import com.mind.simplelogin.Model.TeacherDetails;

public class AdminViewActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Select Teacher to add course");

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("TeacherLogin");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Teacher, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Teacher, ViewHolder>(
                        Teacher.class, R.layout.teacher_details_layout, ViewHolder.class, mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Teacher model, int position) {

                        viewHolder.setDetails(getApplicationContext(), model.getName(), model.getDesc(), model
                                .getImage());

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                String mTitle=getItem(position).getName();
                                String mDesc=getItem(position).getDesc();
                                String mImage=getItem(position).getImage();

                                Intent intent=new Intent(view.getContext(),SemesterActivity.class);
                                intent.putExtra("title",mTitle);
                                intent.putExtra("description",mDesc);
                                intent.putExtra("image",mImage);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {


                            }
                        });
                        return viewHolder;
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}

