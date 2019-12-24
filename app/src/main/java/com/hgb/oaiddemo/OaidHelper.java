package com.hgb.oaiddemo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bun.miitmdid.core.JLibrary;

public class OaidHelper {
    private static final OaidHelper ourInstance = new OaidHelper();

    public static OaidHelper getInstance() {
        return ourInstance;
    }

    private OaidHelper() {
    }

    private String oaid;

    private boolean isSupportOaid;

    public String getOaid() {
        String result = "";
        if (isSupportOaid && !TextUtils.isEmpty(oaid)) {
            result = oaid;
        }
        return result;
    }

    public void setIsSupportOaid(boolean isSupportOaid) {
        this.isSupportOaid = isSupportOaid;
    }

    public void attachBaseContext(Context base) {
        JLibrary.InitEntry(base);
    }

    public void getDeviceIds(Context context) {
        //获取OAID等设备标识符
        MiitHelper miitHelper = new MiitHelper(appIdsUpdater);
        miitHelper.getDeviceIds(context);
    }

    private MiitHelper.AppIdsUpdater appIdsUpdater = new MiitHelper.AppIdsUpdater() {
        @Override
        public void onOaid(boolean isSupport, @NonNull String oaid) {
            setIsSupportOaid(isSupport);
            Log.i("OaidHelper oaid===  ", oaid);
            OaidHelper.this.oaid = oaid;
        }

        @Override
        public void onError(@NonNull String error) {
            Log.i("OaidHelper error===", error);
        }
    };
}
