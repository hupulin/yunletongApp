package com.fskj.applibrary.util;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by xzz on 2016/12/11.
 **/
public class StatueBarUtil {
    public static void setStatueBarTransparent( Window window){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//5.0 全透明实现
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
    }
    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private static int calculateStatusColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }
    public static void setStatueBarNo( Window window){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//5.0 全透明实现
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)

        }
    }
    public static void setStatueBarVideoColor( Window window){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//5.0 全透明实现
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //   window.setStatusBarColor(Color.WHITE);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
    }
    public static void setStatueBarColor( Window window,String color){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//5.0 全透明实现
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_VISIBLE| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //   window.setStatusBarColor(Color.WHITE);
            window.setStatusBarColor(Color.parseColor(color));//calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
    }
    public static void setStatueBarTextColor(Window window){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatueBarTransparent(window);
            //   window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#25293c"));
        }
    }

    public static void setStatueBarTextColor(Window window,boolean white){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (white)
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            else
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }

    }


    public static void setStatueBarTextWhite(Window window){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
    public static void setStatueBarTextBlack(Window window){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    public static int getStatusBarHeight(Context context) {
        //获取res目录
        //getDimensionPixelSize:获取dimens文件夹中的属性对应的属性值
        try {
            //找到系统内部的R文件的内部类dimen的字节码
            Class clazz = Class.forName("com.android.internal.R$dimen");
            //找到字节码身上的属性
            Field field = clazz.getField("status_bar_height");
            //找到属性的值
            int id=(Integer) field.get(clazz.newInstance());
            //将属性值作为id传入方法中获取id对应的值
            int statusBarHeight = context.getResources().getDimensionPixelSize(id);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
