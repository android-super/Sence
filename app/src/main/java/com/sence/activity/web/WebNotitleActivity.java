package com.sence.activity.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.view.View;
import android.webkit.*;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.sence.R;
import com.sence.activity.MyAccountActivity;
import com.sence.base.BaseActivity;
import com.sence.utils.LoginStatus;
import com.sence.utils.NavigationBarUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

/**
 * WebView页面
 */
public class WebNotitleActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.layout_progress)
    RelativeLayout layoutProgress;
    private WebSettings settings;

    private WebConstans.WebCode code;
    private String url;

    @Override
    public int onActLayout() {
        return R.layout.activity_web_notitle;
    }

    @Override
    public void initView() {
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        initSetting();
        code = (WebConstans.WebCode) this.getIntent().getSerializableExtra("code");
        switch (code) {
            case HY:
                url = WebConstans.buildWebUrl(WebConstans.HY, LoginStatus.getUid());
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
        webView.addJavascriptInterface(new JsInterface(this), "Android");
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

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        //在js中调用window.AndroidWebView.showInfoFromJs(name)，便会触发此方法。
        @JavascriptInterface
        public void closeBtnClick() {
            finish();
        }

        @JavascriptInterface
        public void accountClick() {
            Intent intent = new Intent(WebNotitleActivity.this, MyAccountActivity.class);
            startActivity(intent);
        }

    }
}
