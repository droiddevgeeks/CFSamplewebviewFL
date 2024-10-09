package com.example.cfsamplewebviewfl

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

internal class WebViewFactory(private val context: Context) :
    PlatformViewFactory(StandardMessageCodec.INSTANCE) {

    var webView: WebView? = null
    private val cfJsBridge: CFJsBridge by lazy { CFJsBridge(context) }
    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        webView = initAndGetWebView(context, args)
        return CustomWebViewPlatform(webView)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initAndGetWebView(context: Context?, args: Any?): WebView {
        val webView = WebView(context!!)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.addJavascriptInterface(cfJsBridge, "Android")
        val params = args as Map<*, *>
        val webUrl = params["webUrl"]
        if (webUrl != null) {
            webView.loadUrl(webUrl.toString())
        }
        return webView
    }
}

internal class CustomWebViewPlatform(private val webView: WebView?) : PlatformView {
    override fun getView(): View? = webView
    override fun dispose() {
        webView?.destroy()
    }
}