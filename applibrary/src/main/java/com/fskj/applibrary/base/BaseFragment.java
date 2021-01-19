package com.fskj.applibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fskj.applibrary.domain.login.UserInfoTo;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.fskj.applibrary.MainApp;
import com.fskj.applibrary.R;
import com.fskj.applibrary.base.adapter.BaseAdapter;
import com.fskj.applibrary.base.view.RecycleViewHeadView;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.impl.PermissionListener;
import com.fskj.applibrary.util.GlideCircleTransform;
import com.fskj.applibrary.util.GlideRoundTransform;


import java.util.List;


/**
 * Created by xzz on 2018/8/15.
 */

public class BaseFragment<T> extends Fragment {
    protected Context appContext= MainApp.appContext;
    public LRecyclerView mRecycleView;
    public BaseAdapter baseAdapter;
    public View nodata;
    protected UserInfoHelp userInfoHelp=new UserInfoHelp();
    protected UserInfoTo userInfoTo;
    protected View headView;
    protected View rootView;
    private String imagePathString;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userInfoTo=userInfoHelp.getUserInfo();
        return super.onCreateView(inflater, container, savedInstanceState);
    }




    public void goToAnimation(int type) {

        switch (type) {
            case 1:
                // 跳转到下一个界面
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case 2:
                // 按返回
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;

            case 3:
               getActivity(). overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }

    protected void showMessage(String message){
        Toast.makeText(appContext,message,Toast.LENGTH_LONG).show();
    }


    protected void disPlayRoundImage(ImageView imageView) {

        Glide.with(appContext).load(R.drawable.post_image_default).transform(new GlideCircleTransform(appContext)).into(imageView);



    }
    protected void disPlayRoundImage(ImageView imageView,String url) {

        Glide.with(appContext).load(url).placeholder(R.drawable.user_default_icon).transform(new GlideCircleTransform(appContext)).into(imageView);

    }
    protected void disPlayRoundImage(ImageView imageView,int url) {

        Glide.with(appContext).load(url).transform(new GlideCircleTransform(appContext)).into(imageView);

    }

    protected void disPlayRoundImage(ImageView imageView,String url,int icon) {

        Glide.with(appContext).load(url).placeholder(icon).transform(new GlideCircleTransform(appContext)).into(imageView);

    }
    public void loadDataSuccess(T data) {

    }

    public void loadDataSuccess(MessageTo data) {

    }

    public void loadDataSuccess(List data, int total) {

    }

    public void loadDataSuccess(List<T> data) {

    }

    protected void submitDataSuccess(T data) {

    }

    protected void displayImage(ImageView imageView,String url) {

        Glide.with(appContext).load(url).into(imageView);



    }
    protected void displayImagePet(ImageView imageView,String url) {

        Glide.with(appContext).load(url).placeholder(R.drawable.pet_glide).into(imageView);
//        Glide.with(appContext).load(R.drawable.pet_glide).into(imageView);



    }
    protected void displayImage(ImageView imageView,int url) {
        imageView.setTag(null);
        Glide.with(appContext).load(url).into(imageView);



    }
    protected void displayImage(ImageView imageView,String url,int icon) {
        imageView.setTag(null);

        Glide.with(appContext).load(url).placeholder(icon).error(icon).into(imageView);



    }

    protected void displayImage(int round,ImageView imageView,String url) {
        imageView.setTag(null);

        Glide.with(appContext).load(url).transform(new GlideRoundTransform(appContext,round/2)).into(imageView);

    }

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
       if (headView!=null)
           lRecyclerViewAdapter.addHeaderView(headView);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setPullRefreshEnabled(false);

        recycleView.setLoadMoreEnabled(false);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter,View footView) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        if (footView!=null)
            lRecyclerViewAdapter.addFooterView(footView);
        recycleView.setAdapter(lRecyclerViewAdapter);

        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }
    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter,int count,boolean refresh,boolean loadMore) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new GridLayoutManager(getActivity(),count));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        if (headView!=null)
            lRecyclerViewAdapter.addHeaderView(headView);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setPullRefreshEnabled(refresh);
        recycleView.setLoadMoreEnabled(loadMore);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }
    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter,boolean refresh,boolean loadMore) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setLoadMoreEnabled(loadMore);
        recycleView.setPullRefreshEnabled(refresh);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter,boolean refresh,boolean loadMore,View noData) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        this.nodata = noData;
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setLoadMoreEnabled(loadMore);
        recycleView.setPullRefreshEnabled(refresh);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }
    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter, int count) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new GridLayoutManager(getActivity(),count));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        if (headView!=null)
            lRecyclerViewAdapter.addHeaderView(headView);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setPullRefreshEnabled(false);
        recycleView.setLoadMoreEnabled(false);

        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter, int count,View footView) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new GridLayoutManager(getActivity(),count));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        if (headView!=null)
            lRecyclerViewAdapter.addHeaderView(headView);
        if (footView!=null)
            lRecyclerViewAdapter.addFooterView(footView);
        recycleView.setAdapter(lRecyclerViewAdapter);

        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter, Boolean refresh) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);

        if (headView!=null)
            lRecyclerViewAdapter.addHeaderView(headView);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setLoadMoreEnabled(refresh);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }

    public void recycleItemClick(View view, int position, T data) {

    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) MainApp.appContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }
    public int getScreenHeight() {
        WindowManager wm = (WindowManager) MainApp.appContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getHeight();
    }
    public void getPermission(String permission,PermissionListener permissionListener) {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(permission).subscribe(grant -> {
            if (grant)
                permissionListener.accept(permission);
            else
                permissionListener.refuse(permission);

        });
    }

    protected void setImageLayout(GridLayout imageLayout, List<Integer> imageList, int width){
        imagePathString = "";
        for (int i=0;i<imageList.size();i++) {
            ImageView imageView = new ImageView(appContext);
            imagePathString=imagePathString+imageList.get(i)+",";
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(appContext).load(imageList.get(i)).into(imageView);
            GridLayout.LayoutParams layoutParams=new GridLayout.LayoutParams();
            layoutParams.height=width*getScreenWidth()/750;
            layoutParams.width=width*getScreenWidth()/750;
            if (i!=3)
                layoutParams.rightMargin=20*getScreenWidth()/750;
            else
                layoutParams.rightMargin=0;
            imageView.setTag(imageList.get(i));
            imageView.setLayoutParams(layoutParams);
            imageView.setOnClickListener(view -> {

                Intent intent = new Intent(appContext, PostImageDetailActivity.class);
                intent.putExtra("CurrentPath", (String) view.getTag());
                if (imagePathString.length()>0)
                    intent.putExtra("PathList", imagePathString.substring(0,imagePathString.length()-1));
//                startActivity(intent);
//                goToAnimation(1);
            });
            imageLayout.addView(imageView);

        }
    }

}
