package com.cmcc.vr.mid.probe.tasks.playereventtask;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.cmcc.vr.mid.probe.tools.SystemInfoTools;
import com.cmcc.vr.mid.probe.utils.LogUtils;

public class BootReportTask implements Runnable {
    private static final String TAG = "BootReportTask";
    private Context mContext;
    private Handler mHandler;
    private Bundle mBundle;
    private SystemInfoTools mSystemInfoTools;
    private String mBootJson;

    public BootReportTask(Context context, Handler handler, Bundle bundle) {
        mContext = context;
        mHandler = handler;
        mBundle = bundle;
        //TODO 采集数据工具类初始化
        mSystemInfoTools = SystemInfoTools.getInstance(mContext);
    }

    @Override
    public void run() {
        try {
            //TODO 数据采集工作
            collectData();

            //TODO 数据上报
            report(mBootJson);
            //sendDelayMsg
        } catch (Exception e) {
            LogUtils.i(TAG, "BootReportTask Exception");
            e.printStackTrace();
        }
    }

    private void collectData() {

    }
    private void report(String jsonData) {

    }
}
