package org.flutter.platform;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformViewFactory;
import io.flutter.plugin.platform.PlatformViewRegistry;

public class PlatformTextViewPlugin {

    static void registerWith(FlutterEngine engine) {
        // 注册Android Native View
        PlatformViewRegistry registry = engine.getPlatformViewsController().getRegistry();
        String s = "platform_text_view";
        PlatformViewFactory factory = new PlatformTextViewFactory(StandardMessageCodec.INSTANCE,
                engine.getDartExecutor().getBinaryMessenger());
        registry.registerViewFactory(s, factory);
    }

}
