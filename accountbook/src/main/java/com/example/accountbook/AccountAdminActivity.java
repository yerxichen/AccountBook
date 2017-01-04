package com.example.accountbook;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.Digits;
import com.softwise.Util.MyDecimal;
import com.softwise.db.DBUtil;
import com.softwise.db.MyOpenDBHelper;
import com.softwise.dto.Money;

import java.text.DecimalFormat;
import java.util.Calendar;

public class AccountAdminActivity extends AppCompatActivity {
    private TextView tv_cun;
    private TextView tv_qu;
    private TextView tv_yu;
    private EditText et_dialogAddMoney;
    private Button btn_cun;
    private MyOpenDBHelper dbHelper;
    private Context mContext;
    private Double allCost=0.0;
    private Double allCun=0.0;
    private Calendar calendar;
    private String acctime;
    private Money money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_admin);
        //绑定控件
        tv_cun= (TextView) findViewById(R.id.tv_acc_admin_cun);
        tv_qu= (TextView) findViewById(R.id.tv_acc_admin_qu);
        tv_yu= (TextView) findViewById(R.id.tv_acc_admin_yu);
        btn_cun= (Button) findViewById(R.id.btn_acc_admin_addmoney);
        mContext=AccountAdminActivity.this;

        //连接数据库
        dbHelper=new MyOpenDBHelper(mContext,"my.db",null,1);
        final DBUtil db=new DBUtil(dbHelper);
        //从数据库读取所有数据
        allCost=db.costAll();
        tv_qu.setText(allCost.toString());
        allCun=db.cunAll();
        tv_cun.setText(allCun.toString());
        Double mid=allCun-allCost;
        String yu= MyDecimal.pointTwo(mid);
        tv_yu.setText(yu.toString());
        //给赤字赋予颜色标示
        if (mid<0){
            tv_yu.setTextColor(Color.parseColor("#cc0000"));
        }else {
            tv_yu.setTextColor(mContext.getResources().getColor(R.color.green));
        }

        btn_cun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_dialogAddMoney=new EditText(mContext);
                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                builder.setTitle("请输入存入金额：元");
                builder.setView(et_dialogAddMoney);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取系统时间
                        calendar= Calendar.getInstance();
                        int year=calendar.get(Calendar.YEAR);
                        int month=calendar.get(Calendar.MONTH)+1;
                        int day=calendar.get(Calendar.DAY_OF_MONTH);
                        acctime= String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(day);
                        Double accmoney=Double.valueOf(et_dialogAddMoney.getText().toString());
                        money=new Money(accmoney,acctime);
                        db.addMoney(money);
                        //更新页面信息
                        Double nowCun=Double.valueOf(tv_cun.getText().toString());
                        Double tv_show=nowCun+accmoney;
                        tv_cun.setText(tv_show.toString());
                        Double mid=tv_show-allCost;
                        String yu=MyDecimal.pointTwo(mid);
                        tv_yu.setText(yu.toString());
                        //给赤字赋予颜色标示
                        if (mid<0){
                            tv_yu.setTextColor(Color.parseColor("#cc0000"));
                        }else {
                            tv_yu.setTextColor(mContext.getResources().getColor(R.color.green));
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });

    }
}
