package com.c9.cinpockema.adapter;

import com.c9.cinpockema.activity.CinemaFragment;
import com.c9.cinpockema.R;
import com.c9.cinpockema.activity.MovieFragment;
import com.c9.cinpockema.activity.MyselfFragment;

/**
 * Created by marionette on 2016/5/25.
 */
public class TabDb {
    public static String[] getTabsText() {
        String[] tabsText = {"电影","影院","我的"};
        return tabsText;
    }
    public static int[] getTabsImgNormal() {
        int[] imgId = {R.drawable.movie_normal,R.drawable.cinema_nomal,R.drawable.myself_normal};
        return imgId;
    }
    public static int[] getTabImgLight() {
        int[] imgId = {R.drawable.movie_light, R.drawable.cinema_light,R.drawable.myself_light};
        return imgId;
    }
    public static Class[] getContentFragments() {
        Class[] contentFragments = {MovieFragment.class, CinemaFragment.class, MyselfFragment.class};
        return contentFragments;
    }
}
