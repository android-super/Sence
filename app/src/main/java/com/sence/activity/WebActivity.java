package com.sence.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.sence.R;
import com.sence.activity.web.WebConstans;
import com.sence.base.BaseActivity;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import butterknife.BindView;

/**
 * WebView页面
 */
public class WebActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.layout_progress)
    RelativeLayout layoutProgress;
    @BindView(R.id.pt_web)
    PubTitle ptWeb;
    private WebSettings settings;

    private WebConstans.WebCode code;
    private String url;

    @Override
    public int onActLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        initSetting();
        code = (WebConstans.WebCode) this.getIntent().getSerializableExtra("code");
        switch (code) {
            case HY:
                ptWeb.setTitleText("花园");
                url = WebConstans.buildWebUrl(WebConstans.HY, LoginStatus.getUid());
                webView.loadUrl(url);
                break;
            case GRZL:
                webView.loadUrl(url);
                break;
            case SPXQ:
                webView.loadUrl(url);
                break;
            case WZXQ:
                webView.loadUrl(url);
                break;
            case XKXY:
                webView.loadUrl(url);
                break;
            case YSZC:
                ptWeb.setTitleText("隐私政策");
                url = WebConstans.YSZC;
                webView.loadUrl(url);
                break;

        }
    }

    private void initSetting() {
        settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccess(false);
        settings.setUseWideViewPort(false);//禁止webview做自动缩放
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportMultipleWindows(false);
        settings.setAppCachePath(getDir("cache", Context.MODE_PRIVATE).getPath());
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setFocusable(true);
        webView.requestFocus();
        webView.setWebChromeClient(new WebChromeClient());  //解决android与H5协议交互,弹不出对话框问题
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //页面加载完成之后
                layoutProgress.setVisibility(View.GONE);
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity
                            (intent);
                    return true;
                }
                return true;

            }
        });
    }
}
