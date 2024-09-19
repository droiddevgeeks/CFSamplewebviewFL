import 'package:cfsamplewebviewfl/ui/webview/inappwebview.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const Inappwebview(
          targetUrl:
              "https://internal.prodint.cashfree.com/checkout?pt=session_SQiIgpk2OYT6P1dLOnoX_tLsuo4yDz9arwtxJJvlrYcYfElbc_7Z_MBfBmAS_jOcGQapVxfhu60una5vAYbNpKO_MZuRSrp9WJbgRiWdHmca"),
    );
  }
}
