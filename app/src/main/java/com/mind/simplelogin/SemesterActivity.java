package com.mind.simplelogin;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mind.simplelogin.Adapter.SemesterHolder;
import com.mind.simplelogin.Model.Semester;

public class SemesterActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    TextView mTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        mRecyclerView=findViewById(R.id.recyclerVieww);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference("Semester");

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Select Semester for Course");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mTitleTv=findViewById(R.id.TitleTv);

//       String image=getIntent().getStringExtra("image");
//        String title=getIntent().getStringExtra("title");
//        String desc=getIntent().getStringExtra("description");
//
//        mTitleTv.setText(title);
//        mDetailTv.setText(desc);
//        Picasso.get().load(image).into(mImageTv);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Semester, SemesterHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Semester, SemesterHolder>(
                        Semester.class,R.layout.semester_layout,SemesterHolder.class,mRef
                ) {
                    @Override
                    protected void populateViewHolder(SemesterHolder viewHolder, Semester model, int position) {

                        viewHolder.setDetails(getApplicationContext(),model.getSemester());

                    }

//                    @Override
//                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
//                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
//                            @Override
//                            public void onItemClick(View view, int position) {
//                                String mTitle=getItem(position).getTitle();
//                                String mDesc=getItem(position).getDesc();
//                                String mImage=getItem(position).getImage();
//
//                                Intent intent=new Intent(view.getContext(),PostDetailsActivity.class);
//                                intent.putExtra("title",mTitle);
//                                intent.putExtra("description",mDesc);
//                                intent.putExtra("image",mImage);
//                                startActivity(intent);
//                            }
//
//                            @Override
//                            public void onItemLongClick(View view, int position) {
//
//
//                            }
//                        });
//                        return viewHolder;
//                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
