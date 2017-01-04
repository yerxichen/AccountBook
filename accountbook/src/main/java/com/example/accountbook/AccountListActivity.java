package com.example.accountbook;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.softwise.Util.MyDecimal;
import com.softwise.adapter.AccListViewAdapter;
import com.softwise.db.DBUtil;
import com.softwise.db.MyOpenDBHelper;
import com.softwise.dto.Account;
import java.util.ArrayList;
import java.util.Calendar;


public class AccountListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    //定义变量
    private DBUtil db;
    private Context mContext;
    private ArrayList<Account> list;
    private ListView listView;
    private AccListViewAdapter adapter;
    private MyOpenDBHelper dbHelper;
    private ImageView iv_upadate;
    private ImageView iv_del;
    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private String str5;
    private String str6;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private RadioGroup radioGroup;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    //显示本月总共消费，总共存入，和余额
    private TextView tv_cost;
    private TextView tv_cun;
    private Calendar calendar;
    //分页查询
    private Button btn_last;
    private Button btn_next;
    //定义年月日
    private int year;
    private int month;
    private int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        //绑定数据
        mContext=AccountListActivity.this;
        listView= (ListView) findViewById(R.id.lv_acc_list);
        tv_cost= (TextView) findViewById(R.id.tv_acc_qu);
        tv_cun= (TextView) findViewById(R.id.tv_acc_cun);
        btn_last= (Button) findViewById(R.id.btn_acc_lastMonth);
        btn_next= (Button) findViewById(R.id.btn_acc_nextMonth);
        //连接数据库
        dbHelper=new MyOpenDBHelper(mContext,"my.db",null,1);
        db=new DBUtil(dbHelper);
        //得到当月月份
        calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH)+1;
        //根据年，月取得当月消费和当月存入
//        Double monthCost=db.thisMonthCost(year,month);
//        String mCost=MyDecimal.pointTwo(monthCost);
//        tv_cost.setText(mCost);
//        Double monthCun=db.thisMonthAdd(year,month);
//        String mCun=MyDecimal.pointTwo(monthCun);
//        tv_cun.setText(mCun);
        getCun(year,month,db,tv_cost,tv_cun);
        //取得集合数据
        list= (ArrayList<Account>) db.thisMonthList(year,month);
        //加载适配器
        adapter=new AccListViewAdapter(mContext,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month--;
                if (month==0){
                    month=12;
                    year--;
                }
                //取得集合数据
                list= (ArrayList<Account>) db.thisMonthList(year,month);
                //加载适配器
                adapter=new AccListViewAdapter(mContext,list);
                listView.setAdapter(adapter);
                //更新状态栏
                getCun(year,month,db,tv_cost,tv_cun);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month++;
                if (month==13){
                    month=1;
                    year++;
                }
                //取得集合数据
                list= (ArrayList<Account>) db.thisMonthList(year,month);
                //加载适配器
                adapter=new AccListViewAdapter(mContext,list);
                listView.setAdapter(adapter);
                //更新状态栏
                getCun(year,month,db,tv_cost,tv_cun);

            }
        });



    }
    //得到目标月存入的金额，并更新在状态栏
    private static void getCun(int year,int month,DBUtil db,TextView tvCost,TextView tvCun){
        //根据年，月取得当月消费和当月存入
        Double monthCost=db.thisMonthCost(year,month);
        String mCost=MyDecimal.pointTwo(monthCost);
        tvCost.setText(mCost);
        Double monthCun=db.thisMonthAdd(year,month);
        String mCun=MyDecimal.pointTwo(monthCun);
        tvCun.setText(mCun);
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

        //获取listView的item布局，类型为View
        View layout= (View) parent.getChildAt(position);
        //绑定id控件,并获取值
        tv1= (TextView) layout.findViewById(R.id.tv_acc_lv_accid);
        str1="账单："+tv1.getText().toString();
        //绑定action控件，并获取值
        tv2= (TextView) layout.findViewById(R.id.tv_acc_lv_accaction);
        str2="目的："+tv2.getText().toString();
        //绑定money控件，并获取值
        tv3= (TextView) layout.findViewById(R.id.tv_acc_lv_accmoney);
        str3="金额："+tv3.getText().toString();
        //绑定list控件，并获取值
        tv4= (TextView) layout.findViewById(R.id.tv_acc_lv_acclist);
        str4="清单："+tv4.getText().toString();
        //绑定say控件，并获取值
        tv5= (TextView) layout.findViewById(R.id.tv_acc_lv_accsay);
        str5="评论："+tv5.getText().toString();
        //绑定time控件，并获取值
        tv6= (TextView) layout.findViewById(R.id.tv_acc_lv_acctime);
        str6="时间："+tv6.getText().toString();

        //更新功能
        iv_upadate= (ImageView) layout.findViewById(R.id.iv_update);
        iv_upadate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(AccountListActivity.this,"222",Toast.LENGTH_SHORT).show();

                //弹出修改信息
                AlertDialog.Builder updateDialog=new AlertDialog.Builder(AccountListActivity.this);
                final View dialogView= LayoutInflater.from(AccountListActivity.this)
                        .inflate(R.layout.update_account,null);
                updateDialog.setTitle("账单更改");
                updateDialog.setIcon(R.drawable.up1);
                updateDialog.setView(dialogView);
                //把本行数据绑定到自定义的view（update_account.xml）中
                radioGroup= (RadioGroup) dialogView.findViewById(R.id.rg_updateaccount);
                editText1= (EditText) dialogView.findViewById(R.id.et_update1);
                editText2= (EditText) dialogView.findViewById(R.id.et_update2);
                editText3= (EditText) dialogView.findViewById(R.id.et_update3);
                editText1.setText(str3.substring(3));
                editText2.setText(str4.substring(3));
                editText3.setText(str5.substring(3));
                for (int i=0;i<radioGroup.getChildCount();i++){
                    RadioButton radioButton= (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.getText().toString().equals(str2.substring(3))){
                        radioButton.setChecked(true);
                        break;
                    }
                }
                updateDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //把修改完的数据保存至数据库，并更新页面信息
                        //首先更新页面
                        RadioButton radioButton= (RadioButton) dialogView.findViewById(radioGroup.getCheckedRadioButtonId());
                        String accaction,accmoney,acclist,accsay;
                        accaction=radioButton.getText().toString();
                        accmoney=editText1.getText().toString();
                        acclist=editText2.getText().toString();
                        accsay=editText3.getText().toString();
                        tv2.setText(accaction);
                        tv3.setText(accmoney);
                        tv4.setText(acclist);
                        tv5.setText(accsay);
                        //接下来更新数据库
                        //获取id
                        int id=Integer.valueOf(tv1.getText().toString());
                        //连接数据库更新
                        DBUtil db =new DBUtil(dbHelper);
                        Account account=new Account(id,accaction,Double.valueOf(accmoney),acclist,accsay);
                        db.update(account);
                    }
                });
                updateDialog.setNegativeButton("返回", null);
                updateDialog.show();

            }
        });

        //删除功能
        iv_del= (ImageView) layout.findViewById(R.id.iv_del);
        iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialogDel=new AlertDialog.Builder(AccountListActivity.this);
                dialogDel.setTitle("删除账单");
                dialogDel.setIcon(R.drawable.del1);
                dialogDel.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogDel.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBUtil db=new DBUtil(dbHelper);
                        int id=Integer.valueOf(tv1.getText().toString());
                        db.delect(id);
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialogDel.show();


            }
        });

        Dialog dialog=new AlertDialog.Builder(this)
                //标题内容
                .setTitle("账单详情")
                //设置图标
                .setIcon(R.drawable.info1)
                //列表内容，null的类型为Listener
                .setItems(new String[]{str1,str2,str3,str4,str5,str6},null)
                //按钮内容，null的类型为Listener
                .setNegativeButton("返回",null)
                .show();


//        Toast.makeText(mContext,str1+str2+str3+str4+str5+str6,Toast.LENGTH_SHORT).show();


    }
}
