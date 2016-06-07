package com.c9.cinpockema.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.model.FastJsonParser;
import com.c9.cinpockema.model.JsonStringCallBack;
import com.c9.cinpockema.model.Movie;

import com.c9.cinpockema.adapter.HotMovieAdapter;
import com.c9.cinpockema.model.NetworkHelper;

import java.util.ArrayList;

/**
 * Created by a694393453 on 2016/4/10.
 */
public class MovieFragment extends Fragment implements JsonStringCallBack {
    private ListView movieListView;
    private ArrayList<Movie> movieList;

    //定义常量，用于判断当前用户选择热映还是待映
    private static final int SELECTHOTMOVIE = 1;
    private static final int SELECTUPCOMINGMOVIE = 2;

    private Button hotMovieButton;//顶部热映按钮
    private Button upcomingButton;//顶部待映按钮


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.v("attach:","Movie Fragment");
        NetworkHelper.sendMovieListRequest(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_fragment,null);
        //热映和待映的按钮
        hotMovieButton = (Button) view.findViewById(R.id.hot_movie);
        upcomingButton = (Button) view.findViewById(R.id.upcoming_movie);
        //电影列表控件实例化
       movieListView = (ListView) view.findViewById(R.id.movie_list);
        //设置适配器
        //暂未实现
        //HotMovieAdapter hotMovieAdapter = new HotMovieAdapter(getActivity(), movieList);
        //测试
        HotMovieAdapter hotMovieAdapter = new HotMovieAdapter(getActivity(), getHotMovieList());
        movieListView.setAdapter(hotMovieAdapter);
        //listviewitem点击事件
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //携带电影详情url传递过去,未实现
                Intent intent = new Intent(getActivity().getApplicationContext(), MovieInfo.class);
                startActivity(intent);
            }
        });
        //更新
        hotMovieAdapter.notifyDataSetChanged();

        selectChangeUpdate(SELECTHOTMOVIE);
        hotMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectChangeUpdate(SELECTHOTMOVIE);
            }
        });
        upcomingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectChangeUpdate(SELECTUPCOMINGMOVIE);
            }
        });

        return view;
        //return super.onCreateView(inflater,container,savedInstanceState);
    }
    //点击热映或未映更新为相应内容
    public void selectChangeUpdate(int selecteWhich){
        switch (selecteWhich){
            case SELECTHOTMOVIE:
                upcomingButton.setTextColor(getResources().getColor(R.color.White));
                upcomingButton.setBackgroundColor(getResources().getColor(R.color.DarkRed));
                hotMovieButton.setTextColor(getResources().getColor(R.color.Red));
                hotMovieButton.setBackgroundColor(getResources().getColor(R.color.White));
                HotMovieAdapter hotMovieAdapter = new HotMovieAdapter(getActivity(), getHotMovieList());
                break;
            case SELECTUPCOMINGMOVIE:
                hotMovieButton.setTextColor(getResources().getColor(R.color.White));
                hotMovieButton.setBackgroundColor(getResources().getColor(R.color.DarkRed));
                upcomingButton.setTextColor(getResources().getColor(R.color.Red));
                upcomingButton.setBackgroundColor(getResources().getColor(R.color.White));
                break;
            default:
                break;
        }
    }


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

        return movies;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    //json string call back
    @Override
    public void onSuccess(String jsonStr) {
        movieList = (ArrayList<Movie>) FastJsonParser.listParse(jsonStr, Movie.class);
    }
}

