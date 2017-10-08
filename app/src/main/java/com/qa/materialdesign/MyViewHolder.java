package com.qa.materialdesign;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sev_user on 15-Apr-15.
 */
public class MyViewHolder extends RecyclerView.ViewHolder{

    public TextView title;

    public MyViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
    }
}
