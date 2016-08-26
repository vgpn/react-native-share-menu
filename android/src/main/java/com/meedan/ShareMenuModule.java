package com.meedan;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.meedan.ShareMenuPackage;

import java.util.Map;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;

public class ShareMenuModule extends ReactContextBaseJavaModule {
  private Activity mActivity = null;
  private ArrayList<Long> consumedIntents;

  public ShareMenuModule(ReactApplicationContext reactContext, Activity activity) {
    super(reactContext);
    mActivity = activity;
    consumedIntents = new ArrayList<Long>();
  }

  @Override
  public String getName() {
    return "ShareMenu";
  }

  @ReactMethod
  public void getSharedText(Callback successCallback) {
    Intent intent = mActivity.getIntent();
    String inputText = intent.getStringExtra(Intent.EXTRA_TEXT);
    Long currentIntent = intent.getLongExtra("id", -1);

    if (!consumedIntents.contains(currentIntent)) {
      Long id = System.currentTimeMillis();
      intent.putExtra("id", id);
      consumedIntents.add(id);
      successCallback.invoke(inputText);
    }
  }
}
