package com.example.accountbook;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private AccountListFragment frag_list;
    private AccountAdminFragment frag_admin;
    private AccountEntryFragment frag_entry;
    private FragmentManager fragmentManager;
    private RadioButton rb_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        initViews();

    }

    private void initViews() {
        fragmentManager = getFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.rg_bottom);
        radioGroup.setOnCheckedChangeListener(this);
        rb_list= (RadioButton) findViewById(R.id.rb_list);
        rb_list.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId) {
            case R.id.rb_list:
                if (frag_list == null) {
                    frag_list = new AccountListFragment();
                    transaction.add(R.id.fl_content, frag_list);
                } else {
                    transaction.show(frag_list);
                }
                break;
            case R.id.rb_entry:
               // tv_title.setText("记账");
                if (frag_entry == null) {
                    frag_entry = new AccountEntryFragment();
                    transaction.add(R.id.fl_content, frag_entry);
                } else {
                    transaction.show(frag_entry);
                }
                break;
            case R.id.rb_admin:
                //tv_title.setText("存款查询");
                if (frag_admin == null) {
                    frag_admin = new AccountAdminFragment();
                    transaction.add(R.id.fl_content, frag_admin);
                } else {
                    transaction.show(frag_admin);
                }
                break;
        }
        transaction.commit();

    }

    //隱藏所有Fragment
    private void hideAllFragment(FragmentTransaction transaction) {
        if (frag_list != null) {
            transaction.hide(frag_list);
        }
        if (frag_entry != null) {
            transaction.hide(frag_entry);
        }
        if (frag_admin != null) {
            transaction.hide(frag_admin);
        }
    }
}
