package com.ywq.ylib.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * @author yanwenqiang
 * @Date 15-7-8
 * @description 文件存储工具
 */
public class StorageUtil {
    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String INDIVIDUAL_DIR_NAME = "uil-images";

    private StorageUtil() {
    }

    /**
     * 获取缓存文件夹
     */
    public static File getCacheDirectory(Context context) {
        return getCacheDirectory(context, true);
    }

    /**
     * 获取缓存文件夹
     */
    public static File getCacheDirectory(Context context, boolean preferExternal) {
        File appCacheDir = null;
        if (preferExternal && "mounted".equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = getExternalCacheDir(context);
        }

        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }

        if (appCacheDir == null) {
            String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
            print("getCacheDirectory", "Can\'t define system cache directory! \'%s\' will be used." + cacheDirPath);
            appCacheDir = new File(cacheDirPath);
        }

        return appCacheDir;
    }

    public static File getIndividualCacheDirectory(Context context) {
        File cacheDir = getCacheDirectory(context);
        File individualCacheDir = new File(cacheDir, "uil-images");
        if (!individualCacheDir.exists() && !individualCacheDir.mkdir()) {
            individualCacheDir = cacheDir;
        }

        return individualCacheDir;
    }

    public static File getOwnCacheDirectory(Context context, String cacheDir) {
        File appCacheDir = null;
        if ("mounted".equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = new File(Environment.getExternalStorageDirectory(), cacheDir);
        }

        if (appCacheDir == null || !appCacheDir.exists() && !appCacheDir.mkdirs()) {
            appCacheDir = context.getCacheDir();
        }

        return appCacheDir;
    }

    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                print("StorageUtil.", "Unable to create external cache directory");
                return null;
            }

            try {
                (new File(appCacheDir, ".nomedia")).createNewFile();
            } catch (IOException var4) {
                print("getExternalCacheDir", "Can\'t create \".nomedia\" file in application external cache directory");
            }
        }

        return appCacheDir;
    }

    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == 0;
    }

    public static File getExternalDir(String rootDirName) {
        File dataDir = new File(Environment.getExternalStorageDirectory(), rootDirName);
        if (!dataDir.exists()) {
            if (!dataDir.mkdirs()) {
                print("StorageUtil.", "Unable to create external " + rootDirName + " directory");
                return null;
            }
        }

        return dataDir;
    }

    private static void print(String methodName, String msg) {
        Log.e("StorageUtil." + methodName, msg);
    }
}
