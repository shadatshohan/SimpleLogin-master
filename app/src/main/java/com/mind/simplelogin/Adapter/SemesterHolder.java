package com.mind.simplelogin.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mind.simplelogin.R;

public class SemesterHolder extends RecyclerView.ViewHolder {
    View mView;

    public SemesterHolder(@NonNull View itemView) {
        super(itemView);

        mView=itemView;
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mClickListener.onItemClick(view,getAdapterPosition());
//
//            }
//        });
//        itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                mClickListener.onItemLongClick(view,getAdapterPosition());
//                return true;
//            }
//        });
    }
    public void setDetails(Context ctx, String semester){
        TextView mtitleTv=mView.findViewById(R.id.TitleTv);

        mtitleTv.setText(semester);
        ;
    }
//    private ViewHolder.ClickListener mClickListener;
//    public interface ClickListener{
//        void onItemClick(View view,int position);
//        void onItemLongClick(View view,int position);
//    }
//    public void setOnClickListener(ViewHolder.ClickListener clickListener)
//    {
//        mClickListener=clickListener;
//    }
}

