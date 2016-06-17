package com.c9.cinpockema.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.MovieCommentAdapter;
import com.c9.cinpockema.model.Constant;
import com.c9.cinpockema.model.FastJsonParser;
import com.c9.cinpockema.model.JsonStringCallBack;
import com.c9.cinpockema.model.Movie;
import com.c9.cinpockema.model.MovieComment;
import com.c9.cinpockema.model.NetworkHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieInfo extends Activity implements View.OnClickListener {
    private Movie movie;//正展示的movie对象
    private ArrayList<MovieComment> comments;
    private ListView commentListView;//评论
    private Button buyTicketButton;//购票按钮
    private ImageView movieImg;//电影海报
    private TextView movieNameTV;//电影名
    private TextView originalTitleTV;//原名
    private TextView ratingTV;//评分
    private TextView genresTV;//类型
    private TextView descriotionTV;//电影介绍
    private TextView wishCountTV;


    //callback
    private JsonStringCallBack movieDetailCallBack = new JsonStringCallBack() {
        @Override
        public void onSuccess(String jsonStr) {
            //获取电影详细信息,未实现
            Movie movieTemp = (Movie) FastJsonParser.objectParse(jsonStr, Movie.class);
            movie.setWishCount(movieTemp.getWishCount());
            movie.setSummary(movieTemp.getSummary());
            updateUI();
        }
    };

    //get comment callback
    private JsonStringCallBack commentCallBack = new JsonStringCallBack() {
        @Override
        public void onSuccess(String jsonStr) {
            comments = (ArrayList<MovieComment>) FastJsonParser.listParse(jsonStr, MovieComment.class);
            //new adapter
            MovieCommentAdapter adapter = new MovieCommentAdapter(MovieInfo.this, comments);
            commentListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_movie_info);

        movieImg = (ImageView) findViewById(R.id.movie_img);
        movieNameTV = (TextView) findViewById(R.id.name);
        originalTitleTV = (TextView) findViewById(R.id.original_title);
        ratingTV = (TextView) findViewById(R.id.rating);
        genresTV = (TextView) findViewById(R.id.genres);
        descriotionTV = (TextView) findViewById(R.id.movie_description);
        wishCountTV = (TextView) findViewById(R.id.wish_count);
        buyTicketButton = (Button) findViewById(R.id.buy_ticket);
        buyTicketButton.setOnClickListener(this);
        commentListView = (ListView) findViewById(R.id.comment_list_view);
        //初始化
        init();
    }

    //初始化
    private void init(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        movie = new Movie();
        movie.setId(bundle.getInt(Constant.MOVIEID));
        movie.setTitle(bundle.getString(Constant.MOVIENAME));
        movie.setOriginalTitle(bundle.getString(Constant.MOVIEORIGINALTITLE));
        movie.setRating(bundle.getDouble(Constant.MOVIERATING));
        movie.setGenres(bundle.getString(Constant.MOVIEGENRES));
        movie.setImageUrl(bundle.getString(Constant.MOVIEIMAGEURL));

        NetworkHelper.sendStrRequest(movieDetailCallBack, Constant.getMovieDetailInfoUrl(movie.getId()));
        NetworkHelper.sendStrRequest(commentCallBack, Constant.getMovieCommentUrl(movie.getId()));
    }


//    public ArrayList<MovieComment> getMovieCommentList() {
//        ArrayList<MovieComment> movieCommentArrayList = new ArrayList<MovieComment>();
//        MovieComment movieComment1 = new MovieComment();
////        movieComment1.setUserName("用户1");
//        movieComment1.setScore(3.5);
//        long time=System.currentTimeMillis();
//        movieComment1.setCreateTime(new Date(time));
//        movieComment1.setContent("评论1");
//        movieCommentArrayList.add(movieComment1);
//        return  movieCommentArrayList;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_ticket:
                //跳转到上映该电影的影院列表的页面
                Intent intent = new Intent(MovieInfo.this, CinemaListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.MOVIEID, movie.getId());
                bundle.putString(Constant.MOVIENAME, movieNameTV.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
        }
    }


    //更新页面
    private void updateUI() {
        NetworkHelper.sendImgRequest(movieImg, movie.getImageUrl());
        movieNameTV.setText(movie.getTitle());
        originalTitleTV.setText(movie.getOriginalTitle());
        ratingTV.setText(Double.toString(movie.getRating()));
        genresTV.setText(movie.getGenres());
        descriotionTV.setText(movie.getSummary());
        wishCountTV.setText("(" + movie.getWishCount() + "人想看" + ")");
    }



}
