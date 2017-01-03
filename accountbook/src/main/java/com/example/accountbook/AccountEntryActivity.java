package com.example.accountbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Or;
import com.softwise.db.DBUtil;
import com.softwise.db.MyOpenDBHelper;
import com.softwise.dto.Account;

import java.util.Calendar;
import java.util.List;

public class AccountEntryActivity extends AppCompatActivity implements Validator.ValidationListener{
    //要用的变量
    @DecimalMin(value = 0,message = "输入的金额类型有误，必须是数字并且大于0！")
    private EditText et_money;
    @NotEmpty(message = "输入不能为空！")
    private EditText et_list;
    private RadioGroup rg_group;
    private RadioButton rb_checked;
//    private RadioButton rb_me;
//    private RadioButton rb_other;
    private EditText et_comment;
    private Button btn_submit;
    private Validator validator;
    private String accaction,accmoney,acclist,accsay,acctime;
    private MyOpenDBHelper dbHelper;
    private Context mContext;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_entry);
        //绑定数据
        mContext=AccountEntryActivity.this;
        dbHelper=new MyOpenDBHelper(mContext,"my.db",null,1);
        et_money= (EditText) findViewById(R.id.et_acc_money);
        et_list= (EditText) findViewById(R.id.et_acc_list);
        et_comment= (EditText) findViewById(R.id.et_acc_comment);
        rg_group= (RadioGroup) findViewById(R.id.rg_acc);
//        rb_wife= (RadioButton) findViewById(R.id.rb_acc_wife);
//        rb_me= (RadioButton) findViewById(R.id.rb_acc_me);
//        rb_other= (RadioButton) findViewById(R.id.rb_acc_other);
        btn_submit= (Button) findViewById(R.id.btn_acc_submit);
        //输入验证
        validator=new Validator(this);
        validator.setValidationListener(this);
        //提交事件
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

//        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton radioButton= (RadioButton) findViewById(checkedId);
//                accaction =radioButton.getText().toString();
//            }
//        });

    }


    @Override
    public void onValidationSucceeded() {
        //把数据存入SQLite数据库
        DBUtil db=new DBUtil(dbHelper);
        //获取单选框选择的项
        rb_checked= (RadioButton) findViewById(rg_group.getCheckedRadioButtonId());
        accaction=rb_checked.getText().toString();
        //获取金额
        accmoney = et_money.getText().toString();
        //获取清单
        acclist =et_list.getText().toString();
        //获取评论
        accsay=et_comment.getText().toString();
        //获取系统时间
        calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        acctime= String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(day);
        Account account=new Account(accaction,Double.valueOf(accmoney),acclist,accsay,acctime);
        db.add(account);
        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error :errors){
            View view=error.getView();
            String message=error.getCollatedErrorMessage(this);
            if (view instanceof EditText){
                ((EditText) view).setError(message);
            }else {
                Toast.makeText(this,"提交失败，请检查！",Toast.LENGTH_SHORT).show();
            }
        }


    }



}
