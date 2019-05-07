package com.sence.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.view.View;
import android.webkit.*;
import butterknife.BindView;
import com.sence.MainActivity;
import com.sence.R;
import com.sence.activity.web.WebConstans;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import static com.sence.activity.web.WebConstans.WebCode.XTTZ;

/**
 * WebView页面
 */
public class WebActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.pt_web)
    PubTitle ptWeb;
    private WebSettings settings;

    private WebConstans.WebCode code;
    private String url = "";
    private String titlelSys;

    @Override
    public int onActLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        initSetting();
        ptWeb.setBackOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(code == XTTZ && titlelSys.equals("")){
                    startActivity(new Intent(WebActivity.this, MainActivity.class));
                }
                finish();
            }
        });
        code = (WebConstans.WebCode) this.getIntent().getSerializableExtra("code");
        switch (code) {
            case GRZL:
                webView.loadUrl(url);
                break;
            case SPXQ:
                webView.loadUrl(url);
                break;
            case WZXQ:
                webView.loadUrl(url);
                break;
            case YHXX:
                ptWeb.setTitleText("用户协议");
                url = WebConstans.YHXX;
                webView.loadUrl(url);
                break;
            case YSZC:
                ptWeb.setTitleText("隐私政策");
                url = WebConstans.YSZC;
                webView.loadUrl(url);
                break;
            case HYXY:
                ptWeb.setTitleText("会员协议");
                url = WebConstans.HYXY;
                webView.loadUrl(url);
                break;
            case XTTZ:
                url = getIntent().getStringExtra("url");
                titlelSys = getIntent().getStringExtra("title");
                ptWeb.setTitleText(titlelSys);
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
            Intent intent = new Intent(WebActivity.this, MyAccountActivity.class);
            startActivity(intent);
        }

    }
}
