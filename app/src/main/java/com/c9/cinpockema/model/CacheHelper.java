package com.c9.cinpockema.model;

import android.content.Context;
import android.content.pm.ActivityInfo;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.c9.cinpockema.activity.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marionette on 2016/5/31.
 */
public class CacheHelper {

//    //获取缓存中的movie list数据
//    public static List<Movie> getMovieListFromCache() {
//        List<Movie> movieList = new ArrayList<Movie>();
//        if(MyApplication.getRequestQueue().getCache().get(Constant.MOVIELISTURL) != null) {
//            String jsonStr = MyApplication.getRequestQueue().getCache().get(Constant.MOVIELISTURL).data.toString();
//            //解析
//            movieList = FastJsonParser.listParse(jsonStr, Movie.class);
//        }
//        return movieList;
//    }

}
