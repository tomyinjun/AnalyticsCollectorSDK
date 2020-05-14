package com.cmcc.vr.mid.probe.core;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.cmcc.vr.mid.probe.constant.StaticConfig;
import com.cmcc.vr.mid.probe.tasks.playereventtask.BootReportTask;

/**
 * TaskHandler
 *
 * @Type TaskHandler.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午5:28
 */
public final class TaskHandler extends Handler {
    private DetectorService mService;

    TaskHandler(DetectorService detectorService, Looper looper) {
        super(looper);
        mService = detectorService;
    }

    @Override
    public final void handleMessage(Message message) {
        Bundle bundle = message.getData();
        switch (message.what) { //判断时间类型
            case StaticConfig.MSG_START_UPLOAD_BOOT: //6001
                if (bundle != null) {
                    DetectorService.getTaskExecutor().submit(new BootReportTask(
                            DetectorService.getAppContext(), mService.mHandler, bundle));
                }
                break;
            case StaticConfig.MSG_START_UPLOAD_PERIOD: //6002
                if (bundle != null) {
                    //todo MSG_START_UPLOAD_PERIOD task
                }
                break;

            default:
                break;
        }
    }
}
