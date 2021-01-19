package com.example.yule.util;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import com.fskj.applibrary.base.SpUtil;

import java.io.File;

public class DownLoadBroadcastReceiver extends BroadcastReceiver {
    private Uri downloadFileUri;
    @Override
    public void onReceive(Context context, Intent intent) {
        long completeId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        Log.d("asdsad","下载完成后的ID:"+completeId);
        String Id = SpUtil.getString("apk");
        long ids = Long.parseLong(Id);
        if (Id.equals(String.valueOf(completeId))){
            DownloadManager manager =
                    (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Intent installIntent=new Intent(Intent.ACTION_VIEW);
            installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) { // 6.0以下
                downloadFileUri= manager
                        .getUriForDownloadedFile(completeId);
                installPackge(context,installIntent,downloadFileUri);
                Log.d("asdsad","下载完成后的downloadFileUri:6.0以下"+downloadFileUri);

            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // 6.0 - 7.0

                File apkFile = queryDownloadedApk(context, ids);
                downloadFileUri = Uri.fromFile(apkFile);
                Log.d("asdsad","下载完成后的downloadFileUri6.0 - 7.0:"+downloadFileUri);
                installPackge(context,installIntent,downloadFileUri);
            }else {
                installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // 给目标应用一个临时授权
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        "yule.apk");
                downloadFileUri = FileProvider.getUriForFile(context,  "com.fskj.fun",file);
                Log.d("asdsad","下载完成后的downloadFileUri:"+downloadFileUri);
                installPackge(context,installIntent,downloadFileUri);
            }
//
//            context.startActivity(installIntent);
        }
    }



    //通过downLoadId查询下载的apk，解决6.0以后安装的问题
    public static File queryDownloadedApk(Context context, long downloadId) {
        File targetApkFile = null;
        DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        if (downloadId != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            Cursor cur = downloader.query(query);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(uriString)) {
                        targetApkFile = new File(Uri.parse(uriString).getPath());
                    }
                }
                cur.close();
            }
        }
        return targetApkFile;
    }

    /**
     * 安装APK
     * @param context
     * @param intentInstall
     * @param uri
     */
    private void installPackge(Context context, Intent intentInstall, Uri uri){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intentInstall.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intentInstall.setDataAndType(uri, "application/vnd.android.package-archive");
        } else {
            intentInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentInstall.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        context.startActivity(intentInstall);

    }


}
