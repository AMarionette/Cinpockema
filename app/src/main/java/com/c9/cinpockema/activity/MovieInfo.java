package com.c9.cinpockema.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.MovieCommentAdapter;
import com.c9.cinpockema.model.Constant;
import com.c9.cinpockema.model.Movie;
import com.c9.cinpockema.model.MovieComment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieInfo extends Activity implements View.OnClickListener {
    private Movie movie;//正展示的movie对象
    private ListView commentListView;
    private Button buyTicketButton;//购票按钮
    private TextView movieNameTV;//电影名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_movie_info);

        movieNameTV = (TextView) findViewById(R.id.name);
        buyTicketButton = (Button) findViewById(R.id.buy_ticket);
        buyTicketButton.setOnClickListener(this);

        commentListView = (ListView) findViewById(R.id.comment_list_view);
        //new adapter
        MovieCommentAdapter adapter = new MovieCommentAdapter(this, getMovieCommentList());
        commentListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<MovieComment> getMovieCommentList() {
        ArrayList<MovieComment> movieCommentArrayList = new ArrayList<MovieComment>();
        MovieComment movieComment1 = new MovieComment();
//        movieComment1.setUserName("用户1");
        movieComment1.setScore(3.5);
        long time=System.currentTimeMillis();
        movieComment1.setCreateTime(new Date(time));
        movieComment1.setContent("评论1");
        movieCommentArrayList.add(movieComment1);
        return  movieCommentArrayList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_ticket:
                //跳转到上映该电影的影院列表的页面
                Intent intent = new Intent(MovieInfo.this, CinemaListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.MOVIENAME, movieNameTV.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
        }
    }
}
