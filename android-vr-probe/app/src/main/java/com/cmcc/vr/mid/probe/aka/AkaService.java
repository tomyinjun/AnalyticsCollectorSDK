package com.cmcc.vr.mid.probe.aka;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.cmcc.vr.mid.probe.aka.net.AkaApiService;
import com.cmcc.vr.mid.probe.aka.net.CrtResponse;
import com.cmcc.vr.mid.probe.aka.net.KeyResponse;
import com.cmcc.vr.mid.probe.aka.net.NetServiceFactory;
import com.cmcc.vr.mid.probe.constant.ConfigUtils;
import com.cmcc.vr.mid.probe.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

/**
 * AkaService
 *
 * @Type AkaService.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:46
 */
public class AkaService extends Service {
    private static final String TAG = "AkaService";
    private static final int REQUEST_CRT = 0;
    private static final int REQUEST_KEY = 1;
    private static final int RETRY_PENDING_TIME = 1 * 60 * 1000;

    private static String sDeviceId;
    private static String sDeviceMacAddress;
    private static String sSoftProbeVersion;

    private AkaApiService mAkaApiService;
    private AkaHelper mAkaHelper;
    private Handler mHandler;
    private boolean mIsRequesting;

    /**
     * AkaBinder
     */
    public class AkaBinder extends Binder {
        public AkaService getService() {
            return AkaService.this;
        }
    }

    private class AkaHandler extends Handler {
        public AkaHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REQUEST_CRT:
                    mIsRequesting = true;
                    requestCrt();
                    break;
                case REQUEST_KEY:
                    requestKey();
                    mIsRequesting = false;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new AkaBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String stbServerAddress = ConfigUtils.getInstance(this).getServerAddress();
        if (!TextUtils.isEmpty(stbServerAddress)) {
            mAkaApiService = NetServiceFactory.createApiService(stbServerAddress,
                    AkaApiService.class);
        }

        HandlerThread thread = new HandlerThread(TAG);
        thread.start();
        mHandler = new AkaHandler(thread.getLooper());

//        DevInfoTools devInfoTools = DevInfoTools.getInstance(this);
//        sDeviceId = devInfoTools.getDeviceId();
//        sDeviceMacAddress = devInfoTools.getEth0Mac();
//        sSoftProbeVersion = devInfoTools.getSoftDetectorVersion();

        mAkaHelper = AkaHelper.getInstance();
        startAka();
    }

    /**
     * Check aka status
     *
     * @return
     */
    public boolean checkAkaStatus() {
        return mIsRequesting || mAkaHelper.checkAkaStatus();
    }

    /**
     * start aka
     *
     * @return
     */
    public void startAka() {
        LogUtils.i(TAG, "start Aka");
        mHandler.removeMessages(REQUEST_CRT);
        mHandler.sendEmptyMessage(REQUEST_CRT);
    }

    private void startPendingAka() {
        LogUtils.i(TAG, "start Aka...1 minute later");
        mHandler.removeMessages(REQUEST_CRT);
        mHandler.sendEmptyMessageDelayed(REQUEST_CRT, RETRY_PENDING_TIME);
    }

    private void requestCrt() {
        //clear previous status
        mAkaHelper.clean();

        //send IF0 request 1
        HashMap<String, String> body = new HashMap<>();
        body.put("deviceId", sDeviceId);
        body.put("deviceMacAddress", sDeviceMacAddress);

        Call<CrtResponse> crtCall = mAkaApiService.requestCrt(body);
        try {
            Response<CrtResponse> response = crtCall.execute();
            if (response != null && response.isSuccessful() && response.body() != null) {
                if (mAkaHelper.saveAndCheckCrt(this, response.body())) {
                    mHandler.sendEmptyMessage(REQUEST_KEY);
                    LogUtils.i(TAG, "AKA requestCrt succeed!");
                    return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startPendingAka();
    }

    private void requestKey() {
        //send IF0 request 2
        HashMap<String, String> body = new HashMap<>();
        body.put("deviceId", sDeviceId);
        body.put("deviceMacAddress", sDeviceMacAddress);
        body.put("softprobeVersion", sSoftProbeVersion);
        body.put("clientTimeStamp", mAkaHelper.getClientTimeStamp());
        body.put("serverTimeStamp", mAkaHelper.getServerTimeStamp());
        try {
            body.put("encryptedMessage", AkaHelper.getInstance().getKeyRequestEncryptedMsg(this));
            body.put("messageSignature",
                    AkaHelper.getInstance().getKeyRequestMsgSignature(this, body));
        } catch (Exception e) {
            e.printStackTrace();
            startPendingAka();
            return;
        }

        Call<KeyResponse> keyCall = mAkaApiService.requestKey(body);
        try {
            Response<KeyResponse> response = keyCall.execute();
            if (response != null && response.isSuccessful() && response.body() != null) {
                if (mAkaHelper.generateAndCheckKey(response.body())) {
                    LogUtils.i(TAG, "AKA succeed!");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startPendingAka();
    }

}
