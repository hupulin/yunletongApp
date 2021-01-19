package com.fskj.applibrary.util;

import android.widget.TextView;

/**
 * Created by xzz on 2019/5/18.
 */

public class FormatUtil {

    public static String getCommission(float commission){

        return ("佣金 "+String.format("%.2f", commission/100)+"%");
    }

    public static String getCommission2(float commission){

        return ("佣金 "+String.format("%.2f", commission*100)+"%");
    }
    public static void   setPrice(TextView left,TextView right,String price){
        if (price != null && price.contains(".")) {
            left.setText(Integer.valueOf(price.split("\\.")[0]) / 100 + "");
           right.setText(price.split("\\.")[1]);
        } else
           left.setText(Integer.valueOf(price) / 100 + "");
    }
}
