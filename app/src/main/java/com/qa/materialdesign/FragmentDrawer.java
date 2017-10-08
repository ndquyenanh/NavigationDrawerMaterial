package com.qa.materialdesign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sev_user on 15-Apr-15.
 */
public class FragmentDrawer extends Fragment {

    public static String TAG = FragmentDrawer.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter mDrawerAdapter;
    private View containerView;

    private static String[] mTitles;
    private FragmentDrawerListener mDrawerListener;
    private Activity mActivity;

    public FragmentDrawer() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        mDrawerAdapter = new NavigationDrawerAdapter(mActivity, getData());
        mRecyclerView.setAdapter(mDrawerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(mActivity, mRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                mDrawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

        return layout;
    }

    public void setDrawerListener(FragmentDrawerListener drawerListener) {
        mDrawerListener = drawerListener;
    }

    public static List<NavDrawerItem> getData() {

        List<NavDrawerItem> data = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            NavDrawerItem item = new NavDrawerItem();
            item.setTitle(mTitles[i]);
            data.add(item);
        }

        return data;
    }

    public void setup(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = mActivity.findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(mActivity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mActivity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mActivity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
}
