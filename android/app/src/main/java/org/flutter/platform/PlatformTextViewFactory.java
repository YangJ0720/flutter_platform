package org.flutter.platform;

import android.content.Context;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class PlatformTextViewFactory extends PlatformViewFactory {

    private BinaryMessenger mBinaryMessenger;

    public PlatformTextViewFactory(MessageCodec<Object> createArgsCodec, BinaryMessenger messenger) {
        super(createArgsCodec);
        this.mBinaryMessenger = messenger;
    }

    @Override
    public PlatformView create(Context context, int id, Object args) {
        return new PlatformTextView(context, mBinaryMessenger, id);
    }
}
