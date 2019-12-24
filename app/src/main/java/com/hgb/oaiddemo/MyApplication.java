package com.hgb.oaiddemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bun.miitmdid.core.JLibrary;

/**
 * @author: hgb
 * @createTime: 2019/9/27
 * @description:
 * @changed by:
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        OaidHelper.getInstance().attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OaidHelper.getInstance().getDeviceIds(this);
    }

}
