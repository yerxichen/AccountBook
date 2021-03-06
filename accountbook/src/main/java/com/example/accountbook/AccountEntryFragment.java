package com.example.accountbook;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.softwise.Util.BitmapConvent;
import com.softwise.db.DBUtil;
import com.softwise.db.MyOpenDBHelper;
import com.softwise.dto.Account;

import java.util.Calendar;
import java.util.List;

import static android.app.Activity.DEFAULT_KEYS_DIALER;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountEntryFragment extends Fragment implements Validator.ValidationListener {
    //要用的变量
    @DecimalMin(value = 0, message = "输入的金额类型有误，必须是数字并且大于0！")
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
    private String accaction, accmoney, acclist, accsay, acctime,accpic;
    private MyOpenDBHelper dbHelper;
    private Context mContext;
    private Calendar calendar;
    private View view;
    private ImageView iv_photo;

    public AccountEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_entry, container, false);
        init();
        //提交事件
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
        //点击图片打开相机
        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picShow();
            }
        });

//        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton radioButton= (RadioButton) findViewById(checkedId);
//                accaction =radioButton.getText().toString();
//            }
//        });
        return view;
    }
    //加载相机，并把拍摄结果显示在页面上
    private void picShow() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, DEFAULT_KEYS_DIALER);
    }

    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        // 其实这里什么都不要做
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Activity.DEFAULT_KEYS_DIALER) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            //获取图片宽和高
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            //目标图片的大小
            int newWidth = 200;
            int newHeight = 200*height/width;
            //计算缩放率，新尺寸除以旧尺寸
            float scaleWith = (float) newWidth / width;
            float scaleHeight = (float) newHeight / height;
            //创建操作图片的matrix对象
            Matrix matrix = new Matrix();
            //缩放图片
            matrix.postScale(scaleWith, scaleHeight);
            //旋转图片 动作
            // matrix.postRotate(90);
            //创建新图片
            Bitmap reBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            //bitmap转变成Drawable
            //BitmapDrawable drawable = new BitmapDrawable(reBitmap);

            //iv_pic.setImageDrawable(drawable);
            iv_photo.setImageBitmap(reBitmap);
        }
    }

    //初始化
    private void init() {
        //绑定数据
        mContext = this.getActivity();
        dbHelper = new MyOpenDBHelper(mContext, "my.db", null, 1);
        et_money = (EditText) view.findViewById(R.id.et_acc_money);
        et_list = (EditText) view.findViewById(R.id.et_acc_list);
        iv_photo= (ImageView) view.findViewById(R.id.iv_acc_photo);
        et_comment = (EditText) view.findViewById(R.id.et_acc_comment);
        rg_group = (RadioGroup) view.findViewById(R.id.rg_acc);
//        rb_wife= (RadioButton) findViewById(R.id.rb_acc_wife);
//        rb_me= (RadioButton) findViewById(R.id.rb_acc_me);
//        rb_other= (RadioButton) findViewById(R.id.rb_acc_other);
        btn_submit = (Button) view.findViewById(R.id.btn_acc_submit);
        //输入验证
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        //把数据存入SQLite数据库
        DBUtil db = new DBUtil(dbHelper);
        //获取单选框选择的项
        rb_checked = (RadioButton) view.findViewById(rg_group.getCheckedRadioButtonId());
        accaction = rb_checked.getText().toString();
        //获取金额
        accmoney = et_money.getText().toString();
        //获取清单
        acclist = et_list.getText().toString();
        //获取图片并转变成string'
        iv_photo.setDrawingCacheEnabled(true);
        Bitmap bitmap = iv_photo.getDrawingCache();
        accpic = BitmapConvent.convertIconToString(bitmap);
        //获取评论
        accsay = et_comment.getText().toString();
        //获取系统时间
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        acctime = String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day);
        Account account = new Account(accaction, Double.valueOf(accmoney), acclist, accsay, acctime,accpic);
        db.add(account);
        Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(mContext);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(mContext, "提交失败，请检查！", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
