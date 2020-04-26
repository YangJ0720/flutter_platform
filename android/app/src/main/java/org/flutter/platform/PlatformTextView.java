package org.flutter.platform;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;

public class PlatformTextView implements PlatformView, MethodChannel.MethodCallHandler {

    // TextView
    private TextView mTextView;
    // TAG
    private static final String TAG = "PlatformTextView";

    public PlatformTextView(Context context, BinaryMessenger messenger, int id) {
        //
        TextView textView = new TextView(context);
        textView.setText("这是一个Android Native View");
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.BLUE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "Time: " + System.currentTimeMillis();
                textView.setText(text);
                Log.i(TAG, text);
            }
        });
        this.mTextView = textView;
        //
        String name = "platform_text_view_" + id;
        MethodChannel channel = new MethodChannel(messenger, name);
        channel.setMethodCallHandler(this);
    }

    @Override
    public View getView() {
        return mTextView;
    }

    @Override
    public void dispose() {
        Log.e(TAG, "dispose");
    }

    @Override
    public void onFlutterViewAttached(View flutterView) {
        Log.i(TAG, "onFlutterViewAttached: " + flutterView);
    }

    @Override
    public void onFlutterViewDetached() {
        TextView textView = mTextView;
        if (textView != null) {
            ViewParent parent = textView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(textView);
            }
            this.mTextView = null;
        }
        Log.e(TAG, "onFlutterViewDetached: ");
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (TextUtils.equals("setText", methodCall.method)) {
            mTextView.setText(methodCall.arguments.toString());
            result.success("Android " + Build.VERSION.SDK_INT);
        } else {
            result.notImplemented();
        }
    }
}
