package com.cmcc.vr.mid.probe.constant;

import android.content.Context;
import android.text.TextUtils;

import com.cmcc.vr.mid.probe.utils.FileUtils;

/**
 * ConfigUtils
 *
 * @Type ConfigUtils.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:48
 */
public class ConfigUtils {
    private static final long ALARM_OFFSET = 20;
    private static final long ALARM_TIME_INTERVAL = 20000;
    private static final long CDN_ALARM_OFFSET = 60000;
    public static final String CONFIG_FILE_NAME = "config";
    private static final String CPAPP_NAMES = "com.gitv.tv.launcher";
    private static final int CPU_TOP_CNT = 3;
    private static final long DELAY_CYCLE_INTERAL = 600000;
    private static final long DELAY_OFFSET = 60000;
    private static final long FAILPLAY_INTERVAL = 4000;
    public static final long FIRST_BOOT_INTERVAL = 60000;
    private static final long FIRST_BUFFER_INTERVAL = 10000;
    public static final long FIRST_REPORT_INTERVAL = 60000;
    public static final long INTERVAL_AUTH_PERIOD = 600000;
    private static final long INTERVAL_CONFIG_REQUEST = 10800000;
    public static final long INTERVAL_REPORT_BOOT = 3600000;
    private static final String ITCAST_REGEX = "(udp://|rtp://).*";
    public static final long MAX_DB_SIZE = 52428800;
    private static final long MAX_PLAYTIME_INTERVAL = 14400;
    public static final int MAX_SAVE_NUMS = 512;
    private static final long MEDIARATE_OFFSET = 300000;
    private static final int MEM_TOP_CNT = 3;
    private static final long MIN_DISK_SIZE = 52428800;
    public static final long MIN_INTERVAL = 60000;
    private static final long MIN_MEDIARATE = 512000;
    private static final long MIN_PLAYTIME_THRESHOLD = 60000;
    public static final long NETTY_RESTART_INTERVAL = 240000;
    public static final int NET_CONNECT_TIMEOUT = 60000;
    public static final int NET_READ_TIMEOUT = 60000;
    private static final long PLAY_ACTION_INTERVAL = 30000;
    public static long PLAY_ACTION_OFFSET = 60000;
    private static final long PROGRAM_START_OFFSET = 2000;
    private static final String REGEX_M3U8 = ".*\\.m3u8.*";
    private static final String REGEX_TS = ".*(\\.ts|\\.mp4|contentid=|Contentid=|rtsp:).*";
    public static final long SEND_TS_INTERVAL = 15000;
    private static final long STARTUP_OFFSET = 100;
    private static final long TRIGGER_LONG_TIME = 60000;
    private static final String TSNAME_REGEX = ".*\\.ts";
    private static final int UPDATE_RESTART_INTERVAL = 60000;
    public static final int WRITE_IDLE_TIME_SECONDS = 300;
    private static ConfigUtils sInstance = null;
    public static final int START_CAPTURE_LOG = 1;
    public static final int STOP_CAPTURE_LOG = 0;
    private long cntvProgramStartOffset = PLAY_ACTION_INTERVAL;
    private Context mContext;

    private ConfigUtils(Context context) {
        mContext = context;
        String read = FileUtils.read(mContext, "config");
        if (!TextUtils.isEmpty(read)) {

        }
    }

    public static ConfigUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ConfigUtils(context);
        }
        return sInstance;
    }

    public String getServerAddress() {
        return "";
    }

    public long getNetConnectTimeout() {
        return 3000;
    }

    public long getNetReadTimeout() {
        return 3000;
    }
}
