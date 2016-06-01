package com.c9.cinpockema.activity;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by marionette on 2016/5/31.
 */
public class MyApplication extends Application {
    private static Context context;
    private static RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //volley请求队列
        requestQueue = Volley.newRequestQueue(context);
    }
    //获取application context
    public static Context getContext() {
        return context;
    }
    //获取RequestQueue
    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
