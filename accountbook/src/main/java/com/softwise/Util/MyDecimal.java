package com.softwise.Util;

import java.text.DecimalFormat;

/**
 * Created by softwise on 2017/1/4.
 */

public class MyDecimal {
    //保留double小数点后两位
    public static String pointTwo(Double d) {
        DecimalFormat decimalFormat = new DecimalFormat(".##");
        String str = decimalFormat.format(d);
        return str;
    }

}
