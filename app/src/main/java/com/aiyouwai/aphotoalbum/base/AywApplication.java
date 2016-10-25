package com.aiyouwai.aphotoalbum.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.StrictMode;

import com.aiyouwai.aphotoalbum.BuildConfig;
import com.aiyouwai.aphotoalbum.utils.LogUtil;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

public class AywApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // onCreate会多次启动
        String processAppName = getAppName();
        if (!getPackageName().equalsIgnoreCase(processAppName)) {
            return;
        }

        initPicasso();
        setStrictMode();
    }

    // 初始化Picasso
    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(getApplicationContext())
                .downloader(new OkHttp3Downloader(getApplicationContext()))
                .build();
        Picasso.setSingletonInstance(picasso);
    }

    // 启用严苛模式
    private void setStrictMode() {
        final ApplicationInfo appInfo = getApplicationContext().getApplicationInfo();
        final int appFlags = appInfo.flags;
        if (BuildConfig.DEBUG && (0 != (appFlags & ApplicationInfo.FLAG_DEBUGGABLE))) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy
                    .Builder()
                    .detectNetwork()
                    .penaltyLog()
                    .penaltyDialog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy
                    .Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }

    private String getAppName() {
        String processName = null;
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        try {
            List<ActivityManager.RunningAppProcessInfo> l = am.getRunningAppProcesses();
            Iterator<ActivityManager.RunningAppProcessInfo> i = l.iterator();
            while (i.hasNext()) {
                ActivityManager.RunningAppProcessInfo info = (i.next());
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;
                    return processName;
                }
            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        return processName;
    }
}
