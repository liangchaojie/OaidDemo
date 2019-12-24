package com.hgb.oaiddemo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bun.miitmdid.core.JLibrary;

import java.util.ArrayList;
import java.util.List;

public class OaidHelper {
    private static final OaidHelper ourInstance = new OaidHelper();

    public static OaidHelper getInstance() {
        return ourInstance;
    }

    private OaidHelper() {
    }

    public void attachBaseContext(Context base) {
        JLibrary.InitEntry(base);
    }

    public void getDeviceIds(Context context) {
        //获取OAID等设备标识符
        MiitHelper miitHelper = new MiitHelper(appIdsUpdater);
        miitHelper.getDeviceIds(context);
    }


    private List<OaidListener> mListener = new ArrayList<>();

    public void addOaidListener(OaidListener listener) {
        if (listener != null) {
            mListener.add(listener);
        }
    }

    public interface OaidListener {
        void onOaid(String oaid);
    }

    private MiitHelper.AppIdsUpdater appIdsUpdater = new MiitHelper.AppIdsUpdater() {
        @Override
        public void onOaid(boolean isSupport, @NonNull String oaid) {
            Log.i("OaidHelper oaid===  ", oaid);
            if (!isSupport || TextUtils.isEmpty(oaid)) {
                return;
            }
            for (int i = 0; i < mListener.size(); i++) {
                mListener.get(i).onOaid(oaid);
            }
        }

        @Override
        public void onError(@NonNull String error) {
            Log.i("OaidHelper error===", error);
        }
    };
}
