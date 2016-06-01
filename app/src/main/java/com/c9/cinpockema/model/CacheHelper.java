package com.c9.cinpockema.model;

import android.content.Context;
import android.content.pm.ActivityInfo;

import com.c9.cinpockema.activity.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marionette on 2016/5/31.
 */
public class CacheHelper {

    //将数据缓存
    public static void cacheMovieList(String str) {
        ACache cache = ACache.get(MyApplication.getContext());
        cache.put(Constant.MOVIELISTKEY, str);
    }

    //获取缓存中的movie数据
    public static List<Movie> getMovieListFromCache() {
        List<Movie> movieList = new ArrayList<Movie>();
        ACache cache = ACache.get(MyApplication.getContext());
        String jsonStr = cache.getAsString(Constant.MOVIELISTKEY);
        //解析
        movieList = FastJsonParser.listParse(jsonStr, Movie.class);
        return movieList;
    }

}
