package com.example.accountbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.softwise.adapter.AccListViewAdapter;
import com.softwise.db.DBUtil;
import com.softwise.db.MyOpenDBHelper;
import com.softwise.dto.Account;

import java.util.ArrayList;

public class AccountListActivity extends AppCompatActivity {
    //定义变量
    private Context mContext;
    private ArrayList<Account> list;
    private ListView listView;
    private AccListViewAdapter adapter;
    private MyOpenDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        mContext=AccountListActivity.this;
        dbHelper=new MyOpenDBHelper(mContext,"my.db",null,1);
        listView= (ListView) findViewById(R.id.lv_acc_list);
        //取得集合数据
        DBUtil db=new DBUtil(dbHelper);
        list= (ArrayList<Account>) db.excuteAll();
        //加载适配器
        adapter=new AccListViewAdapter(mContext,list);
        listView.setAdapter(adapter);
    }
}
