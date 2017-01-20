package com.example.accountbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.softwise.Util.BitmapConvent;
import com.softwise.Util.SingletonPic;

public class ImageShowActivity extends AppCompatActivity {
    private ImageView iv_picShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_show);
        init();
//        Intent intent=getIntent();
//        Bundle bundle=intent.getExtras();
        //String str=intent.getStringExtra("data");
        Bitmap bitmap= BitmapConvent.convertStringToIcon(SingletonPic.getInstance().getPic());
        iv_picShow.setImageBitmap(bitmap);
        iv_picShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
    private void init(){
        iv_picShow= (ImageView) findViewById(R.id.iv_showFull);
    }
}
