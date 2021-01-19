package com.fskj.applibrary.base.banner;

import android.content.Context;
import android.view.View;
import android.widget.GridLayout;

import com.bigkoo.convenientbanner.holder.Holder;

import java.util.List;




/**
 * Created by xzz on 2017/6/25.
 **/

public class BannerMenuView implements Holder<List<String>> {

    private GridLayout gridLayout;

    @Override
    public View createView(Context context) {
        gridLayout = new GridLayout(context);

       gridLayout.setColumnCount(4);
        return gridLayout;
    }

    @Override
    public void UpdateUI(Context context, int position, List<String> menuList ) {

    }
}
