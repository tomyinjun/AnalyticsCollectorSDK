package com.cmcc.vr.mid.probe.tools;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SystemInfoTools {
    private static SystemInfoTools sInstance;
    private WifiManager mWifiManager;
    private WifiInfo mConnectionInfo;
    private ConnectivityManager mConnectivityManager;
    private Context mContext;
    private Object mEthernetManager;

    private SystemInfoTools(Context context) {
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        mConnectionInfo = mWifiManager.getConnectionInfo();
        mContext = context;
        try {
            Field field = Context.class.getField("ETHERNET_SERVICE");
            mEthernetManager = mContext.getSystemService((String) field.get(field));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (mEthernetManager == null) {
                mEthernetManager = Class.forName("android.net.ethernet.EthernetManager")
                        .getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static SystemInfoTools getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SystemInfoTools(context);
        }
        return sInstance;
    }

    /**
     *
     * @return 设备ID
     */
    public String getDeviceId() {
        String androidId = Settings.System.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return androidId;
    }

    /**
     *
     * @return cpu型号
     */
    public String getCPUInfo() {
        return Build.HARDWARE;
    }

    /**
     *
     * @return 安卓系统版本号
     */
    public String getSystemVersion() {
        return VERSION.RELEASE;
    }

    /**
     *
     * @return 终端固件
     */
    public String getRelease() {
        return VERSION.INCREMENTAL;
    }

    /**
     *
     * @return 终端型号
     */
    public String getModel() {
        return Build.MODEL;
    }

    /**
     *
     * @return 终端厂商
     */
    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getWifiSsid() {
        String str = "";
        try {
            if (!"0".equals(getAccessMethod())) {
                return "-1";
            }
            mConnectionInfo = mWifiManager.getConnectionInfo();
            return mConnectionInfo.getSSID().replaceAll("\"", "");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public String getNetworkId() {
        String str = "";
        if (!"0".equals(getAccessMethod())) {
            return "-1";
        }
        try {
            return String.valueOf(mConnectionInfo.getNetworkId());
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public String getLinkSpeed() {
        String str = "";
        if (!"0".equals(getAccessMethod())) {
            return "-1";
        }
        try {
            return String.valueOf(mConnectionInfo.getLinkSpeed());
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public String getAccessMethod() {
        String str = "";
        try {
            mConnectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null
                    && activeNetworkInfo.getTypeName().toLowerCase().contains("pppoe")) {
                return "2";
            }
            State state = mConnectivityManager.getNetworkInfo(9).getState();
            if (state.equals(State.CONNECTED) || state.equals(State.CONNECTING)) {
                String ethernetMode = getEthenetMode();
                if (!TextUtils.isEmpty(ethernetMode)) {
                    String toLowerCase = ethernetMode.toLowerCase();
                    if (toLowerCase.contains("pppoe")) {
                        return "2";
                    }
                    if (!toLowerCase.contains("dhcp")) {
                        if (toLowerCase.contains("manual")) {
                            return "3";
                        }
                        return str;
                    }
                }
                return "1";
            }

            state = mConnectivityManager.getNetworkInfo(1).getState();
            if (state.equals(State.CONNECTED) || state.equals(State.CONNECTING)) {
                return "0";
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public String getEthenetMode() {
        String str = "";
        try {
            if (mEthernetManager != null) {
                return (String) mEthernetManager.getClass()
                        .getMethod("getEthernetMode", new Class[0])
                        .invoke(mEthernetManager, new Object[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getWirelessSignalStrength() {
        String str = "";
        if (!"0".equals(getAccessMethod())) {
            return "-9999";
        }
        try {
            return String.valueOf(mConnectionInfo.getRssi());
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public long getTotalMemory() {
        try {
            Reader fileReader = new FileReader("/proc/meminfo");
            BufferedReader bufferedReader = new BufferedReader(fileReader, 2048);
            String[] split = bufferedReader.readLine().split("\\s+");
            bufferedReader.close();
            fileReader.close();
            return (long) Integer.parseInt(split[1]);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public long getAvailableMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / 1024;
    }

    public synchronized String getMemUsageTopList(int cnt) {
        String cmd = "top -n 1 -m 20 -s rss";
        List<String> memInfo = new ArrayList<>();

        BufferedReader reader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            int prefix = 7;
            do {
                line = reader.readLine();

                if (line != null) {
                    if (prefix > 0) {
                        prefix--;
                        continue;
                    }
                    String[] element = line.trim().split("\\s+");
                    if (element.length >= 10) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(element[9]).append("(").append(element[0]).append(")")
                                .append(":").append(element[6].replace("K", ""));

                        memInfo.add(builder.toString());
                    }
                }

            } while (line != null && memInfo.size() < cnt);

            StringBuilder builder = new StringBuilder(String.valueOf(getTotalMemory()));
            for (String mem : memInfo) {
                builder.append(",");
                builder.append(mem);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (process != null) {
                try {
                    process.exitValue();
                } catch (Exception e) {
                }
            }
        }
    }

    private static String a(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 8) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 16) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 24) & 255)));
        return stringBuffer.toString();
    }

    private static String getProp(String str) {
        String cmd = "getprop " + str;
        Process exec = null;
        BufferedReader bufferedReader = null;
        try {
            exec = Runtime.getRuntime().exec(cmd);
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            cmd = bufferedReader.readLine();
            if (TextUtils.isEmpty(cmd)) {
                return null;
            } else {
                return cmd;
            }
        } catch (IOException exception) {
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (exec != null) {
                try {
                    exec.exitValue();
                } catch (IllegalThreadStateException e) {
                }
            }
        }
    }
}
