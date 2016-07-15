package com.c9.cinpockema.model;

/**
 * Created by marionette on 2016/5/31.
 */
public class Constant {
    public static final String URL = "http://119.29.93.200:80/api";
    public static final String MOVIELISTURL = "http://119.29.93.200:80/api/movies";
    public static final String NETWORKERROR = "网络连接错误，请重新连接";
    public static final String MOVIENAME = "movieName";
    public static final String MOVIEID = "movieId";
    public static final String MOVIEORIGINALTITLE = "originalTitle";
    public static final String MOVIERATING = "movieRating";
    public static final String MOVIEGENRES = "movieGenres";
    public static final String MOVIEIMAGEURL = "movieImageUrl";
    public static final String MOVIEINFO = "http://119.29.93.200:80/api/movies/";
    public static final String CINEMAID = "cinemaId";

    public static final String MOVIETIME = "movieTime";
    public static final String CINEMANAME = "cinemaName";
    public static final String HALLID = "hallId";

    public static final String SEATOVERMAX = "一次最多只能订购四个座位";
    public static String getMovieDetailInfoUrl(int id) {
        String str = "http://119.29.93.200:80/api/movies/" + id + "/details";
        return str;
    }

    public static String getMovieCommentUrl(int movieId) {
        String str = "http://119.29.93.200:80/api/movies/" + movieId + "/comments";
        return str;
    }

    public static String getCinemaListUrl(int id) {
        String str = "http://119.29.93.200:80/api/movies/" + id + "/cinemas";
        return str;
    }
    public static String getCinemaInfoUrl(int id) {
        String str = "http://119.29.93.200:80/api/cinemas/" + id;
        return str;
    }

    public static String getSeatsUrl(int cinemaId, int hallId) {
        String str = "http://119.29.93.200:80/api/cinemas/" + cinemaId + "/halls/" + hallId + "/seats";
        return str;
    }

    public static String getScreeningsUrl(int cinemaId) {
        String str = "http://119.29.93.200:80/api/cinemas/" + cinemaId + "/screenings";
        return str;
    }
}
