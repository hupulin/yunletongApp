package com.example.yule.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/22 16:24
 * Email:635768909@qq.com
 */
public class FragmentAdapter  extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

