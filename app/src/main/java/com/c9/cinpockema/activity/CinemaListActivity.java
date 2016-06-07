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
import com.c9.cinpockema.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class CinemaListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Cinema> cinemaList;//电影对应的影院列表
    private String movieName = "MovieName";//电影名
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
        //获取电影名
        bundle = getIntent().getExtras();
        movieName = bundle.getString(Constant.MOVIENAME);
        //actionbar设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ActionBar));
        actionBar.setTitle(movieName);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        //listview的初始化
        cinemaList = getCinemaList();//test,待删除
        cinemaListView = (ListView)CinemaListActivity.this.findViewById(R.id.cinema_listView);
        CinemaAdapter adapter = new CinemaAdapter(CinemaListActivity.this, cinemaList);
        cinemaListView.setAdapter(adapter);//添加适配器
        cinemaListView.setOnItemClickListener(this);
        adapter.notifyDataSetChanged();

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


    //test
    public ArrayList<Cinema> getCinemaList() {
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>();

        Cinema cinema0 = new Cinema();
        cinema0.setName("金逸珠江国际影城（大学城店）");
        cinema0.setAddress("番禺区小谷围街北岗村中二横路新天地");
        cinemas.add(cinema0);

        Cinema cinema1 = new Cinema();
        cinema1.setName("广东科学中心IMAX巨幕影院");
        cinema1.setAddress("番禺区大学城科普路168号");
        cinemas.add(cinema1);

        Cinema cinema2 = new Cinema();
        cinema2.setName("映联万和影城");
        cinema2.setAddress("海珠区新港东路618号南丰汇广场3楼");
        cinemas.add(cinema2);

        return cinemas;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳转到影院详情页面，携带信息：电影，影院
        Intent intent = new Intent(CinemaListActivity.this, CinemaInfo.class);
        startActivity(intent);
    }
}
