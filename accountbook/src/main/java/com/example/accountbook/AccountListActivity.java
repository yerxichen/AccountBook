package com.example.accountbook;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.softwise.adapter.AccListViewAdapter;
import com.softwise.db.DBUtil;
import com.softwise.db.MyOpenDBHelper;
import com.softwise.dto.Account;
import java.util.ArrayList;


public class AccountListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
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
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //获取listView的item布局，类型为View
        View layout= (View) parent.getChildAt(position);
        //绑定id控件,并获取值
        TextView tv1= (TextView) layout.findViewById(R.id.tv_acc_lv_accid);
        String str1="账单："+tv1.getText().toString();
        //绑定action控件，并获取值
        TextView tv2= (TextView) layout.findViewById(R.id.tv_acc_lv_accaction);
        String str2="目的："+tv2.getText().toString();
        //绑定money控件，并获取值
        TextView tv3= (TextView) layout.findViewById(R.id.tv_acc_lv_accmoney);
        String str3="金额："+tv3.getText().toString();
        //绑定list控件，并获取值
        TextView tv4= (TextView) layout.findViewById(R.id.tv_acc_lv_acclist);
        String str4="清单："+tv4.getText().toString();
        //绑定say控件，并获取值
        TextView tv5= (TextView) layout.findViewById(R.id.tv_acc_lv_accsay);
        String str5="评论："+tv5.getText().toString();
        //绑定time控件，并获取值
        TextView tv6= (TextView) layout.findViewById(R.id.tv_acc_lv_acctime);
        String str6="时间："+tv6.getText().toString();

        //弹出详细信息
        Dialog dialog=new AlertDialog.Builder(this).setTitle("账单详情：")
                .setItems(new String[]{str1,str2,str3,str4,str5,str6},null).setNegativeButton("确定",null).show();
//        Toast.makeText(mContext,str1+str2+str3+str4+str5+str6,Toast.LENGTH_SHORT).show();
    }
}
