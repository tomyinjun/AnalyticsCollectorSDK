package com.cmcc.vr.mid.probe.constant;

import android.os.SystemClock;
import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticConfig {
    public static final String ACTION_ALARM_BROADCAST = "com.baina.bnsysplugin.alert";
    public static final String AFTER_FEC = "AFTER_FEC";
    public static final String ALARMTASKFLAG = "alarmTaskFlag";
    public static final int ALARM_CODE_STUTTER = 31;
    public static final int ALARM_CODE_EPG = 32;
    public static final int ALARM_CODE_M3U8 = 33;
    public static final int ALARM_CODE_TS = 34;
    public static final String ALARM_DATA = "alarm_data";

    public static final String ALERTCODE = "alertCode";
    public static final String ALERTCODE_EPG = "tz_002";
    public static final String ALERTCODE_FIRST_BUFFER = "tz_005";
    public static final String ALERTCODE_M3U8 = "tz_004";
    public static final String ALERTCODE_SHUT = "tz_001";
    public static final String ALERTCODE_TS = "tz_003";

    public static final String AREA_CODE_GD = "20";
    public static final String AREA_CODE_GS = "28";
    public static final String AREA_CODE_GX = "21";
    public static final String AREA_CODE_GZ = "24";
    public static final String AREA_CODE_HAIN = "22";
    public static final String AREA_CODE_HB = "18";
    public static final String AREA_CODE_HEN = "17";
    public static final String AREA_CODE_HN = "19";
    public static final String AREA_CODE_JL = "09";
    public static final String AREA_CODE_JS = "11";
    public static final String AREA_CODE_JX = "15";
    public static final String AREA_CODE_NX = "30";
    public static final String AREA_CODE_QH = "29";
    public static final String AREA_CODE_SX = "06";
    public static final String AREA_CODE_XJ = "31";
    public static final String AREA_CODE_YN = "25";
    public static final String AREA_CODE_ZJ = "12";
    public static final String AREA_BJ = "x010";
    public static final String AREA_GD = "x020";
    public static final String AREA_GS = "x093";
    public static final String AREA_GX = "x077";
    public static final String AREA_GZ = "x085";
    public static final String AREA_HAIN = "x089";
    public static final String AREA_HB = "x0270";
    public static final String AREA_HEN = "x037";
    public static final String AREA_HN = "x073";
    public static final String AREA_JL = "x043";
    public static final String AREA_JS = "x025";
    public static final String AREA_JX = "x079";
    public static final String AREA_NX = "x095";
    public static final String AREA_QH = "x097";
    public static final String AREA_SH = "x021";
    public static final String AREA_SX = "x035";
    public static final String AREA_XJ = "x099";
    public static final String AREA_YN = "x087";
    public static final String AREA_ZJ = "x057";
    public static final String AREA_ZJ_MOGU = "x057mg";
    public static final String AREA_CD = "x999";
    public static final String BLURR_NUM = "blurr_num";
    public static final String BLURR_TOTAL_TIME = "blurr_total_time";
    public static final String BUFFER_TIME = "BUFFER_TIME";
    public static final String BYTES = "BYTES";
    public static final String BYTES_HISTOGRAM = "1,2,5,10,20,40,80,100,140";
    public static final int BYTE_SIZE = 8;
    public static final String CAPTURERESULTNODE = "CAPTURERESULTNODE";
    public static final int CAPTURERESULT_CODE_FAIL = 0;
    public static final int CAPTURERESULT_CODE_SUCCESS = 1;
    public static final int CAPTURERESULT_CODE_RUNING = 2;
    public static final int CAPTURERESULT_CODE_ZIPING = 3;
    public static final int CAPTURERESULT_CODE_ZIPFAIL = 4;
    public static final int CAPTURERESULT_CODE_UPLOADING = 5;
    public static final int CAPTURERESULT_CODE_UPLOADFAIL = 6;
    public static final int CAPTURERESULT_CODE_UPLOADSUCEESS = 1110;

    public static final String CLIENT_NAME_SPACE = "cmcc_local_socket_client";
    public static final String CLIENT_TOKEN = "client_token";
    public static final String CMCC_AUTH_SERVICE_NAME = "com.chinamobile.middleware.auth.service.MainAuthService";
    public static final String CMCC_AUTH_SERVICE_PKG_NAME = "com.chinamobile.middleware.auth";

    public static final String DEBUG_BJ_NETTY_HOST = "218.206.177.149";
    public static final int DEBUG_BJ_NETTY_PORT = 8888;
    public static final String DEBUG_BJ_SERVER_ADDRESS = "http://218.206.177.139:9080/family/";

    public static final String DEBUG_STB_NETTY_HOST = "221.226.67.42";
    public static final int DEBUG_STB_NETTY_PORT = 6087;
    public static final String DEBUG_STB_SERVER_ADDRESS = "http://221.226.67.42:6086/Qcs/";

    public static final int DEFAULT_SECOND_MONITOR_INTERVAL = 5000;
    public static final int DELAY_TASK_TRUE = 1;
    public static final String DEL_TIME = "DEL_TIME";
    public static final String DNS_DATA = "dns_data";
    public static final int END_SECOND = 81;
    public static final String END_TIME = "END_TIME";
    public static final String ERROR_CODE = "ERROR_CODE";
    public static final int EVENT_CODE_ALARM = 3;
    public static final int EVENT_CODE_AUTH = 7;
    public static final int EVENT_CODE_BOOT = 1;
    public static final int EVENT_CODE_NETWOK_TEST = 5;
    public static final int EVENT_CODE_PERIODIC = 2;
    public static final int EVENT_CODE_PROGRAM_INFO = 6;
    public static final int EVENT_CODE_SECOND_MONITOR = 4;
    public static final String EXTRA_BLURR_TIME = "extra_blurr_time";
    public static final String EXTRA_SHUTTER_TIME = "extra_shuttertime";
    public static final String EXTRA_UNLOAD_TIME = "extra_unload_time";
    public static final String FRAMERATE = "FRAMERATE";
    public static final String HEIGHT = "HEIGHT";
    public static final String ID = "ID";
    public static final String IGMP_DATA = "igmp_data";
    public static final String IS_DELAY_TASK = "is_delay_task";
    public static final String IS_STANDBY = "IS_STANDBY";
    public static final String KEY_STR = "cmcc_softdetector";
    public static final String MSG_TYPE = "TYPE";

    public static final int LOCAL_TIMEOUT = 30000;
    public static boolean MANAGE_DATA = true;
    public static final int MAXADDRESSUSECNT = 5;
    public static final String MEDIA_ACTION = "MEDIA_PLAY_MONITOR_MESSAGE";
    public static final String MEDIA_ACTION_CP = "CP_PLAY_MONITOR_MESSAGE";
    public static final String MEDIA_EVENT_BLURREDSCREEN_END = "BLURREDSCREEN_END";
    public static final String MEDIA_EVENT_BLURREDSCREEN_START = "BLURREDSCREEN_START";
    public static final String MEDIA_EVENT_BUFFER_END = "BUFFER_END";
    public static final String MEDIA_EVENT_BUFFER_START = "BUFFER_START";
    public static final String MEDIA_EVENT_ERROR_MESSAGE = "ERROR_MESSAGE";
    public static final String MEDIA_EVENT_PAUSE_MESSAGE = "PAUSE_MESSAGE";
    public static final String MEDIA_EVENT_PLAYABE_REPORT = "PLAYABE_REPORT";
    public static final String MEDIA_EVENT_PLAY_EXIT = "PLAY_EXIT";
    public static final String MEDIA_EVENT_PLAY_OPEN = "PLAY_OPEN";
    public static final String MEDIA_EVENT_PLAY_PREPARE = "PLAY_PREPARE";
    public static final String MEDIA_EVENT_PLAY_QUIT = "PLAY_QUIT";
    public static final String MEDIA_EVENT_PLAY_START = "PLAY_START";
    public static final String MEDIA_EVENT_PLAY_STARTUP = "PLAY_STARTUP";
    public static final String MEDIA_EVENT_PREPARE_COMPLETED = "PREPARE_COMPLETED";
    public static final String MEDIA_EVENT_RESUME_MESSAGE = "RESUME_MESSAGE";
    public static final String MEDIA_EVENT_SEEK_END = "SEEK_END";
    public static final String MEDIA_EVENT_SEEK_START = "SEEK_START";
    public static final String MEDIA_EVENT_UNLOAD_END = "UNLOAD_END";
    public static final String MEDIA_EVENT_UNLOAD_START = "UNLOAD_START";
    public static final String MEDIA_SOFTDETECTOR_ACTION = "MEDIA_PLAY_MONITOR_MESSAGE_SOFTDETECTOR";

    public static final int MSG_STORE_DATA = 1002;
    public static final int MSG_PROGRAM_UPLOAD_TASK = 3004;
    public static final int MSG_START_UPLOAD_BOOT = 6001;
    public static final int MSG_START_UPLOAD_PERIOD = 6002;
    public static final int MSG_START_UPLOAD_ALARM = 6003;
    public static final int MSG_START_UPLOAD_NETWORK = 6004;
    public static final int MSG_UPLOAD_NETWORK = 6005;
    public static final int MSG_START_UPLOAD_AUTH = 6006;
    public static final int MSG_START_UPDATE_CHECK = 6007;
    public static final int MSG_REUPLOAD = 6008;
    public static final int MSG_SEND_BROADCAST = 6009;
    public static final int MSG_UPLOAD_PERIOD_DATA = 7001;
    public static final int MSG_UPLOAD_SECOND_DATA = 7002;
    public static final int MSG_SHOW_PROBE_LOG = 7003;
    public static final int MSG_PROGRAM_FIRST_BUFFER_ALARM = 7004;
    public static final int MSG_PROGRAM_FIRST_BUFFER_ALARM_EXIT = 7005;
    public static final int MSG_SHOW_HTTP_REPORT_RESULT = 7006;
    public static final int MSG_SCREEN_STANDBY_START = 7007;
    public static final int MSG_SCREEN_STANDBY_END = 7008;
    public static final int MSG_START_UPDATE = 7009;
    public static final int MSG_SHOW_RESULT_OF_PROBE = 8001;
    public static final int MSG_START_START_NETTY = 9001;
    public static final int MSG_REBOOT = 9002;
    public static final int MSG_TRACEROUTE_REPORTE = 9003;
    public static final int MSG_REQUEST_CONFIG = 9004;
    public static final int MSG_REQUEST_TS = 9005;
    public static final int MSG_CLEAR_DB = 9006;
    public static final int MSG_START_TCP_MANAGER = 9007;
    public static final int MSG_SHOW_TOAST = 9008;
    public static final int MSG_UPDATE_X021 = 9009;
    public static final int MSG_SEND_CAPTURE = 9010;
    public static final int MSG_CHECK_DELAYED = 9011;
    public static final int MSG_HANDLE_RESUME = 9012;
    public static final int MSG_HANDLE_UPLOAD_FILE = 9013;
    public static final int MSG_HANDLE_DELETE_FILE = 9014;
    public static final int MSG_START_TCP_KEEP_LIVE = 9015;
    public static final int MSG_PLAY_QUIT = 9016;
    public static final int MSG_REQUEST_TOKEN = 9017;
    public static final int MSG_REQUEST_STBADDRESS = 9018;
    public static final int MSG_OPEN_WIFI = 9019;
    public static final int MSG_HANDLE_ALARM_MSG = 9020;
    public static final int MSG_REMOTE_VIDEO_TEST = 9023;
    public static final int MSG_UPLOAD_EPG = 9024;
    public static final int MSG_UPLOAD_STREAM_INFO = 9025;
    public static final int MSG_UPLOAD_FAIL_PLAY = 9026;

    public static final String NAME_CAPTURE = "capture";
    public static final String NAME_LOG = "log";
    public static final String NAME_SCREEN = "screen";

    public static final int NETWORK_START_SECOND_MONITOR = 41;
    public static final int NETOWRK_CODE_PING = 51;
    public static final int NETWORK_CODE_SPEED = 52;
    public static final int NETOWRK_CODE_PING_RESULT = 53;
    public static final int NETWORK_CODE_SPEED_RESULT = 54;
    public static final int NETWORK_CODE_TRACERT = 55;
    public static final int NETWORK_CODE_TRACERT_RESULT = 56;
    public static final int NETWORK_CODE_REBOOT = 57;
    public static final int NETWORK_CAPTURE_UPLOAD = 58;
    public static final int NETWORK_STOP_SECOND_MONITOR = 59;
    public static final int NETWORK_REGEIST_NETTY = 60;
    public static final int NETWORK_REGEIST_NETTY_RESPONSE = 61;
    public static final int NETWORK_START_BOOT_CATPURE = 62;
    public static final int NETWORK_STOP_BOOT_CATPURE = 63;
    public static final int NETWORK_START_BOOT_CAPTURE_LOG = 64;
    public static final int NETWORK_STOP_BOOT_CAPTURE_LOG = 65;
    public static final int NETWORK_START_CAPTURE_LOG = 66;
    public static final int NETWORK_START_SCREEN = 67;
    public static final int NETWORK_START_PLAY_TEST = 68;
    public static final int NETWORK_CAPTURE_DELAY_CATPURE = 1058;
    public static final int NETWORK_CAPTURE_DELAY_LOG = 1066;
    public static final int NETWORK_CAPTURE_DELAY_SCREEN = 1067;

    public static final String NIC_TYPE_ETHE = "eth0";
    public static final String NIC_TYPE_WLAN = "wlan0";
    public static final int OPERATION_CODE_PAUSE_END = 75;
    public static final int OPERATION_CODE_PAUSE_START = 74;
    public static final int OPERATION_CODE_QUIT = 0;
    public static final int OPERATION_CODE_SEEK_END = 71;
    public static final int OPERATION_CODE_SEEK_START = 70;
    public static final int OPERATION_CODE_STUTTER_END = 73;
    public static final int OPERATION_CODE_STUTTER_START = 72;
    public static final int OTHER_CODE = -1;

    public static final String PING_DEFAULT_CNT = "5";
    public static final String PING_DEFAULT_INTREVAL = "1";
    public static final String PING_DEFAULT_SIZE = "1000";
    public static final String PLAY_DATA = "play_data";
    public static final String PLAY_ID = "PLAY_ID";
    public static final String PLAY_TIME = "PLAY_TIME";
    public static final String PRE_FEC = "PRE_FEC";
    public static final int PROBE_DATA_INDEX = 2;
    public static final int PROBE_TYPE_INDEX = 0;
    public static final int PROBE_VERSION_INDEX = 1;
    public static final String PROGRAM_BITRATE = "BITRATE";
    public static final int PROGRAM_SUB_CODE_CONTINUED = 62;
    public static final int PROGRAM_SUB_CODE_END = 63;
    public static final int PROGRAM_SUB_CODE_START = 61;
    public static final String PROGRAM_UPLOAD_END_TIME = "upload_end_time";
    public static final String PROGRAM_UPLOAD_PLAY_ID = "upload_play_id";
    public static final String PROGRAM_UPLOAD_START_TIME = "upload_start_time";
    public static final String PROGRAM_UPLOAD_TYPE = "upload_type";
    public static final String PROGRAM_URL = "URL";
    public static final String REDIRECTPATH_DATA = "redirectpath_data";
    public static final String RELEASE_BJ_NETTY_HOST = "sqmpush.cmri.cn";
    public static final int RELEASE_BJ_NETTY_PORT = 10080;
    public static final String RELEASE_BJ_SERVER_ADDRESS = "http://sqmserver.cmri.cn:8080/family/";
    public static final String RELEASE_STB_NETTY_HOST = "tanzhen.zjkdds";
    public static final int RELEASE_STB_NETTY_PORT = 4000;
    public static final String RELEASE_STB_SERVER_ADDRESS = "http://tanzhen.zjkdds:3000/Qcs/";
    public static final String RESOLUTION = "resolution";
    public static final String RESULT = "result";
    public static final int RESULT_SUCCESS = 0;
    public static final String RTP_DATA = "rtp_data";
    public static final String SECONDS = "SECONDS";
    public static final String SEEK_BUFFER_END = "SEEK_BUFFER_END";
    public static final String SEEK_BUFFER_START = "SEEK_BUFFER_START";
    public static final String SEQUENCENO = "sequenceno";
    public static final String SERVER_NAME_SPACE = "cmcc_local_detector_socket_namespace";
    public static int SHUT_NUM = 1;
    public static final String SHUT_STATE = "shut_state";
    public static int SHUT_TIME = 4;
    public static int SHUT_TOTAL_TIME = 2;
    public static final String SHUT_TYPE = "shut_type";
    public static final int START_SECOND = 80;
    public static final String START_TIME = "START_TIME";
    public static final String STATE = "state";

    public static final int STORE_TCP_DATA = 101;
    public static final int STORE_PLAY_DATA = 102;
    public static final int STORE_ALARM_DATA = 103;
    public static final int STORE_IGMP_DATA = 104;
    public static final int STORE_RTP_DATA = 105;
    public static final int STORE_DNS_DATA = 106;

    public static final String STUTTER_MAX_TIME = "shutter_max_time";
    public static final String STUTTER_MIN_TIME = "stutter_min_time";
    public static final String STUTTER_NUM = "shutter_num";
    public static final String STUTTER_TOTAL_TIME = "stutter_total_time";
    public static final String SUB_CODE = "SUB_CODE";
    public static final int SUCCESS = 0;
    public static final String TASKID = "taskId";
    public static final String TCP_DATA = "tcp_data";
    public static final String TIME = "TIME";
    public static final String TOKEN_REQUEST_CNT = "token_request_cnt";

    public static final String TROUTE_DEFAULT_CNT = "3";
    public static final String TROUTE_DEFAULT_TTL = "30";

    public static final String UNLOAD_NUM = "unload_num";
    public static final String UNLOAD_TOTAL_TIME = "unload_total_time";
    public static final String UPDATE_BAKURL = "update_bakurl";
    public static final String UPDATE_URL = "update_url";
    public static final String UPDATE_VERSION = "update_version";
    public static final String UPLOAD_NAME_ADDRESS = "getstbaddress";
    public static final String UPLOAD_NAME_ALARM = "alarm";
    public static final String UPLOAD_NAME_AUTH = "auth";
    public static final String UPLOAD_NAME_BOOT = "boot";
    public static final String UPLOAD_NAME_CHECK_UPDATE = "checkUpdate";
    public static final String UPLOAD_NAME_CONFIG = "config";
    public static final String UPLOAD_NAME_NETWORK = "networkreport";
    public static final String UPLOAD_NAME_PERIODIC = "periodic";
    public static final String UPLOAD_NAME_PROGRAMINFO = "programInfo";
    public static final String UPLOAD_NAME_SECOND = "secondMonitor";
    public static final String UPLOAD_NAME_TOKEN = "getaccesstoken";

    public static final String RESPONSE_CODE_SUCCESS = "0";
    public static final String RESPONSE_CODE_KEY_EXPIRE = "-1";
    public static final String RESPONSE_CODE_PKG_ERROR = "-2";
    public static final String RESPONSE_CODE_TIME_ASYNC = "-3";
    public static final String RESPONSE_CODE_CRT_EXPIRE = "-4";
    public static final String RESPONSE_CODE_OTHER = "-5";

    public static String URL = null;
    public static final int URL_TYPE_EPG = 32;
    public static final int URL_TYPE_M3U8 = 33;
    public static final int URL_TYPE_TS = 34;
    public static final int URL_TYPE_F4V = 36;
    public static final int URL_TYPE_AUTH = 37;
    public static final int URL_TYPE_ALARMTCP = 38;
    public static final int URL_TYPE_OTHER = -1;

    public static final String VERSION_DEBUG = "debug";
    public static final String VERSION_RELEASE = "release";
    public static final String WIDTH = "WIDTH";
    public static String area = "";
    public static final String INTERFACE_VERSION = "3.0";
    public static boolean isCheckedUpdate = true;
    public static int isFirstBuffer = 0;
    public static boolean isHaveStbAddress = false;
    public static boolean isLongTS = false;
    public static boolean isNettyConnected = false;
    public static boolean isTimeCounterRunning = false;
    public static String sStartAlarmResult = "";
    public static boolean showToast = false;
    public static int sSoftProbeModel = 1;

    public static int KILO_BYTES = 1024;
    public static final String NETASCII_EOL = "\r\n";

    private static long standbyEndTime = 0;

    public static final String PINGTESTNODE = "PingTestNode";
    public static final String TRACEROUTETESTNODE = "TracerouteTestNode";
    public static final String REMOTEVIDEOPLAYTEST = "RemoteVideoPlayTest";
    public static final String UPLOADSTEAMINFO = "UploadSteamInfo";

    public static long getStandbyEndTime() {
        return standbyEndTime;
    }

    public static void setStandbyEndTime(long j) {
        standbyEndTime = j;
    }

    public static long getDeviceRunTime() {
        return getActrulRunTime() - standbyEndTime;
    }

    public static long getActrulRunTime() {
        return SystemClock.elapsedRealtime();
    }

    public static long getSysTime(long aRunTime, long aStartTime) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < aStartTime) {
            return aRunTime;
        }
        currentTimeMillis -= aStartTime;
        return currentTimeMillis <= 60000 ? aRunTime - currentTimeMillis : aRunTime;
    }

    public static boolean isEffectiveNum(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (Character.isDigit(str.charAt(length)));
        return false;
    }

    /**
     * formatFloat
     * @param f
     * @return
     */
    public static String formatFloat(float f) {
        return new DecimalFormat("#0.00").format((double) f);
    }

    /**
     * formatRtpLoss
     * @param f
     * @return
     */
    public static String formatRtpLoss(float f) {
        return new DecimalFormat("#0.0000").format((double) f);
    }

    /**
     * formatInt
     * @param i
     * @return
     */
    public static String formatInt(int i) {
        return String.valueOf((((float) i) * 100.0f) / 100.0f);
    }

    /**
     * formatFloat
     * @param j
     * @param j2
     * @return
     */
    public static float formatFloat(long j, long j2) {
        return (((float) j) * 100.0f) / ((float) j2);
    }

    public static String getRightUrl(String str) {
        if (str.length() > 255) {
            return str.substring(0, 254);
        }
        return str;
    }

    public static String getTsname(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile(".*\\.ts").matcher(str);
        if (!matcher.find()) {
            return str;
        }
        String group = matcher.group();
        return group.substring(group.lastIndexOf("/") + 1);
    }
}
