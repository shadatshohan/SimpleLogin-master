package com.mind.simplelogin.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mind.simplelogin.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView=itemView;
       itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getAdapterPosition());

           }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               mClickListener.onItemLongClick(view,getAdapterPosition());
              return true;
           }
      });
    }
    public void setDetails(Context ctx, String name, String desc, String image ){
        TextView mtitleTv=mView.findViewById(R.id.rTitleTv);
        TextView mDetailTv=mView.findViewById(R.id.rDescriptionTv);
        ImageView mImageTv=mView.findViewById(R.id.rImageView);

        mtitleTv.setText(name);
        mDetailTv.setText(desc);
        Picasso.get().load(image).into(mImageTv);
    }
    private ViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener)
    {
        mClickListener=clickListener;
    }

}
