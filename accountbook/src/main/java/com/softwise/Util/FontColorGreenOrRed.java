package com.softwise.Util;

import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by softwise on 2017/1/4.
 */

public class FontColorGreenOrRed {
    //根据大小给Textview填充颜色
    public static void greenOrRed(TextView textView, Boolean boo){
        if (boo){
            textView.setTextColor(Color.parseColor("#cc0000"));
        }else {
            textView.setTextColor(Color.parseColor("00cc00"));
        }
    }
}
