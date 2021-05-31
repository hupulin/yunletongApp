package com.example.yule.login.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.yule.login.AuthenticationActivity;
import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.api.LoginApi;
import com.fskj.applibrary.api.TicketApi;
import com.fskj.applibrary.base.BaseActivity;
import com.fskj.applibrary.base.BasePresenter;
import com.fskj.applibrary.base.MyObserver;
import com.fskj.applibrary.domain.MessageTo;
import com.fskj.applibrary.domain.main.CardInfoTo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright：杭州方升科技有限公司
 * Created hpl on 2020/12/30 16:24
 * Email:635768909@qq.com
 */
public class AuthenticationPresenter extends BasePresenter {
    public AuthenticationPresenter(BaseActivity activity) {
        initContext(activity);
    }

//type
    public void upLoadIdCard(String type,String path) {
//      File file = saveBitmapFile(ImageCompressUtil.compressPixel(path), path);
        File file  =new File(path);
        showLoadingDialog();
        MultipartBody.Builder      builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("cardPicture", file.getName(), imageBody);
        builder.addFormDataPart("card_type", type);
        List<MultipartBody.Part> parts = builder.build().parts();
        ApiClient.create(LoginApi.class).upLoadIdCard(parts).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                dismissLoadingDialog();
                if(msg.getError_code()==0){
                    submitDataSuccess(msg);
                }else{
                    showMessage((String) msg.getData());
                }
            }
        });
    }
    public static File saveBitmapFile(Bitmap bitmap, String filepath) {
        File file = new File(filepath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public void getCardIdentification() {
        showLoadingDialog();
        ApiClient.create(LoginApi.class).getCardIdentification().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<CardInfoTo>(this) {
            @Override
            public void onNext(CardInfoTo msg) {
                dismissLoadingDialog();
                if(msg.getError_code()==0){
                    getDataSuccess(msg);
                }else{
                    showMessage((String) msg.getData());
                }
            }
        });
    }
    public void uploadPic(String imagePath) {
        showLoadingDialog();
        File file = new File(imagePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
        ApiClient.create(LoginApi.class).upLoadPic(body).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
                if (file.isFile() && file.exists()) {
                    file.delete();
                }
                if (msg.getError_code() == 0) {
                    ((AuthenticationActivity)activity).upLoadPicSuccess();
                } else {
                    String ss = (String) msg.getData();
                    showMessage(ss);
                }
            }
        });
    }
}
