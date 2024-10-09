import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class CustomWebview extends StatefulWidget {
  final String targetUrl;
  const CustomWebview({required this.targetUrl, super.key});

  @override
  State<StatefulWidget> createState() => CustomWebviewState();
}

class CustomWebviewState extends State<CustomWebview> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: const Text("CustomWebView Example")),
        body: AndroidView(
          creationParams: {
            'webUrl': widget.targetUrl,
          },
          creationParamsCodec: const StandardMessageCodec(),
          viewType: 'custom_webview',
        ));
  }
}
