package com.cmcc.vr.mid.probe.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.cmcc.vr.mid.probe.constant.ConfigUtils;
import com.cmcc.vr.mid.probe.constant.StaticConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtils
 *
 * @Type FileUtils.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午5:05
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    /**
     * write
     *
     * @param context
     * @param str
     * @param str2
     */
    public static void write(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes());
            openFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * judgeFileNum
     *
     * @param str
     * @param j
     * @return
     */
    public static boolean judgeFileNum(String str, long j) {
        int i = 0;
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles.length < 50) {
            return false;
        }
        int length = listFiles.length;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isFile() && j - 10000 > file2.lastModified()) {
                file2.delete();
            }
            i++;
        }
        return true;
    }

    /**
     * read
     *
     * @param context
     * @param str
     * @return
     */
    public static String read(Context context, String str) {
        try {
            return readInStream(context.openFileInput(str));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * readInStream
     *
     * @param inputStream
     * @return
     */
    public static String readInStream(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[ConfigUtils.MAX_SAVE_NUMS];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return byteArrayOutputStream.toString();
                }
            }
        } catch (IOException e) {
            LogUtils.i("FileTest", e.getMessage());
            return null;
        }
    }

    public static long getDirSize(File file) {
        long j = 0;
        if (file != null && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    j += file2.length();
                } else if (file2.isDirectory()) {
                    j = (j + file2.length()) + getDirSize(file2);
                }
            }
        }
        return j;
    }

    public static long getFileList(File file) {
        File[] listFiles = file.listFiles();
        long length = (long) listFiles.length;
        long j = length;
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                j = (j + getFileList(file2)) - 1;
            }
        }
        return j;
    }

    /**
     * listPathFiles
     *
     * @param str
     * @return
     */
    public static List<File> listPathFiles(String str) {
        List<File> arrayList = new ArrayList();
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        securityManager.checkRead(str);
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                arrayList.add(file2);
            } else {
                listPath(file2.getAbsolutePath());
            }
        }
        return arrayList;
    }

    /**
     * listPaths
     *
     * @param str
     * @return
     */
    public static List<String> listPath(String str) {
        List<String> arrayList = new ArrayList();
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        securityManager.checkRead(str);
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory() && !file2.getName().startsWith(".")) {
                    arrayList.add(file2.getAbsolutePath());
                }
            }
        }
        return arrayList;
    }

    /**
     * clearFileWithPaths
     *
     * @param str
     */
    public static void clearFileWithPath(String str) {
        List<File> listPathFiles = listPathFiles(str);
        if (!listPathFiles.isEmpty()) {
            for (File file : listPathFiles) {
                if (file.isDirectory()) {
                    clearFileWithPath(file.getAbsolutePath());
                } else {
                    file.delete();
                }
            }
        }
    }

    public static String getSDRoot() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * writerFile
     *
     * @param str
     * @param str2
     * @return
     */
    public static boolean writerFile(String str, String str2) {
        File file = new File(str);
        if (file.isDirectory()) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write((str2 + StaticConfig.NETASCII_EOL).getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * writeFormat
     *
     * @param context
     * @param str
     * @param str2
     * @return
     */
    public static boolean writeFormat(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, Context.MODE_APPEND);
            openFileOutput.write((str2 + "\r\n\r\n").getBytes());
            openFileOutput.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * initAssetsToLocal
     *
     * @param context
     * @param fileName
     * @return
     */
    public static boolean initAssetsToLocal(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return false;
        }
        String path = getDir(context, "bin") + File.separator + fileName;
        File file = new File(path);
        if (verifyExists(context, fileName, path)) {
            LogUtils.d("file", "no new file,no need copy");
            return true;
        }
        boolean succeed = copy(context, fileName, path);
        LogUtils.d("file", "copy file " + fileName + ", succeed: " + succeed);
        if (succeed) {
            chmod(path);
            return succeed;
        }
        file.delete();
        return succeed;
    }

    /**
     * getAssetsPathFromLocal
     *
     * @param context
     * @param str
     * @return
     */
    public static String getAssetsPathFromLocal(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = getDir(context, "bin") + File.separator + str;
        if (new File(str2).exists()) {
            return str2;
        }
        return null;
    }

    private static String getDir(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return "";
        }
        return context.getDir(str, 0).toString();
    }

    private static boolean copy(Context context, String name, String path) {
        if (context == null || TextUtils.isEmpty(name) || TextUtils.isEmpty(path)) {
            return false;
        }
        InputStream inputStream = null;
        try {
            boolean result;
            inputStream = context.getAssets().open(name);
            File file = new File(path);
            if (inputStream != null) {
                result = copyStream(inputStream, file);
            } else {
                result = false;
            }
            return result;
        } catch (IOException e) {
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
    }

    private static boolean verifyExists(Context context, String name, String path) {
        try {
            return getMD5(new FileInputStream(new File(path)))
                    .equals(getMD5(context.getAssets().open(name)));
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean copyStream(InputStream inputStream, File file) {
        if (inputStream == null || createFile(file) == null) {
            return false;
        }
        byte[] bytes = new byte[4096];
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);

            int read;
            do {
                read = inputStream.read(bytes);
                if (read > 0) {
                    fileOutputStream.write(bytes, 0, read);
                }
            } while (read >= 0);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {

                    fileOutputStream.flush();
                    sync(fileOutputStream);
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private static boolean sync(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.getFD().sync();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    private static File createFile(File file) {
        if (file == null) {
            return null;
        }
        boolean createNewFile;
        if (file.exists()) {
            file.delete();
        } else {
            file.getParentFile().mkdirs();
        }
        try {
            createNewFile = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            createNewFile = false;
        }
        LogUtils.d("file", "createNewFile: " + file.getAbsolutePath() + ", exists: " + file.exists());
        if (createNewFile) {
            return file;
        }
        return null;
    }

    private static boolean chmod(String str) {
        try {
            Runtime.getRuntime().exec("chmod 777 " + str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static long getResDataSize() {
        File dataDirectory = Environment.getDataDirectory();
        if (dataDirectory == null || !dataDirectory.exists()) {
            return 0;
        }
        return dataDirectory.getUsableSpace();
    }

    /**
     * saveData
     *
     * @param context
     * @param str
     * @param str2
     */
    public static void saveData(Context context, String str, String str2) {
    }

    public static String getMD5(InputStream inputStream) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] bArr = new byte[StaticConfig.KILO_BYTES];
            while (true) {
                int read = inputStream.read(bArr, 0, StaticConfig.KILO_BYTES);
                if (read == -1) {
                    break;
                }
                instance.update(bArr, 0, read);
            }

            return new BigInteger(1, instance.digest()).toString(16);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getDiskUsage() {
        File dataDirectory = Environment.getDataDirectory();
        if (dataDirectory == null || !dataDirectory.exists()) {
            return 0;
        }
        return (int) ((dataDirectory.getTotalSpace() - dataDirectory.getUsableSpace()) * 100
                / dataDirectory.getTotalSpace());
    }
}
