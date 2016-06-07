package com.c9.cinpockema.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.c9.cinpockema.activity.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marionette on 2016/5/31.
 */
public class NetworkHelper {


    //判断网络状态
    public static boolean isNetworkConnected() {
        Context context = MyApplication.getContext();
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    //获取上映电影列表
    public static void sendMovieListRequest(final JsonStringCallBack callBack) {

        CachingStringRequest movieListRequest = new CachingStringRequest(Request.Method.GET, Constant.MOVIELISTURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //调用回调函数，数据的解析在回调函数中实现
                        callBack.onSuccess(s);
//                        FastJsonParser.listParse(s, Movie.class);//解析json string
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyApplication.getContext(), Constant.NETWORKERROR, Toast.LENGTH_SHORT);
            }
        }){};

    }
}
