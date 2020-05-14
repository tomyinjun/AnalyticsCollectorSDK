package com.cmcc.vr.mid.probe.core;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.cmcc.vr.mid.probe.R;
import com.cmcc.vr.mid.probe.VideoQualityInfoInterface;
import com.cmcc.vr.mid.probe.constant.StaticConfig;
import com.cmcc.vr.mid.probe.job.JobManager;
import com.cmcc.vr.mid.probe.tasks.PlayerEventTaskFactory;
import com.cmcc.vr.mid.probe.tools.SharedPreferencesOpt;
import com.cmcc.vr.mid.probe.utils.LogUtils;

public class DetectorService extends Service {
    private static final String TAG = "DetectorService";
    private static Context sContext;
    private static TaskExecutor sTaskExecutor = null;
    public Handler mHandler = new TaskHandler(this, Looper.getMainLooper());
    private boolean mStarted = false;
    private DetectorState mDetectorState;

    public static Context getAppContext() {
        return sContext;
    }


    public static TaskExecutor getTaskExecutor() {
        return sTaskExecutor;
    }


    /**
     * cleanMessages
     *
     * @param handler
     */
    public static void cleanMessages(Handler handler) {

    }

    private IBinder iBinder = new VideoQualityInfoInterface.Stub() {
        @Override
        public void playerEventCall(int eventType, String eventParametersJSON) throws RemoteException {
            LogUtils.d("播放器事件：", eventType+"");
            PlayerEventTaskFactory factory = new PlayerEventTaskFactory();
            try {
                Runnable runnable = factory.getEventTaskByCode(eventType);

            } catch (Exception e){
                LogUtils.d("","");
            }
        }
    };

    @Override
    public void onCreate() {
        JobManager.startCollectJob();
        JobManager.startReportJob();
        sContext = getApplicationContext();
        sTaskExecutor = TaskExecutor.getExecutor();
        mDetectorState = DetectorState.getInstance(sContext);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        startBootReport();
        return START_STICKY;
    }

    private void startBootReport() {
//        long deviceRunTime = StaticConfig.getDeviceRunTime();
//        mDetectorState.setPlayStartTime(deviceRunTime);
//        Message obtain = Message.obtain();
//        obtain.what = StaticConfig.MSG_START_UPLOAD_PERIOD;
//        long intervalReportPeriodic = SharedPreferencesOpt.getIntervalReportPeriodic(sContext);
//        long j = deviceRunTime % intervalReportPeriodic;
//        Bundle bundle = new Bundle();
//        bundle.putLong(StaticConfig.START_TIME, deviceRunTime);
//        bundle.putLong(StaticConfig.DEL_TIME, 0);
//        obtain.setData(bundle);
//        mHandler.sendMessageDelayed(obtain, intervalReportPeriodic - j);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
