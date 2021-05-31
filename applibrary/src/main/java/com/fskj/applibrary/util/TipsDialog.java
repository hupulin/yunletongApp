package com.fskj.applibrary.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fskj.applibrary.R;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/30 12:42
 * Email:635768909@qq.com
 */
public class TipsDialog {
    private static TextView cancel;
    private static NiftyDialogBuilder dialogBuilder;

    public static Button show(Context context, String title, String confirmContent, String cancelContent){
        dialogBuilder = NiftyDialogBuilder.getInstance(context);
        dialogBuilder.setContentView(R.layout.dialog_alert);
        Button confirm=  dialogBuilder.findViewById(R.id.confirm);
        cancel = dialogBuilder.findViewById(R.id.cancel);
        TextView tipText= dialogBuilder.findViewById(R.id.title);
        confirm.setText(confirmContent);
        cancel.setText(cancelContent);
        tipText.setText(title);
        dialogBuilder.show();
        cancel.setOnClickListener(v -> dialogBuilder.dismiss());
        dialogBuilder.findViewById(R.id.parent).setOnClickListener(v -> dialogBuilder.dismiss());
        return confirm;
    }
    public static NiftyDialogBuilder show(Context context, String title){
        dialogBuilder = NiftyDialogBuilder.getInstance(context);
        dialogBuilder.setContentView(R.layout.dialog_alert);
        cancel = dialogBuilder.findViewById(R.id.cancel);
        TextView tipText= dialogBuilder.findViewById(R.id.title);
        tipText.setText(title);
        dialogBuilder.show();
        cancel.setOnClickListener(v -> dialogBuilder.dismiss());
        dialogBuilder.findViewById(R.id.parent).setOnClickListener(v -> dialogBuilder.dismiss());
        return dialogBuilder;
    }

    public static NiftyDialogBuilder showTitle(Context context, SpannableString title, String titleName){
        dialogBuilder = NiftyDialogBuilder.getInstance(context);
        dialogBuilder.setContentView(R.layout.dialog_alert);
        cancel = dialogBuilder.findViewById(R.id.cancel);
        TextView  tvtTitleName = dialogBuilder.findViewById(R.id.title_name);
        TextView tipText= dialogBuilder.findViewById(R.id.title);
        tipText.setText(title);
        tipText.setGravity(Gravity.START);
        tipText.setMovementMethod(LinkMovementMethod.getInstance());
        tvtTitleName.setText(titleName);
        dialogBuilder.show();
        cancel.setOnClickListener(v -> dialogBuilder.dismiss());
//        dialogBuilder.findViewById(R.id.parent).setOnClickListener(v -> dialogBuilder.dismiss());
        return dialogBuilder;
    }

    public static Button show(Context context, String title, View view){

        dialogBuilder = NiftyDialogBuilder.getInstance(context);
        dialogBuilder.setContentView(R.layout.dialog_alert);
        Button confirm=  dialogBuilder.findViewById(R.id.confirm);
        cancel = dialogBuilder.findViewById(R.id.cancel);
        TextView tipText= dialogBuilder.findViewById(R.id.title);

        tipText.setText(title);
        dialogBuilder.show();
        view.setEnabled(true);
        cancel.setOnClickListener(v -> dialogBuilder.dismiss());

        dialogBuilder.findViewById(R.id.parent).setOnClickListener(v -> dialogBuilder.dismiss());

        return confirm;
    }

    public static Button show(Context context){

        dialogBuilder = NiftyDialogBuilder.getInstance(context);
        dialogBuilder.setContentView(R.layout.dialog_alert);
        Button confirm=  dialogBuilder.findViewById(R.id.confirm);
        cancel = dialogBuilder.findViewById(R.id.cancel);


        dialogBuilder.show();
        cancel.setOnClickListener(v -> dialogBuilder.dismiss());

        dialogBuilder.findViewById(R.id.parent).setOnClickListener(v -> dialogBuilder.dismiss());

        return confirm;
    }
    public static TextView getCancelClick(){
        return cancel;
    }

    public static void dismiss(){
        dialogBuilder.dismiss();
    }
}

