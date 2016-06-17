package com.c9.cinpockema.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.c9.cinpockema.R;
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
        Log.v("send request:", "success");
        RequestQueue requestQueue = MyApplication.getRequestQueue();
        CachingStringRequest movieListRequest = new CachingStringRequest(Request.Method.GET, Constant.MOVIELISTURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.v("response:",s);
                        //调用回调函数，数据的解析在回调函数中实现
                        callBack.onSuccess(s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyApplication.getContext(), Constant.NETWORKERROR, Toast.LENGTH_SHORT);
                Log.v("request error", "error");
            }
        }){};
        requestQueue.add(movieListRequest);
    }
    //网络图片请求，缓存
    public static void  sendImgRequest(ImageView imageView, String url) {
        RequestQueue requestQueue = MyApplication.getRequestQueue();
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
        //最后两个参数为default img 和 fault img
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.apple,R.drawable.apple);
        imageLoader.get(url, listener);
    }


    //send request
    public static void sendStrRequest(final JsonStringCallBack callBack, String url) {
        Log.v("send request:", "success");
        RequestQueue requestQueue = MyApplication.getRequestQueue();
        StringRequest movieListRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.v("response:",s);
                        //调用回调函数，数据的解析在回调函数中实现
                        callBack.onSuccess(s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyApplication.getContext(), Constant.NETWORKERROR, Toast.LENGTH_SHORT).show();
                Log.v("request error", "error");
            }
        }){};
        requestQueue.add(movieListRequest);
    }


//    //send movie info request
//    public static void sendMovieInfoRequest(final MovieInfoCallBack callBack, String url) {
//        Log.v("send request:", "success");
//        RequestQueue requestQueue = MyApplication.getRequestQueue();
//        StringRequest movieListRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//                        Log.v("response:",s);
//                        //调用回调函数，数据的解析在回调函数中实现
//                        callBack.onMovieInfoRequestSuccess(s);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(MyApplication.getContext(), Constant.NETWORKERROR, Toast.LENGTH_SHORT).show();
//                Log.v("request error", "error");
//            }
//        }){};
//        requestQueue.add(movieListRequest);
//    }

}
