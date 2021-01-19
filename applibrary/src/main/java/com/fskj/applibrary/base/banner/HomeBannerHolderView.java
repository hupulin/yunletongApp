package com.fskj.applibrary.base.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.fskj.applibrary.util.GlideRoundTransform;


/**
 * Created by xzz on 2017/6/25.
 **/

public class HomeBannerHolderView implements Holder<Integer> {
    private ImageView imageView;
    private int loadSrc;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {

        Glide.with(context).load(data).placeholder(loadSrc).into(imageView);
    }
}
