package com.example.yule.util;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yule.R;
import com.fskj.applibrary.base.SpUtil;
import com.fskj.applibrary.domain.UpdateInfo;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.io.File;

public class AutoUpdateUI {
    private static AutoUpdateUI autoUpdateUI;
    private UpdateInfo mode;

    private Context context;

    public static AutoUpdateUI instance(Context context) {
        if (autoUpdateUI == null) {
            autoUpdateUI = new AutoUpdateUI();
        }
        autoUpdateUI.context = context;
        return autoUpdateUI;
    }

    public void executeUpdateUI(UpdateInfo updateInfo) {
        this.mode = updateInfo;
        updateDialog();
    }

    /**
     * 更新弹框
     *
     * @param
     */
    private void updateDialog() {

        NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(context);
        dialog.setContentView(R.layout.dialog_updata);
        TextView version=dialog.findViewById(R.id.version)  ;
        TextView content=dialog.findViewById(R.id.content)  ;
        version.setText("发现新版本 V"+mode.getVersion());
        if(TextUtils.isEmpty(mode.getRemarks())){
            content.setText(mode.getRemarks());

        }else{
            content.setText("");
        }
        dialog.show();
        dialog.findViewById(R.id.cancel).setOnClickListener(view -> dialog.dismiss());
        dialog.findViewById(R.id.confirm).setOnClickListener(view -> {
            downloadSet();
            Toast.makeText(context, "正在后台下载apk", Toast.LENGTH_SHORT).show();
            dialog.dismiss();


        });
    }

    /**
     * 下载进度弹框
     *
     * @param url
     */
    ProgressBar mProgressBar;
    TextView mDialogText;
    NiftyDialogBuilder dialog;
//    private void downloadDialog() {
//         dialog = NiftyDialogBuilder.getInstance(context);
//        dialog.setContentView(R.layout.dialog_download);
//         mProgressBar = (ProgressBar) dialog.findViewById(R.id.progressbar_two);
//        mDialogText = (TextView) dialog.findViewById(R.id.dialogText);
//        mProgressBar.setProgress(50);
//        dialog.show();
//    }

    /**
     * 下载文件
     *
     * @param url
     */
    @SuppressWarnings("deprecation")
//    private void downloadApkFile(final String url) {
//        downloadSet();
//
//        new Thread() {
//
//            public void run() {
//                HttpClient client = new DefaultHttpClient();
//                HttpGet get = new HttpGet(url);
//                HttpResponse response;
//                try {
//                    response = client.execute(get);
//                    HttpEntity entity = response.getEntity();
//                    long length = entity.getContentLength();
////                    InputStream is = entity.getContent();
//                    CountingInputStream is = new CountingInputStream(
//                            entity.getContent(), new CountingInputStream.ProgressListener() {
//                        @Override
//                        public void transferred(long transferedBytes) {
//                            Log.i("FileDownLoadAsyncTask", "总字节数：" + length
//                                    + " 已下载字节数：" + transferedBytes);
//                            //    publishProgress((int) (100 * transferedBytes / length));
//                            int x = (int) (100 * transferedBytes / length);
//
//                            mDialogText.setText("当前进度：" + x + "%");
//                            mProgressBar.setMax((int) length);
//                            mProgressBar.setProgress((int) transferedBytes);
//
//                        }
//                    });
//                    FileOutputStream fileOutputStream = null;
//                    if (is != null) {
//
//                        File file = new File(Environment.getExternalStorageDirectory(), "joyProperty.apk");
//                        fileOutputStream = new FileOutputStream(file);
//
//                        byte[] buf = new byte[1024];
//                        int ch = -1;
//                        int count = 0;
//                        while ((ch = is.read(buf)) != -1) {
//                            fileOutputStream.write(buf, 0, ch);
//                            count += ch;
//                            if (length > 0) {
//                            }
//                        }
//                    }
//                    fileOutputStream.flush();
//                    if (fileOutputStream != null) {
//                        fileOutputStream.close();
//                    }
//                    downloadSuccess();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }.start();
//
//    }

    private long enqueue;
    private DownloadManager dm;

    private String updateFilePath;
    private File file;

    private void downloadSet() {
        updateFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + "yule" + mode.getUrl() + ".apk";
        file = new File(updateFilePath);
        if(!file.exists()){
            dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//            dm.remove(8285);
            //设置下载地址
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(mode.getUrl()));
            //在通知栏中显示，默认就是显示的
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            request.setTitle("云乐通新版本");
            request.setDescription("正在下载");
            request.setVisibleInDownloadsUi(true);
            //设置下载文件的类型
            request.setMimeType("application/vnd.android.package-archive");
            //设置下载存放的文件夹和文件名字
//            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "app-release.apk" + mode.getAppVersion().getAppVersion() + ".apk");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "yule.apk");
            //设置下载时或者下载完成时，通知栏是否显示
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setTitle("下载云乐通新版本");
            //执行下载，并返回任务唯一id
            enqueue = dm.enqueue(request);
//                        AlertDialogBuilder.getInstance().showToast(context.getResources().getString(R.string.download_new_version), cont ext);
            Log.i("asdsad", "开始下载新版本：" + "," + enqueue);
            SpUtil.put("apk",enqueue+"");
            SpUtil.put("apkVersion",mode.getUrl());
//        updateBtn.setText("下载程序");
//        updateBtn.setEnabled(false);
//        closeBtn.setEnabled(false);
            //注册广播接收者，监听下载状态
            IntentFilter intent=  new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
            context.registerReceiver(new DownLoadBroadcastReceiver(),intent);
        }else{
            updateApk();

        }


    }

    //广播监听下载的各个状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            intent.getStringExtra("url");
            checkStatus();
        }
    };

    //检查下载状态
    private void checkStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        //通过下载的id查找
        query.setFilterById(enqueue);
        Cursor c = dm.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                //下载暂停
                case DownloadManager.STATUS_PAUSED:
                    Log.i("333331111", "下载暂停: ");

                    break;
                //下载延迟
                case DownloadManager.STATUS_PENDING:
                    Log.i("333331111", "下载延迟 ");

                    break;
                //正在下载
                case DownloadManager.STATUS_RUNNING:
                    Log.i("333331111", "正在下载 ");

                    break;
                //下载完成
                case DownloadManager.STATUS_SUCCESSFUL:
                    //下载完成安装APK
                    Log.i("333331111", "下载完成 ");

                    Toast.makeText(context, "下载成功开始安装", Toast.LENGTH_SHORT).show();
                    updateApk();
                    break;
                //下载失败
                case DownloadManager.STATUS_FAILED:
                    Log.i("333331111", "下载失败 ");

                    Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    /**
     * 更新APK文件
     */
    void updateApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
//            Uri uriForFile = FileProvider.getUriForFile(context, "com.tabao.university", file);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.setDataAndType(uriForFile, context.getContentResolver().getType(uriForFile));
//        }else{
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        }
        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            Log.v("asdsad","7.0以上，正在安装apk...");
            //provider authorities
            Uri apkUri = FileProvider.getUriForFile(context, "com.fskj.fun", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            Log.v("asdsad","7.0以下，正在安装apk...");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }

        context.startActivity(intent);
    }

}
