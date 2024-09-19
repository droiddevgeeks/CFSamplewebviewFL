import 'package:flutter/material.dart';
import 'package:flutter_inappwebview/flutter_inappwebview.dart';
import 'package:url_launcher/url_launcher.dart';

class Inappwebview extends StatefulWidget {
  final String targetUrl;
  const Inappwebview({required this.targetUrl, super.key});

  @override
  State<StatefulWidget> createState() => InappwebviewState();
}

class InappwebviewState extends State<Inappwebview> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("InAppWebView Example")),
      body: InAppWebView(
        initialUrlRequest: URLRequest(url: Uri.parse(widget.targetUrl)),
        initialOptions: InAppWebViewGroupOptions(
          crossPlatform: InAppWebViewOptions(useShouldOverrideUrlLoading: true),
        ),
        onWebViewCreated: (InAppWebViewController controller) {},
        onLoadStart: (controller, url) {
          setState(() {
            print('Page started loading: $url');
          });
        },
        onLoadStop: (controller, url) {
          setState(() {
            print('Page finished loading: $url');
          });
        },
        onLoadError: (controller, url, code, message) {
          print("Error loading $url: $message");
        },
        onConsoleMessage: (controller, consoleMessage) {
          print(consoleMessage);
        },
        shouldOverrideUrlLoading: (controller, navigationAction) async {
          var uri = navigationAction.request.url!;
          if ([
            "upi",
            "tez",
            "gpay",
            "paytmmp",
            "phonepe",
          ].contains(uri.scheme)) {
            print("Opening PSP app");
            await launchUrl(
              uri,
            );
            return NavigationActionPolicy.CANCEL;
          }
          return NavigationActionPolicy.ALLOW;
        },
      ),
    );
  }
}
