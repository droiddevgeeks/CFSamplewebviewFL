package com.example.cfsamplewebviewfl

import android.content.Intent
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine


class MainActivity : FlutterActivity() {

    private val webViewFactory: WebViewFactory by lazy { WebViewFactory(this) }
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        initWebViewFactory()
    }

    private fun initWebViewFactory() {
        flutterEngine?.platformViewsController?.registry?.registerViewFactory(
            "custom_webview", webViewFactory
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            webViewFactory.webView?.evaluateJavascript("window.showVerifyUI()") {}
        }
    }
}
