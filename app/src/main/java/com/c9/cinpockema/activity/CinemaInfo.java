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
import com.c9.cinpockema.model.Cinema;
import com.c9.cinpockema.model.Constant;
import com.c9.cinpockema.model.FastJsonParser;
import com.c9.cinpockema.model.JsonStringCallBack;
import com.c9.cinpockema.model.Movie;
import com.c9.cinpockema.model.NetworkHelper;
import com.c9.cinpockema.model.Screening;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class CinemaInfo extends AppCompatActivity {

    private int cinemaId = 0;
    private Cinema cinema;//影院
    private Movie selectMovie;//选中的电影
    private ArrayList<Screening> screenings;//所有场次
    private ArrayList<Screening> showScreenings;//选中的电影对应的场次
    private ArrayList<Movie> movies;//放映的电影
    private Gallery movieGallery;
    private TextView movieNameTV;//显示电影名字
    private TextView movieRatingTV;//电影评分
    private ListView screeningListView;


    //影院信息相关UI
    private TextView cinemaNameTextView;
    private TextView addressTextView;
    private TextView phoneTextView;


    private Button testButton;

    private JsonStringCallBack getCinemaInfoCallBack = new JsonStringCallBack() {
        @Override
        public void onSuccess(String jsonStr) {
            cinema = (Cinema) FastJsonParser.objectParse(jsonStr, Cinema.class);
            updateCinemaInfo();
        }
    };


    //get screenings call back
    private JsonStringCallBack getScreeningsCallBack = new JsonStringCallBack() {
        @Override
        public void onSuccess(String jsonStr) {
            screenings = (ArrayList<Screening>) FastJsonParser.listParse(jsonStr, Screening.class);
            HashSet<Movie> movieSet = new HashSet<Movie>();
            for (int i = 0; i < screenings.size(); i++) {
                movieSet.add(screenings.get(i).getMovie());
            }
            Iterator<Movie> iterator = movieSet.iterator();
            while (iterator.hasNext()) {
                movies.add(iterator.next());
            }
            createScreeningView();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_info);
        movies = new ArrayList<Movie>();
        //获取传递过来的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cinemaId = bundle.getInt(Constant.CINEMAID);
        //初始化
        init();
//        Log.v("cinemaId:", Integer.toString(cinemaId));
        NetworkHelper.sendStrRequest(getCinemaInfoCallBack, Constant.getCinemaInfoUrl(cinemaId));
        NetworkHelper.sendStrRequest(getScreeningsCallBack, Constant.getScreeningsUrl(cinemaId));

    }

    private void init(){
        //actionbar设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ActionBar));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        //影院相关UI初始化
        cinemaNameTextView = (TextView) findViewById(R.id.cinema_name_tv);
        addressTextView = (TextView) findViewById(R.id.address_tv);
        phoneTextView = (TextView) findViewById(R.id.phone_number_tv);
        //场次相关UI初始化
        movieNameTV = (TextView) CinemaInfo.this.findViewById(R.id.movie_name_tv);
        movieRatingTV = (TextView) CinemaInfo.this.findViewById(R.id.movie_rating_tv);
        //场次信息初始化
        screeningListView = (ListView) findViewById(R.id.screening_list_view);

        //gallery初始化
        movieGallery = (Gallery) findViewById(R.id.movie_gallery);
    }

    private void createScreeningView() {
        MovieGalleryAdapter adapter = new MovieGalleryAdapter(CinemaInfo.this,movies);
        movieGallery.setAdapter(adapter);
        movieGallery.setCallbackDuringFling(false);//仅返回滑动到左后的一个位置
        movieGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item select:", String.valueOf(id));
                //更新下面的内容，包括电影信息和场次信息
                selectMovie = movies.get(position);
                showScreenings = new ArrayList<Screening>();
                for (int i = 0; i < screenings.size(); i++) {
                    if (screenings.get(i).getMovie().getId() == selectMovie.getId()) {
                        showScreenings.add(screenings.get(i));
                    }
                }
                updateUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                //更新下面的内容，包括电影信息和场次信息
//                selectMovie = movies.get(0);
//                showScreenings = new ArrayList<Screening>();
//                for (int i = 0; i < screenings.size(); i++) {
//                    if (screenings.get(i).getMovie().getId() == selectMovie.getId()) {
//                        showScreenings.add(screenings.get(i));
//                    }
//                }
//                updateUI();
            }
        });
        adapter.notifyDataSetChanged();
    }

    //更新UI，包括电影名字、评分、场次
    private void updateUI() {
        movieNameTV.setText(selectMovie.getTitle());
        movieRatingTV.setText(Double.toString(selectMovie.getRating()));
        updateScreenings();
    }

    //update UI ：影院的信息
    private void updateCinemaInfo(){
        cinemaNameTextView.setText(cinema.getName());
        addressTextView.setText(cinema.getAddress());
        phoneTextView.setText(cinema.getPhone());
        getSupportActionBar().setTitle(cinema.getName());
    }


    private void updateScreenings() {
        ScreeningAdapter screeningAdapter = new ScreeningAdapter(CinemaInfo.this, showScreenings);
        screeningListView.setAdapter(screeningAdapter);
        screeningListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("tag","on click");
                Intent intent = new Intent(CinemaInfo.this, SelectSeatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.MOVIENAME, selectMovie.getTitle());
                bundle.putString(Constant.CINEMANAME,cinema.getName());
                bundle.putLong(Constant.MOVIETIME, showScreenings.get(position).getStartTime());
                bundle.putInt(Constant.CINEMAID, cinema.getId());
                bundle.putInt(Constant.HALLID, showScreenings.get(position).getHall().getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        screeningAdapter.notifyDataSetChanged();//场次信息更新时，更新UI
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






}
