package com.example.cfsamplewebviewfl;

import android.content.Intent;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;

public class MainActivity extends FlutterActivity {

    private final WebViewFactory webViewFactory = new WebViewFactory(this);

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        initWebViewFactory(flutterEngine);
    }

    private void initWebViewFactory(FlutterEngine flutterEngine) {
        flutterEngine.getPlatformViewsController().getRegistry().registerViewFactory(
                "custom_webview", webViewFactory
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (webViewFactory.webView != null) {
                webViewFactory.webView.evaluateJavascript("window.showVerifyUI()", null);
            }
        }
    }
}