package com.c9.cinpockema.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.CinemaAdapter;
import com.c9.cinpockema.model.Cinema;
import com.c9.cinpockema.model.Constant;
import com.c9.cinpockema.model.FastJsonParser;
import com.c9.cinpockema.model.JsonStringCallBack;
import com.c9.cinpockema.model.Movie;
import com.c9.cinpockema.model.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

public class CinemaListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, JsonStringCallBack {

    private ArrayList<Cinema> cinemaList;//电影对应的影院列表
    private String movieName = "MovieName";//电影名
    private int movieId;//movie id
    private Bundle bundle;//由MovieInfo传递过来的bundle
    private ListView cinemaListView;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_list);
        init();
    }
    //初始化
    private void init() {
        //获取电影名,电影id
        bundle = getIntent().getExtras();
        movieName = bundle.getString(Constant.MOVIENAME);
        movieId = bundle.getInt(Constant.MOVIEID);
        NetworkHelper.sendStrRequest(this, Constant.getCinemaListUrl(movieId));
        //actionbar设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ActionBar));
        actionBar.setTitle(movieName);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        //listview的初始化
        cinemaListView = (ListView)CinemaListActivity.this.findViewById(R.id.cinema_listView);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //cinemlistview 的 item 点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳转到影院详情页面，携带信息：电影，影院
        Intent intent = new Intent(CinemaListActivity.this, CinemaInfo.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.MOVIEID, movieId);
        bundle.putInt(Constant.CINEMAID, cinemaList.get(position).getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onSuccess(String jsonStr) {
        cinemaList = (ArrayList<Cinema>) FastJsonParser.listParse(jsonStr, Cinema.class);
        CinemaAdapter adapter = new CinemaAdapter(CinemaListActivity.this, cinemaList);
        cinemaListView.setAdapter(adapter);//添加适配器
        cinemaListView.setOnItemClickListener(this);
        adapter.notifyDataSetChanged();
    }



}
