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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.c9.cinpockema.R;
import com.c9.cinpockema.model.Constant;
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
    private HotMovieAdapter hotMovieAdapter;
    private View view;
    //定义常量，用于判断当前用户选择热映还是待映
    private static final int SELECTHOTMOVIE = 1;
    private static final int SELECTUPCOMINGMOVIE = 2;

    private Button hotMovieButton;//顶部热映按钮
    private Button upcomingButton;//顶部待映按钮

    private ProgressBar progressBar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.v("attach:","Movie Fragment");
        //初始化movie list
        movieList = new ArrayList<Movie>();
        //发送请求
        NetworkHelper.sendMovieListRequest(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie_fragment,null);
        //热映和待映的按钮
        hotMovieButton = (Button) view.findViewById(R.id.hot_movie);
        upcomingButton = (Button) view.findViewById(R.id.upcoming_movie);
        //进度
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

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
        //更新UI
        updateUI();
        return view;
//        return super.onCreateView(inflater,container,savedInstanceState);
    }
    //点击热映或未映更新为相应内容
    public void selectChangeUpdate(int selecteWhich){
        switch (selecteWhich){
            case SELECTHOTMOVIE:
                upcomingButton.setTextColor(getResources().getColor(R.color.White));
                upcomingButton.setBackgroundColor(getResources().getColor(R.color.DarkRed));
                hotMovieButton.setTextColor(getResources().getColor(R.color.Red));
                hotMovieButton.setBackgroundColor(getResources().getColor(R.color.White));
//                HotMovieAdapter hotMovieAdapter = new HotMovieAdapter(getActivity(), movieList);
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

    private void updateUI(){
        //电影列表控件实例化
        movieListView = (ListView) view.findViewById(R.id.movie_list);
        if (movieList.size() != 0 && progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        //设置适配器
        hotMovieAdapter = new HotMovieAdapter(getActivity(), movieList);
//        //测试
//        HotMovieAdapter hotMovieAdapter = new HotMovieAdapter(getActivity(), getHotMovieList());
        movieListView.setAdapter(hotMovieAdapter);
        //listviewitem点击事件
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //携带电影id传递过去
                Intent intent = new Intent(getActivity().getApplicationContext(), MovieInfo.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.MOVIEID, movieList.get(position).getId());
                bundle.putString(Constant.MOVIENAME, movieList.get(position).getTitle());
                bundle.putString(Constant.MOVIEORIGINALTITLE, movieList.get(position).getOriginalTitle());
                bundle.putDouble(Constant.MOVIERATING, movieList.get(position).getRating());
                bundle.putString(Constant.MOVIEGENRES, movieList.get(position).getGenres());
                bundle.putString(Constant.MOVIEIMAGEURL, movieList.get(position).getImageUrl());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //数据更新，更新ui
        hotMovieAdapter.notifyDataSetChanged();
    }




    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    //json string call back
    @Override
    public void onSuccess(String jsonStr) {
        movieList = (ArrayList<Movie>) FastJsonParser.listParse(jsonStr, Movie.class);
        updateUI();
    }
}

