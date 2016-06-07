package com.c9.cinpockema.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.TextView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.MovieGalleryAdapter;
import com.c9.cinpockema.adapter.ScreeningAdapter;
import com.c9.cinpockema.model.Movie;
import com.c9.cinpockema.model.Screening;

import java.util.ArrayList;
import java.util.List;

public class CinemaInfo extends AppCompatActivity {

    private ArrayList<Screening> screenings;
    private ArrayList<Movie> movies;//放映的电影
    private Gallery movieGallery;
    private String cinemaName = "CinemaName";
    private TextView movieNameTV;//显示电影名字
    private TextView movieRatingTV;//电影评分
    private ListView screeningListView;


    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_info);
        init();

        testButton = (Button) findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CinemaInfo.this, SelectSeatActivity.class);
                startActivity(intent);
            }
        });


    }

    private void init(){
        //actionbar设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ActionBar));
        actionBar.setTitle(cinemaName);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();

        movieNameTV = (TextView) CinemaInfo.this.findViewById(R.id.movie_name_tv);
        movieRatingTV = (TextView) CinemaInfo.this.findViewById(R.id.movie_rating_tv);
        //场次信息初始化
        screeningListView = (ListView) findViewById(R.id.screening_list_view);
        ScreeningAdapter screeningAdapter = new ScreeningAdapter(CinemaInfo.this, screenings);
        screeningListView.setAdapter(screeningAdapter);
        screeningAdapter.notifyDataSetChanged();//场次信息更新时，更新UI
        //gallery初始化
        movieGallery = (Gallery) findViewById(R.id.movie_gallery);
        MovieGalleryAdapter adapter = new MovieGalleryAdapter(CinemaInfo.this,getHotMovieList());
        movieGallery.setAdapter(adapter);
        movieGallery.setCallbackDuringFling(false);//仅返回滑动到左后的一个位置
        movieGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item select:", String.valueOf(id));
                //更新下面的内容，包括电影信息和场次信息

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter.notifyDataSetChanged();
    }

    //actionbar上的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回按钮
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //test测试用的，正式删
    private ArrayList<Movie> getHotMovieList() {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        Movie movie = new Movie();
        movie.setTitle("奇幻森林");
        movie.setDescription("男孩混狼群，从小住森林");
        movie.setRating(9.0);
        movies.add(movie);

        Movie movie1 = new Movie();
        movie1.setTitle("伦敦陷落");
        movie1.setDescription("上部炸白宫，这次大本钟");
        movie1.setRating(8.7);
        movies.add(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("冰河追凶");
        movie2.setDescription("冰河男尸案，凶手不一般");
        movie2.setRating(8.0);
        movies.add(movie2);

        for(int i = 0; i < 8; i++) {
            Movie movie3 = new Movie();
            movies.add(movie3);
        }

        return movies;
    }


}
