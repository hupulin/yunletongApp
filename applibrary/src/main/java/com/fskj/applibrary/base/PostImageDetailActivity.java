package com.fskj.applibrary.base;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.fskj.applibrary.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xzz on 2017/7/1.
 **/

public class PostImageDetailActivity extends BaseActivity {

    private List<String> paths=new ArrayList();
    private ViewPager viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image_detail);
        viewPager = findViewById(R.id.view_pager);


        getIntentData();
    }

    private void getIntentData() {

        String currentPath = getIntent().getStringExtra("CurrentPath");
        paths = getIntent().getStringArrayListExtra("PathList");
//        paths = pathList.contains(";") ? pathList.split(";") : pathList.split(",");
        viewPager.setAdapter(adapter);
        for (int i = 0; i < paths.size(); i++) {
            if (currentPath.equals(paths.get(i))) {
                viewPager.setCurrentItem(i);
                setTitleName(i + 1 + "/" + paths.size());
            }
        }
        setViewPagerListener(paths.size());
    }

    private void setViewPagerListener(int length) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitleName(position + 1 + "/" + length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return paths.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @SuppressLint("NewApi")
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View mView = View.inflate(appContext, R.layout.item_see_image, null);
            PhotoView view = mView.findViewById(R.id.image);
            view.enable();
//            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setTransitionName("LifeImage");
            Glide.with(appContext).load(Constant.IMG_BASE_URL+paths.get(position) + (paths.get(position).contains("Camera") ? "" : "?imageMogr2/thumbnail/500x")).into(view);
            container.addView(mView);
            return mView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };
}