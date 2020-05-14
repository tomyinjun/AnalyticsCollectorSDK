package com.cmcc.vr.mid.probe.core;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;

import com.cmcc.vr.mid.probe.tools.SharedPreferencesOpt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DetectorState
 *
 * @Type DetectorState.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:56
 */
public class DetectorState {
    //TODO: sync with 3.1
    private static final String TAG = "DetectorState";
    public static final int AFTER_LONG_PAUSE = 2;
    public static final int AFTER_LONG_PLAY = 1;
    public static final int NORMAL = 0;

    /**
     * 0
     */
    public static final int PROGRAM_STATE_QUIT = 0;
    /**
     * 1
     */
    public static final int PROGRAM_STATE_PREPARE = 1;
    /**
     * 2
     */
    public static final int PROGRAM_STATE_START = 2;
    /**
     * 3
     */
    public static final int PROGRAM_STATE_OPEN = 3;
    /**
     * 4
     */
    public static final int PROGRAM_STATE_PREPARE_COMPLETED = 4;
    /**
     * 5
     */
    public static final int PROGRAM_STATE_STARTUP = 5;
    /**
     * 70
     */
    public static final int PROGRAM_STATE_SEEK_START = 70;
    /**
     * 71
     */
    public static final int PROGRAM_STATE_SEEK_END = 71;
    /**
     * 72
     */
    public static final int PROGRAM_STATE_BUFFER_START = 72;
    /**
     * 73
     */
    public static final int PROGRAM_STATE_BUFFER_END = 73;
    /**
     * 74
     */
    public static final int PROGRAM_STATE_PAUSE = 74;
    /**
     * 75
     */
    public static final int PROGRAM_STATE_RESUME = 75;
    /**
     * 76
     */
    public static final int PROGRAM_STATE_BLURREDSCREEN_START = 76;
    /**
     * 77
     */
    public static final int PROGRAM_STATE_BLURREDSCREEN_END = 77;
    /**
     * 78
     */
    public static final int PROGRAM_STATE_UNLOAD_START = 78;
    /**
     * 79
     */
    public static final int PROGRAM_STATE_UNLOAD_END = 79;
    public static final int PROGRAM_TYPE_DEMAND = 2;
    public static final int PROGRAM_TYPE_LIVE = 1;
    public static final int PROGRAM_TYPE_NONE = 0;
    public static final int PROGRAM_TYPE_UNKNOW = 10;

    public static final int STANDBBY_STATE_FALSE = 0;
    public static final int STANDBBY_STATE_TRUE = 1;
    public static final int START_NETTY_CONNECT = 1;
    public static final int START_REPORT = 0;
    public static final int STOP_NETTY_CONNECT = 0;
    public static final int STOP_REPORT = 1;

    private static DetectorState sInstance = null;

    private boolean mIsPrepareToPlay = false; //A
    private boolean mPrepareCompleted = false;
    private boolean mIsStartUpPlay = false;
    private long mIntervalStart = 0;
    private boolean mHadTs = false;
    private long mSumPlayTime = 0; //F
    private long mResumeTime = 0; //G
    private long mUnloadStartTime = -1; //H
    private long mBlurStartTime = -1; //I
    private long mBufferStartTime = -1; //J
    private long mPauseTime = 0; //K
    private int mCurProgramType = -1; //L
    private int mProgramReportType = -1; //M
    private boolean mProbeRcvServerRunState = false; //N
    private long mRealTime = -1; //O
    private long mBootTime; //P
    private long mLaseQuitTime = 0; //Q
    private static final String URL_REGEX = ".*livemode=1.*,rtp://.*;.*HlsSubType=1.*;.*gitv_live.*;.*livemode=4.*";
    private int mNettyState = 0; //S
    private int mUploadTS = 0; //U
    private int mProgramReportState = 0; //V
    private String mProgramApk = null; //X
    private int mMulticastState = 0; //Y
    private int mJoinMulticastCnt = 0; //Z
    long mOpenTime = 0; //a
    private int mQuitMulticastCnt = 0; //aa
    private String mStbAddr = ""; //ab
    private String mBackServerAddress = ""; //ac
    long mPrepareTime = 0; //b
    long mMaxFirstBufferTime = 0; //c
    long mFirstBufferTime = 0; //d
    long mPrepareCompletedTime = 0; //e
    long mStartUpTime = 0; //f
    int mAllTimesToPlay = 0; //g
    int mSucTimesToPlay = 0; //h
    private Context mContext; //i
    private long mFailPlayInterval; //m
    private long mPeriodReportInterval = -1; //n
    private long mProgramInfoReportInterval = -1; //o
    private int mM3u8RespAlarmThreshold = -1; //p
    private int mEpgRespAlarmThreshold = -1; //q
    private int mTsRespThreshold = -1; //r
    private String mStutterThreshold = null; //s
    public int mStbPlayingStatus = 0;
    private int mReportState = -1; //u
    private int mStandbyState = -1; //v
    private long mStandbyTime = -1; //w
    private String mLocalIp = null; //x
    private int mCurPlayId = -1; //y
    private int mProgramState = -1; //z
    private long mMulticastJoinThreshold = -1;
    private String mBytesHistogram;
    private String mCostHistogramOfM3u8Interval;
    private String mCostHistogramOfTsInterval;
    private String mDnsList;
    private String mEpgDownloadHistogram;
    private int mFailPlayMode;
    private long mKeepAliveCheckUpdate;
    private String mM3u8DownloadHistogram;
    private HashMap<String, Integer> au = new HashMap<>();
    private String l;
    private long mProgramReportTime;
    private long mScreenStartTime;
    private String mSecondServerAddr;
    private String mSecondsHistogramOfScreenBlurred;
    private String mSecondsHistogramOfShutter;
    private String mSecondsHistogramOfUnload;
    private String mTsDownloadHistogram;
    private boolean mIsEncrypt;
    private String mBytesHistogramBuffer;
    private long s;
    private long t;

    public DetectorState(Context context) {
        mContext = context;
    }

    public static DetectorState getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DetectorState(context);
        }
        return sInstance;
    }

    public boolean isReportToPlatform() {
        if (mReportState == -1) {
            mReportState = SharedPreferencesOpt.getReportState(mContext);
        }
        return mReportState == 0;
    }

    public void setPlayStartTime(long deviceRunTime) {
    }
}
