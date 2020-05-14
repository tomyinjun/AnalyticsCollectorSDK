package com.cmcc.vr.mid.probe.receiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.cmcc.vr.mid.probe.constant.StaticConfig;
import com.cmcc.vr.mid.probe.core.TaskHandler;
import com.cmcc.vr.mid.probe.utils.LogUtils;

import org.json.JSONObject;

public class PlayerEventReceiver {
    JSONObject eventData;
    Handler mHandler;
    public PlayerEventReceiver(JSONObject eventData, TaskHandler handler) {
        this.eventData = eventData;
        mHandler = handler;
    }

    public void createJob() {
        try {
            switch (eventData.getInt("event")){
                case StaticConfig.MSG_START_UPLOAD_ALARM:
                    //TODO alarmReportTask
                    startAlarmReport();

            }
        } catch (Exception e){
            LogUtils.d("","");
        }
    }
    public void startAlarmReport() throws Exception {
        Message obtain = Message.obtain();
        obtain.what = StaticConfig.MSG_START_UPLOAD_ALARM;
        Bundle bundle = new Bundle();
        bundle.putString("data", eventData.getJSONObject("data").getString("firstBufferTime"));
        obtain.setData(bundle);
        mHandler.sendMessageDelayed(obtain, 0);
    }
}
