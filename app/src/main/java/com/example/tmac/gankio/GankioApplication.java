package com.example.tmac.gankio;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.OkHttpClient;

/**
 * Created by Tmac on 2016/11/24.
 */

public class GankioApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new LoggerInterceptor("gankio")).build();
        OkHttpUtils.initClient(client);
    }
}
