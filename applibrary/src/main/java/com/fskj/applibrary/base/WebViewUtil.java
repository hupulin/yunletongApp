package com.fskj.applibrary.base;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by xzz on 2019/4/26.
 */

public class WebViewUtil {
    public static void setGoodsImageText(WebView webView,String content) {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String data = String.format("<html><head><style>img{max-width:100%%;height:auto " +
                        "!important;width:auto !important;};</style>" +
                        "</head><body style='margin:0; padding:20px;'>" +

                        "<div style='padding:0px'>%s</div></body></html>",
                content);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        webView.loadDataWithBaseURL("", data, "text/html", "utf-8", "");
    }
}
