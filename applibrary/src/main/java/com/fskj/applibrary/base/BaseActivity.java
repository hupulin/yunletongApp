package com.fskj.applibrary.base;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.fskj.applibrary.base.photopick.PhotoPickerActivity;
import com.fskj.applibrary.base.photopick.PhotoPreviewActivity;
import com.fskj.applibrary.base.photopick.SelectModel;
import com.fskj.applibrary.base.photopick.intent.PhotoPickerIntent;
import com.fskj.applibrary.base.photopick.intent.PhotoPreviewIntent;
import com.fskj.applibrary.base.view.RecycleViewHeadView;
import com.fskj.applibrary.domain.MessageTo;

import com.fskj.applibrary.impl.FragmentPermissionListener;
import com.fskj.applibrary.impl.PermissionListener;
import com.fskj.applibrary.util.GetPathFromUri;
import com.fskj.applibrary.util.GlideCircleTransform;
import com.fskj.applibrary.util.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by xzz on 2018/8/14.
 */

public class BaseActivity<T> extends FragmentActivity implements FragmentPermissionListener {
    public ArrayList<String> imagePaths = new ArrayList<>();//本地file文件
    public ArrayList<String> imagePathsHttps = new ArrayList<>();//上传https路径
    public ArrayList<String> imageEdit = new ArrayList<>();//编辑传过来的
    protected GridLayout pictureLayout;
    protected Context appContext;
    public View nodata;
    protected UserInfoHelp userInfoHelp = new UserInfoHelp();
    protected UserInfoTo userInfoTo;
    public BaseAdapter baseAdapter;
    public LRecyclerView mRecycleView;
    private int maxNum;
    protected ImageView singleImage;
    protected View headView;
    private String imagePathString;
    private int photoWidth;
    protected ImageView mVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        StatueBarUtil.setStatueBarColor(getWindow(), "#ffffff");
//        StatueBarUtil.setStatueBarTextBlack(getWindow());
        appContext = this;
        userInfoTo = userInfoHelp.getUserInfo();
        ActivityManager.activityList.add(this);
    }

    public void setView(int viewId) {
        setContentView(viewId);
        ButterKnife.bind(this);

    }

    protected void setTitleName(String name) {
        View back = findViewById(R.id.back);
        if (back != null)
            back.setOnClickListener(v -> onBackPressed());
        TextView title = findViewById(R.id.title_name);
        if (title != null)
            title.setText(name);

    }

    protected void setTitleNameBack(String name) {
        TextView title = findViewById(R.id.title_name);
        if (title != null)
            title.setText(name);

    }

    protected void setTitleNameNoBack(String name) {
        View back = findViewById(R.id.back);
        back.setVisibility(View.GONE);

        TextView title = findViewById(R.id.title_name);
        if (title != null)
            title.setText(name);

    }

    protected void setRightName(String name) {

        TextView right = findViewById(R.id.right_name);
        if (right != null)
            right.setText(name);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    public void goToAnimation(int type) {

        switch (type) {
            case 1:
                // 跳转到下一个界面
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case 2:
                // 按返回
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;

            case 3:
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }

    protected void showMessage(String message) {
        Toast.makeText(appContext, message, Toast.LENGTH_LONG).show();
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


    protected void setPostImageLayout(GridLayout itemImageLayout, int width, int num) {
        pictureLayout = itemImageLayout;
        maxNum = num;
        photoWidth = width;
        itemImageLayout.removeAllViews();

        for (int i = 0; i < (imagePaths.size() == num ? num : imagePaths.size() + 1) && i < num; i++) {
            View mView = View.inflate(appContext, R.layout.circle_post_image_item, null);
            ImageView imageView = mView.findViewById(R.id.image_view);
            mView.findViewById(R.id.delete_image).setVisibility((imagePaths.size() == 0 || imagePaths.size() == i) ? View.GONE : View.VISIBLE);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setTag(null);

            Glide.with(appContext).load(i < imagePaths.size() ? imagePaths.get(i) : R.drawable.post_image_default).into(imageView);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = width;
            layoutParams.height = width;
            layoutParams.setMargins(0, 0, (int) (10.0 / 414 * getScreenWidth()), 0);
//            layoutParams.bottomMargin = ((int) (28.0 / 750 * getScreenWidth()));
            mView.setLayoutParams(layoutParams);
            mView.setTag(i);
            mView.setOnClickListener(view -> {
                pictureLayout.setTag(itemImageLayout.getTag());
                getPermissionPhoto(Manifest.permission.CAMERA, view, this);
            });
            mView.findViewById(R.id.delete_image).setOnClickListener(view -> {
                imagePaths.remove(imagePaths.get((Integer) mView.getTag()));
                setPostImageLayout(itemImageLayout, width, num);
            });
            itemImageLayout.addView(mView);
        }
    }


    /**
     * 这个view必须设置tag
     */
    public void getPermissionPhoto(String permission, View view, FragmentPermissionListener permissionListener) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permission).subscribe(grant -> {
            if (grant)
                permissionListener.accept(permission, view);
            else
                permissionListener.refuse(permission);

        });
    }

    @Override
    public void accept(String permission, View view) {
        if (Manifest.permission.CAMERA.equals(permission)) {
            getPermissionPhoto(Manifest.permission.WRITE_EXTERNAL_STORAGE, view, this);


        }
        if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
            getPermissionPhoto(Manifest.permission.READ_EXTERNAL_STORAGE, view, this);
        }
        if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission)) {
            if ((Integer) view.getTag() == imagePaths.size()) {
                PhotoPickerIntent intent = new PhotoPickerIntent(appContext);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true); // 是否显示拍照
                intent.setMaxTotal(maxNum == 0 ? 4 : maxNum); // 最多选择照片数量，默认为6
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent, 10);
            } else {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(appContext);
                intent.setCurrentItem(0);
                intent.setPhotoPaths(imagePaths);
                startActivityForResult(intent, 20);
            }
        }
    }

    @Override
    public void refuse(String permission) {

    }


    protected void disPlayRoundImage(ImageView imageView) {
        Glide.with(appContext).load(R.drawable.post_image_default).transform(new GlideCircleTransform(appContext)).into(imageView);
    }

    protected void disPlayRoundImage(ImageView imageView, String url) {
        Glide.with(appContext).load(url).transform(new GlideCircleTransform(appContext)).placeholder(R.drawable.user_default_icon).into(imageView);

    }

    protected void disPlayRoundImage(ImageView imageView, int url) {
        Glide.with(appContext).load(url).transform(new GlideCircleTransform(appContext)).placeholder(R.drawable.user_default_icon).into(imageView);
    }

    protected void disPlayRoundImage(ImageView imageView, String url, int icon) {
        Glide.with(appContext).load(url).transform(new GlideCircleTransform(appContext)).placeholder(icon).into(imageView);

    }

    protected void displayImage(ImageView imageView, String url) {
        Glide.with(appContext).load(url).into(imageView);
    }

    protected void displayImage(int round, ImageView imageView, String url) {
        Glide.with(appContext).load(url).transform(new GlideRoundTransform(appContext, round / 2)).into(imageView);
    }

    protected void displayImage(ImageView imageView, int url) {
        Glide.with(appContext).load(url).into(imageView);
    }

    protected void displayImage(ImageView imageView, String url, int icon) {
        Glide.with(appContext).load(url).placeholder(icon).error(icon).into(imageView);

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
    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter,boolean refresh,boolean loadMore,View noData) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        this.nodata = noData;
        recycleView.setLayoutManager(new LinearLayoutManager(this));
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

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        if (headView != null)
            lRecyclerViewAdapter.addHeaderView(headView);
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
        recycleView.setAdapter(lRecyclerViewAdapter);

//        recycleView.forceToRefresh();


    }

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter, int count, View footView) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new GridLayoutManager(this, count));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        if (headView != null)
            lRecyclerViewAdapter.addHeaderView(headView);
        if (footView != null)
            lRecyclerViewAdapter.addFooterView(footView);
        recycleView.setAdapter(lRecyclerViewAdapter);

        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter, int count, boolean refresh, boolean loadMore) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new GridLayoutManager(this, count));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        if (headView != null)
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

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter, View footView) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        if (footView != null)
            lRecyclerViewAdapter.addFooterView(footView);
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter, int number) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new GridLayoutManager(this, number));

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter, boolean refresh, boolean loadMore, int number) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new GridLayoutManager(this, number));

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
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


    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter, boolean refresh, boolean loadMore) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
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

    protected void setRecycleViewRefresh(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case 10:
                    imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    if (pictureLayout != null) {

                        setPostImageLayout(pictureLayout, photoWidth, maxNum);
                    } else {
                        singleImage.setTag(null);
                        Glide.with(appContext).load(imagePaths.get(0)).into(singleImage);
                    }


                    break;
                // 预览
                case 20:
                    imagePaths = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    if (pictureLayout != null)
                        setPostImageLayout(pictureLayout, photoWidth, maxNum);
                    else {
                        singleImage.setTag(null);
                        Glide.with(appContext).load(imagePaths.get(0)).into(singleImage);
                    }

                    break;

                case 40:
                    if (mVideoView != null) {
                        String videoPath = GetPathFromUri.getPath(this, data.getData());
                        mVideoView.setTag(null);
                        displayImage(mVideoView, videoPath);
                        mVideoView.setTag(videoPath);
                    }
                    break;
            }
        }
    }

    public void getPermission(String permission, PermissionListener permissionListener) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permission).subscribe(grant -> {
            if (grant)
                permissionListener.accept(permission);
            else
                permissionListener.refuse(permission);

        });
    }

    public void recycleItemClick(View view, int position, T data) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToAnimation(2);
    }

}
