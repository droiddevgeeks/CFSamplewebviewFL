package com.example.cfsamplewebviewfl;

import android.view.View;
import android.webkit.WebView;

import io.flutter.plugin.platform.PlatformView;

class CustomWebViewPlatform implements PlatformView {

    private final WebView webView;

    public CustomWebViewPlatform(WebView webView) {
        this.webView = webView;
    }

    @Override
    public View getView() {
        return webView;
    }

    @Override
    public void dispose() {
        if (webView != null) {
            webView.destroy();
        }
    }
}
