package com.c9.cinpockema.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.MovieCommentAdapter;
import com.c9.cinpockema.model.MovieComment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieInfo extends Activity {
    private ListView commentListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_movie_info);

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
}
