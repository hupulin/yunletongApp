package com.example.yule.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yule.Constant;
import com.example.yule.R;
import com.fskj.applibrary.base.BaseActivity;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/25 10:46
 * Email:635768909@qq.com
 */
public class StaticWebViewActivity extends BaseActivity {

    private String baseUrl = Constant.BaseUrl;
//   服务协议及隐私政策
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_static);
        WebView webView = findViewById(R.id.web_view);
        setTitleName(getIntent().getStringExtra("Title"));
        WebSettings webSetting = webView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        webSetting.setUseWideViewPort(true); // 将图片调整到适合WebView的大小
        webSetting.setLoadWithOverviewMode(true); // 自适应屏幕
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);

        webView.loadUrl(baseUrl + getIntent().getStringExtra("Url"));
        webView.setHorizontalScrollBarEnabled(false);//去掉webview的滚动条,水平不显示
        webView.setScrollbarFadingEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);


            }
        });

    }


}
