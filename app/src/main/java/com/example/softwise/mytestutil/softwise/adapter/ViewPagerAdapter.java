package com.example.softwise.mytestutil.softwise.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by softwise on 2016/12/20.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private ArrayList<View> views;
    public ViewPagerAdapter() {
    }

    public ViewPagerAdapter(ArrayList<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
