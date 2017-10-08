package com.qa.materialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by sev_user on 15-Apr-15.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<NavDrawerItem> mNavDrawerItems = Collections.emptyList();
    private LayoutInflater mInflater;
    private Context mContext;

    public NavigationDrawerAdapter(Context mContext, List<NavDrawerItem> mNavDrawerItems) {
        this.mNavDrawerItems = mNavDrawerItems;
        this.mContext = mContext;

        mInflater = LayoutInflater.from(mContext);
    }

    public void delete(int pos) {
        mNavDrawerItems.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = mInflater.inflate(R.layout.nav_drawer_row, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        NavDrawerItem cur = mNavDrawerItems.get(i);
        myViewHolder.title.setText(cur.getTitle());
    }

    @Override
    public int getItemCount() {
        return mNavDrawerItems.size();
    }
}
