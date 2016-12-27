package com.example.softwise.mytestutil;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.softwise.mytestutil.softwise.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private ArrayList<View> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        list=new ArrayList<View>();
        viewPager= (ViewPager) findViewById(R.id.veiwPager);
        LayoutInflater la=getLayoutInflater().from(this);
        list.add(la.inflate(R.layout.image_view,null,false));
        list.add(la.inflate(R.layout.image_view,null,false));
        list.add(la.inflate(R.layout.image_view,null,false));
        pagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        };
        viewPager.setAdapter(pagerAdapter);

    }
}
