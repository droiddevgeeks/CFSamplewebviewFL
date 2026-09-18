package com.example.cfsamplewebviewfl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Map;

import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class WebViewFactory extends PlatformViewFactory {
    public WebView webView;
    private final CfJsBridge cfJsBridge;

    public WebViewFactory(Context context) {
        super(StandardMessageCodec.INSTANCE);
        this.cfJsBridge = new CfJsBridge(context);
    }

    @Override
    public PlatformView create(Context context, int viewId, Object args) {
        webView = initAndGetWebView(context, args);
        return new CustomWebViewPlatform(webView);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private WebView initAndGetWebView(Context context, Object args) {
        WebView webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(cfJsBridge, "Android");

        if (args instanceof Map) {
            Map<?, ?> params = (Map<?, ?>) args;
            Object webUrl = params.get("webUrl");
            if (webUrl != null) {
                webView.loadUrl(webUrl.toString());
            }
        }
        return webView;
    }
}