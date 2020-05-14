package com.cmcc.vr.mid.probe.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.cmcc.vr.mid.probe.constant.ConfigUtils;

import java.util.HashMap;

public class SharedPreferencesOpt {
    private static String sUrlRegex = "";
    private static String sLogFileName = "";
    private static int sNettyState = -1;
    private static int sProgramState = 0;
    private static boolean sPlayMark = true;
    private static long sTotalRunTime = -1;
    private static long sBlurredScreenTime = 0;
    private static long sUnloadTime = 0;
    private static int sAddressUseCnt = -1;
    private static String sStbAddress = null;
    private static String sEthMac = "";
    private static String sWlanMac = "";
    private static String sBytesHistogram = "";
    private static String sStutterHistogram = "";
    private static String sBlurHistogram = "";
    private static String sUnloadHistogram = "";
    private static String sEpgHistogram = "";
    private static long sStandbyEndTime = -1;
    private static int sProgramPlayState = 0;
    private static int sProgramNextReportType = 0;
    private static long sStbRunTime = -1;
    private static long sBootPlatformTime = -1;
    private static long sBootPlatformCpuTime = -1;
    private static long sBeginPlayTime = -1;
    private static long sPlayPauseTime = 0;
    private static boolean sPlayPauseState = false;
    private static String sFtpServerAddr = "";
    private static String sFtpUser = "";
    private static String sFtpPassword = "";
    private static int sCurrentPlayId = -1;
    private static boolean sIsChangeProgram;
    private static String sCpInfoList = "";
    private static HashMap<String, String> sCpNameInfoMap = new HashMap();
    private static String sCpInfo = "";
    private static boolean mIsStutting = false;
    private static String sTimeCounterData = null;
    private static String sTaskId = null;
    private static String sAlarmTaskFlag = null;
    private static String sTraceDest = null;
    private static long sStutterTime;
    private static String sEpgList = "";

    private static String sAreaCode = "";
    private static String sContentidRegex = "";
    private static String sDnsList = "";
    private static String sEPGDownloadHistogram = "";
    private static String sM3u8IntervalHistogram = "";
    private static String sPreloadCpList = "";
    private static String sSecondsHistogramOfScreenBlurred = "";
    private static String sSecondsHistogramOfUnload = "";
    private static String sSecondsHistogramOfShutter = "";
    private static String sTSDownloadHistogram = "";
    private static String sTsIntervalHistogram = "";
    private static int sUpdateModel = -1;
    private static String sM3u8DownloadHistogram = "";

    /**
     * updateReadCountMap
     *
     * @param str
     */
    public static void updateReadCountMap(String str) {
    }

    /**
     * showReadCountMap
     */
    public static void showReadCountMap() {
    }

    public static void setReportState(Context context, int i) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("detector_sp", 0);
        if (i != getReportState(context)) {
            Editor edit = sharedPreferences.edit();
            edit.putInt("report_state", i);
            edit.commit();
        }
    }

    public static int getReportState(Context context) {
        return context.getSharedPreferences("detector_sp", 0).getInt("report_state", 1);
    }

    public static void setStandbyState(Context context, int i) {
        if (i != getStandbyState(context)) {
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putInt("standby_state", i);
            edit.commit();
        }
    }

    public static int getStandbyState(Context context) {
        return context.getSharedPreferences("detector_sp", 0).getInt("standby_state", 0);
    }

    public static void setStandbyStartTime(Context context, long j) {
        if (j != getStandbyStartTime(context)) {
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putLong("standby_start_time", j);
            edit.commit();
        }
    }

    public static long getStandbyStartTime(Context context) {
        return context.getSharedPreferences("detector_sp", 0).getLong("standby_start_time", 0);
    }

    public static void setStandbyEndTime(Context context, long j) {
        if (j != getStandbyEndTime(context)) {
            sStandbyEndTime = j;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putLong("standby_end_time", j);
            edit.commit();
        }
    }

    public static long getStandbyEndTime(Context context) {
        if (sStandbyEndTime != -1) {
            return sStandbyEndTime;
        }
        long j = context.getSharedPreferences("detector_sp", 0).getLong("standby_end_time", 0);
        sStandbyEndTime = j;
        return j;
    }

    public static void setProgramPlayState(Context context, int i) {
        sProgramPlayState = i;
    }

    public static int getProgramPlayState(Context context) {
        return sProgramPlayState;
    }

    public static void setProgramNextReportType(Context context, int i) {
        sProgramNextReportType = i;
    }

    public static int getProgramNextReportType(Context context) {
        return sProgramNextReportType;
    }

    public static void setStbRunTime(Context context, long j) {
        if (j != 0) {
            sStbRunTime = j;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putLong("stb_run_time", j);
            edit.commit();
        }
    }

    public static long getStbRunTime(Context context) {
        if (sStbRunTime != -1) {
            return sStbRunTime;
        }
        long j = context.getSharedPreferences("detector_sp", 0).getLong("stb_run_time", 0);
        sStbRunTime = j;
        return j;
    }

    public static void setBootPlatformTime(Context context, long j) {
        if (j != getBootPlatformTime(context)) {
            sBootPlatformTime = j;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putLong("boot_platform_time", j);
            edit.commit();
        }
    }

    public static long getBootPlatformTime(Context context) {
        if (sBootPlatformTime != -1) {
            return sBootPlatformTime;
        }
        long j = context.getSharedPreferences("detector_sp", 0).getLong("boot_platform_time", 0);
        sBootPlatformTime = j;
        return j;
    }

    public static void setBootPlatformCpuTime(Context context, long j) {
        if (j != getBootPlatformCpuTime(context)) {
            sBootPlatformCpuTime = j;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putLong("boot_platform_cpu_time", j);
            edit.commit();
        }
    }

    public static long getBootPlatformCpuTime(Context context) {
        if (sBootPlatformCpuTime != -1) {
            return sBootPlatformCpuTime;
        }
        long j = context.getSharedPreferences("detector_sp", 0).getLong("boot_platform_cpu_time",
                0);
        sBootPlatformCpuTime = j;
        return j;
    }

    public static void setCpInfo(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
        edit.putString("cp_apk_name", str);
        edit.putString("cp_pkg_name", str2);
        edit.commit();
    }

    public static String getCpName(Context context) {
        return context.getSharedPreferences("detector_sp", 0).getString("cp_apk_name", "null");
    }

    public static String getCpPkgName(Context context, String cpName) {
        String cpInfo = (String) sCpNameInfoMap.get(cpName);
        if (TextUtils.isEmpty(cpInfo)) {
            cpInfo = context.getSharedPreferences("detector_sp", 0).getString(cpName, null);
            if (!TextUtils.isEmpty(cpInfo)) {
                sCpNameInfoMap.put(cpName, cpInfo);
            }
        }
        return cpInfo;
    }

    public static void setBeginPlayTime(Context context, long j) {
        sBeginPlayTime = j;
    }

    public static long getBeginPlayTime(Context context) {
        return sBeginPlayTime;
    }

    public static void setPlayPauseTime(Context context, long time) {
        sPlayPauseTime = time;
    }

    public static long getPlayPauseTime(Context context) {
        return sPlayPauseTime;
    }

    public static void setPlayPauseState(Context context, boolean z) {
        sPlayPauseState = z;
    }

    public static boolean getPlayPauseState(Context context) {
        return sPlayPauseState;
    }

    public static void setIntervalReportPeriodic(Context context, long j) {
        if (j != getIntervalReportPeriodic(context)) {
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putLong("interval_report_periodic", j);
            edit.commit();
        }
    }

    public static long getIntervalReportPeriodic(Context context) {
        long j = ConfigUtils.INTERVAL_AUTH_PERIOD;
        SharedPreferences sharedPreferences = context.getSharedPreferences("detector_sp", 0);
        try {
            return sharedPreferences.getLong("interval_report_periodic",
                    ConfigUtils.INTERVAL_AUTH_PERIOD);
        } catch (Exception e) {
            Editor edit = sharedPreferences.edit();
            edit.remove("interval_report_periodic");
            edit.putLong("interval_report_periodic", j);
            edit.commit();
            return j;
        }
    }

    public static void setIntervalReportProgram(Context context, long j) {
        if (j != getIntervalReportProgram(context)) {
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putLong("interval_report_program", j);
            edit.commit();
        }
    }

    public static long getIntervalReportProgram(Context context) {
        long j = ConfigUtils.INTERVAL_AUTH_PERIOD;
        SharedPreferences sharedPreferences = context.getSharedPreferences("detector_sp", 0);
        try {
            return sharedPreferences.getLong("interval_report_program",
                    ConfigUtils.INTERVAL_AUTH_PERIOD);
        } catch (Exception e) {
            Editor edit = sharedPreferences.edit();
            edit.remove("interval_report_program");
            edit.putLong("interval_report_program", j);
            edit.commit();
            return j;
        }
    }

    public static void setIntervalM3u8RespThreshold(Context context, int i) {
        if (i != getIntervalM3u8RespThreshold(context)) {
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putInt("interval_m3u8_resp_threshold", i);
            edit.commit();
        }
    }

    public static int getIntervalM3u8RespThreshold(Context context) {
        return context.getSharedPreferences("detector_sp", 0).getInt("interval_m3u8_resp_threshold",
                800);
    }

    public static void setIntervalEpgRespThreshold(Context context, int i) {
        if (i != getIntervalEpgRespThreshold(context)) {
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putInt("interval_epg_resp_threshold", i);
            edit.commit();
        }
    }

    public static int getIntervalEpgRespThreshold(Context context) {
        return context.getSharedPreferences("detector_sp", 0).getInt("interval_epg_resp_threshold",
                6000);
    }

    public static void setIntervalTsRespThreshold(Context context, int i) {
        if (i != getIntervalTsRespThreshold(context)) {
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putInt("interval_ts_resp_threshold", i);
            edit.commit();
        }
    }

    public static int getIntervalTsRespThreshold(Context context) {
        return context.getSharedPreferences("detector_sp", 0).getInt("interval_ts_resp_threshold",
                6000);
    }

    public static void setAlarmStutterRule(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getAlarmStutterRule(context))) {
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("alarm_stutter_rule", str);
            edit.commit();
        }
    }

    public static String getAlarmStutterRule(Context context) {
        return context.getSharedPreferences("detector_sp", 0).getString("alarm_stutter_rule", null);
    }

    public static void setFtpServerAddr(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getFtpServerAddr(context))) {
            sFtpServerAddr = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("ftp_server_addr", str);
            edit.commit();
        }
    }

    public static String getFtpServerAddr(Context context) {
        if (!TextUtils.isEmpty(sFtpServerAddr)) {
            return sFtpServerAddr;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("ftp_server_addr",
                null);
        sFtpServerAddr = string;
        return string;
    }

    public static void setFtpUser(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getFtpUser(context))) {
            sFtpUser = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("ftp_user", str);
            edit.commit();
        }
    }

    public static String getFtpUser(Context context) {
        if (!TextUtils.isEmpty(sFtpUser)) {
            return sFtpUser;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("ftp_user", null);
        sFtpUser = string;
        return string;
    }

    public static void setFtpPasswd(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getFtpPasswd(context))) {
            sFtpPassword = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("ftp_passwd", str);
            edit.commit();
        }
    }

    public static String getFtpPasswd(Context context) {
        if (!TextUtils.isEmpty(sFtpPassword)) {
            return sFtpPassword;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("ftp_passwd",
                null);
        sFtpPassword = string;
        return string;
    }

    public static void setCurrentPlayId(Context context, int i) {
        sCurrentPlayId = i;
    }

    public static int getCurrentPlayId(Context context) {
        return sCurrentPlayId;
    }

    public static void setIsChangeProagram(Context context, boolean z) {
        sIsChangeProgram = z;
    }

    public static boolean isChangeProagram(Context context) {
        return sIsChangeProgram;
    }

    public static void setBooted(Context context, boolean z) {
        if (z != isBooted(context)) {
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putBoolean("isbooted", z);
            edit.commit();
        }
    }

    public static boolean isBooted(Context context) {
        return context.getSharedPreferences("detector_sp", 0).getBoolean("isbooted", false);
    }

    public static String getCpNames(Context context) {
        if (!TextUtils.isEmpty(sCpInfoList)) {
            return sCpInfoList;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("cp_names", null);
        sCpInfoList = string;
        return string;
    }

    public static void setCpInfo(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getCpInfo(context))) {
            sCpInfo = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("cp_name", str);
            edit.commit();
        }
    }

    public static String getCpInfo(Context context) {
        if (!TextUtils.isEmpty(sCpInfo)) {
            return sCpInfo;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("cp_name", null);
        sCpInfo = string;
        return string;
    }

    public static boolean isShutting(Context context) {
        return mIsStutting;
    }

    public static void setShutting(Context context, boolean z) {
        mIsStutting = z;
    }

    public static void setTimeCounterData(Context context, String str) {
        sTimeCounterData = str;
    }

    public static String getTimeCounterData(Context context) {
        return sTimeCounterData;
    }

    public static void setTaskId(Context context, String str) {
        sTaskId = str;
    }

    public static String getTaskId(Context context) {
        return sTaskId;
    }

    public static void setAlarmTaskFlag(Context context, String str) {
        sAlarmTaskFlag = str;
    }

    public static String getAlarmTaskFlag(Context context) {
        return sAlarmTaskFlag;
    }

    public static void setTraceDest(Context context, String str) {
        sTraceDest = str;
    }

    public static String getTraceDest(Context context) {
        return sTraceDest;
    }

    public static void setShuttingTime(Context context, long j) {
        sStutterTime = j;
    }

    public static long getShuttingTime(Context context) {
        return sStutterTime;
    }

    public static void setEpgList(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getEpgList(context))) {
            sEpgList = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("epglist", str);
            edit.commit();
        }
    }

    public static String getEpgList(Context context) {
        if (!TextUtils.isEmpty(sEpgList)) {
            return sEpgList;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("epglist", null);
        sEpgList = string;
        return string;
    }

    public static String getAreaCode(Context context) {
        if (!TextUtils.isEmpty(sAreaCode)) {
            return sAreaCode;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("area_code", null);
        sAreaCode = string;
        return string;
    }

    public static String getContentidRegex(Context context) {
        if (!TextUtils.isEmpty(sContentidRegex)) {
            return sContentidRegex;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("contentid_regex", null);
        sContentidRegex = string;
        return string;
    }

    public static String getDnsList(Context context) {
        if (!TextUtils.isEmpty(sDnsList)) {
            return sDnsList;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("dnslist", null);
        sDnsList = string;
        return string;
    }

    public static String getEPGDownloadHistogram(Context context) {
        if (!TextUtils.isEmpty(sEPGDownloadHistogram)) {
            return sEPGDownloadHistogram;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("EPGDOWNLOADHISTOGRAM", null);
        sEPGDownloadHistogram = string;
        return string;
    }

    public static String getM3u8IntervalHistogram(Context context) {
        if (!TextUtils.isEmpty(sM3u8IntervalHistogram)) {
            return sM3u8IntervalHistogram;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("M3U8INTERVALHISTOGRAM", null);
        sM3u8IntervalHistogram = string;
        return string;
    }

    public static String getM3u8DownloadHistogram(Context context) {
        if (!TextUtils.isEmpty(sM3u8DownloadHistogram)) {
            return sM3u8DownloadHistogram;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("M3U8DOWNLOADHISTOGRAM", null);
        sM3u8DownloadHistogram = string;
        return string;
    }


    public static String getPreloadCpList(Context context) {
        if (!TextUtils.isEmpty(sPreloadCpList)) {
            return sPreloadCpList;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("preload_cplist", null);
        sPreloadCpList = string;
        return string;
    }


    public static String getSecondsHistogramOfScreenBlurred(Context context) {
        if (!TextUtils.isEmpty(sSecondsHistogramOfScreenBlurred)) {
            return sSecondsHistogramOfScreenBlurred;
        }
        String string = context.getSharedPreferences("detector_sp", 0)
                .getString("SECONDSHISTOGRAMOFSCREENBLURRED", null);
        sSecondsHistogramOfScreenBlurred = string;
        return string;
    }

    public static String getSecondsHistogramOfUnload(Context context) {
        if (!TextUtils.isEmpty(sSecondsHistogramOfUnload)) {
            return sSecondsHistogramOfUnload;
        }
        String string = context.getSharedPreferences("detector_sp", 0)
                .getString("SECONDSHISTOGRAMOFUNLOAD", null);  // original is "BYTESHISTOGRAM"
        sSecondsHistogramOfUnload = string;
        return string;
    }

    public static String getSecondsHistogramofShutter(Context context) {
        if (!TextUtils.isEmpty(sSecondsHistogramOfShutter)) {
            return sSecondsHistogramOfShutter;
        }
        String string = context.getSharedPreferences("detector_sp", 0)
                .getString("SECONDSHISTOGRAMOFSHUTTER", null);
        sSecondsHistogramOfShutter = string;
        return string;
    }

    public static String getTSDownloadHistogram(Context context) {
        if (!TextUtils.isEmpty(sTSDownloadHistogram)) {
            return sTSDownloadHistogram;
        }
        String string = context.getSharedPreferences("detector_sp", 0)
                .getString("TSDOWNLOADHISTOGRAM", null);
        sTSDownloadHistogram = string;
        return string;
    }

    public static String getTsIntervalHistogram(Context context) {
        if (!TextUtils.isEmpty(sTsIntervalHistogram)) {
            return sTsIntervalHistogram;
        }
        String string = context.getSharedPreferences("detector_sp", 0)
                .getString("TSINTERVALHISTOGRAM", null);
        sTsIntervalHistogram = string;
        return string;
    }

    public static int getUpdateModel(Context context) {
        if (-1 != sUpdateModel) {
            return sUpdateModel;
        }
        int model = context.getSharedPreferences("detector_sp", 0).getInt("UPDATE_MODE", -1);
        sUpdateModel = model;
        return model;
    }

    public static void setUrlRegex(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getUrlRegex(context))) {
            sUrlRegex = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("urlRegex", str);
            edit.commit();
        }
    }

    public static String getUrlRegex(Context context) {
        if (!TextUtils.isEmpty(sUrlRegex)) {
            return sUrlRegex;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("urlRegex", null);
        sUrlRegex = string;
        return string;
    }

    /**
     * saveLogFileName
     *
     * @param context
     * @param str
     */
    public static void saveLogFileName(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getLogFileName(context))) {
            sLogFileName = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("logFileName", str);
            edit.commit();
        }
    }


    public static String getLogFileName(Context context) {
        if (!TextUtils.isEmpty(sLogFileName)) {
            return sLogFileName;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("logFileName",
                null);
        sLogFileName = string;
        return string;
    }

    /**
     * saveContentidRegex
     *
     * @param context
     * @param str
     */
    public static void saveContentidRegex(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getContentidRegex(context))) {
            sContentidRegex = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("contentid_regex", str);
            edit.commit();
        }
    }

    /**
     * saveUpdateModel
     *
     * @param context
     * @param model
     */
    public static void saveUpdateModel(Context context, int model) {
        if (model != getUpdateModel(context)) {
            sUpdateModel = model;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putInt("UPDATE_MODE", model);
            edit.commit();
        }
    }

    public static void setAreaCode(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getAreaCode(context))) {
            sAreaCode = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("area_code", str);
            edit.commit();
        }
    }

    public static void setDnsList(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getDnsList(context))) {
            sDnsList = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("dnslist", str);
            edit.commit();
        }
    }

    public static void setEPGDownloadHistogram(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getEPGDownloadHistogram(context))) {
            sEPGDownloadHistogram = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("EPGDOWNLOADHISTOGRAM", str);
            edit.commit();
        }
    }

    public static void setM3u8IntervalHistogram(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getM3u8IntervalHistogram(context))) {
            sM3u8IntervalHistogram = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("M3U8INTERVALHISTOGRAM", str);
            edit.commit();
        }
    }

    public static void setSecondsHistogramOfUnload(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getSecondsHistogramOfUnload(context))) {
            sSecondsHistogramOfUnload = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("SECONDSHISTOGRAMOFUNLOAD", str); // original is "BYTESHISTOGRAM"
            edit.commit();
        }
    }

    public static void setSecondsHistogramofShutter(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getSecondsHistogramofShutter(context))) {
            sSecondsHistogramOfShutter = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("SECONDSHISTOGRAMOFSHUTTER", str);
            edit.commit();
        }
    }

    public static void setSecondsHistogramOfScreenBlurred(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getSecondsHistogramOfScreenBlurred(context))) {
            sSecondsHistogramOfScreenBlurred = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("SECONDSHISTOGRAMOFSCREENBLURRED", str);
            edit.commit();
        }
    }

    public static void setTSDownloadHistogram(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getTSDownloadHistogram(context))) {
            sTSDownloadHistogram = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("TSDOWNLOADHISTOGRAM", str);
            edit.commit();
        }
    }

    public static void setTsIntervalHistogram(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getTsIntervalHistogram(context))) {
            sTsIntervalHistogram = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("TSINTERVALHISTOGRAM", str);
            edit.commit();
        }
    }

    public static void setM3u8DownloadHistogram(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getM3u8DownloadHistogram(context))) {
            sM3u8DownloadHistogram = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("M3U8DOWNLOADHISTOGRAM", str);
            edit.commit();
        }
    }

    public static void setPreloadCpList(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getPreloadCpList(context))) {
            sPreloadCpList = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("preload_cplist", str);
            edit.commit();
        }
    }

    public static void setNettyState(Context context, int i) {
        if (i != getNettyState(context)) {
            sNettyState = i;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putInt("netty_state", i);
            edit.commit();
        }
    }

    public static int getNettyState(Context context) {
        if (sNettyState != -1) {
            return sNettyState;
        }
        int i = context.getSharedPreferences("detector_sp", 0).getInt("netty_state", 0);
        sNettyState = i;
        return i;
    }

    public static void setProgramState(Context context, int i) {
        sProgramState = i;
    }

    public static int getProgramState(Context context) {
        return sProgramState;
    }

    public static void setPlayMark(Context context, boolean z) {
        sPlayMark = z;
    }

    public static boolean getPlayMark(Context context) {
        return sPlayMark;
    }

    public static void setTotalRunTime(Context context, long j) {
        if (j != getTotalRunTime(context)) {
            sTotalRunTime = j;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putLong("total_run_time", j);
            edit.commit();
        }
    }

    public static long getTotalRunTime(Context context) {
        if (sTotalRunTime != -1) {
            return sTotalRunTime;
        }
        long j = context.getSharedPreferences("detector_sp", 0).getLong("total_run_time", 0);
        sTotalRunTime = j;
        return j;
    }

    public static void setBlurredscreenTime(Context context, long j) {
        sBlurredScreenTime = j;
    }

    public static long getBlurredscreenTime(Context context) {
        return sBlurredScreenTime;
    }

    public static void setUnloadTime(Context context, long j) {
        sUnloadTime = j;
    }

    public static long getUnloadTime(Context context) {
        return sUnloadTime;
    }

    public static void setAddressUseCnt(Context context, int i) {
        sAddressUseCnt = i;
    }

    public static int getAddressUseCnt(Context context) {
        return sAddressUseCnt;
    }

    public static void setStbAddress(Context context, String str) {
        sStbAddress = str;
    }

    public static String getStbAddress(Context context) {
        return sStbAddress;
    }

    public static void setEthMac(Context context, String str) {
        String ethMac = getEthMac(context);
        if (!TextUtils.isEmpty(sEthMac) && !str.equals(ethMac)) {
            sEthMac = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("ETH0_MAC", str);
            edit.commit();
        }
    }

    public static String getEthMac(Context context) {
        if (!TextUtils.isEmpty(sEthMac)) {
            return "ETH0_MAC";
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("ETH0_MAC", null);
        sEthMac = string;
        return string;
    }

    public static void setWlanMac(Context context, String str) {
        String ethMac = getEthMac(context);
        if (!TextUtils.isEmpty(str) && !str.equals(ethMac)) {
            sWlanMac = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("WLAN_MAC", str);
            edit.commit();
        }
    }

    public static String getWlanMac(Context context) {
        if (!TextUtils.isEmpty(sWlanMac)) {
            return sWlanMac;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("WLAN_MAC", null);
        sWlanMac = string;
        return string;
    }

    public static void setBytesHistogram(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(getBytesHistogram(context))) {
            sBytesHistogram = str;
            Editor edit = context.getSharedPreferences("detector_sp", 0).edit();
            edit.putString("BYTESHISTOGRAM", str);
            edit.commit();
        }
    }

    public static String getBytesHistogram(Context context) {
        if (!TextUtils.isEmpty(sBytesHistogram)) {
            return sBytesHistogram;
        }
        String string = context.getSharedPreferences("detector_sp", 0).getString("BYTESHISTOGRAM",
                null);
        sBytesHistogram = string;
        return string;
    }

}
