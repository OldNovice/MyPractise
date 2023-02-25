package com.winfo.mypractise.util;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * APK更新工具包封装
 */
public class ApkUtil {
    private static String FILE_PROVIDER="com.zuanuniverse.universe.fileprovider";
    private static String archive="application/vnd.android.package-archive";
    /**
     * 下载APK新版本
     * @param downloadUrl
     * @param savePath
     *
     */
    public static void downloadApp(final String downloadUrl, final String savePath) {

        new Thread() {
            public void run() {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + savePath);//记得加扩展名
               file.getParentFile().mkdir();

                try {
                    file.createNewFile();
                    URL url2 = new URL(downloadUrl);
                    HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
                    conn.connect();
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream ips = conn.getInputStream();
                        FileOutputStream fops = new FileOutputStream(file);

                        byte[] buf = new byte[1024];
                        int read = ips.read(buf);
                        while (read != -1) {
                            fops.write(buf, 0, read);
                            fops.flush();
                            read = ips.read(buf);
                        }
                        fops.close();
                        ips.close();
                        conn.disconnect();
                    }
                } catch (Exception ignored) {
                }
            }
        }.start();

    }

    /**
     * 安装新的Apk
     * @param activity
     * @param apkPath 安装的apk路径
     */
    public static void installNewApk(Activity activity, String apkPath) {
        File apkFile = new File(apkPath);
        if (!apkFile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //兼容android7.0以上版本
        Uri uri = Uri.fromFile(apkFile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //通过FileProvider创建一个content类型的Uri
            uri = FileProvider.getUriForFile(activity, FILE_PROVIDER, apkFile);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(uri, archive);
        activity.startActivity(intent);
    }

}
