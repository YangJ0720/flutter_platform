import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class Show extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return ShowState();
  }
}

class ShowState extends State<Show> {
  Widget getPlatformTextView() {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
          viewType: "platform_text_view",
          creationParams: <String, dynamic>{"text": "Android Text View"},
          creationParamsCodec: const StandardMessageCodec());
    } else if (defaultTargetPlatform == TargetPlatform.iOS) {
      return UiKitView(
          viewType: "platform_text_view",
          creationParams: <String, dynamic>{"text": "iOS Label"},
          creationParamsCodec: const StandardMessageCodec());
    } else {
      return Text("Not supported");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text('title')),
        body: Column(
          children: <Widget>[
            Container(child: Center(child: getPlatformTextView()), height: 200),
            Expanded(
                child: Container(
                    child: Center(
                        child: Text(
                      '这是一个Flutter View',
                      style: TextStyle(color: Colors.white),
                    )),
                    color: Colors.pink))
          ],
        ));
  }
}
