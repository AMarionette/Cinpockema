package com.c9.cinpockema.adapter;

/**
 * Created by marionette on 2016/5/25.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.c9.cinpockema.model.Movie;
import com.c9.cinpockema.R;
import com.c9.cinpockema.model.NetworkHelper;

import java.util.ArrayList;

public class HotMovieAdapter extends BaseAdapter {
    private ArrayList<Movie> movieList;
    private LayoutInflater inflater;

    public HotMovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.movieList = movieList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder movieViewHolder = null;
        if (convertView == null) {
            movieViewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.hot_movie_item, null);
            //获取控件实例
            movieViewHolder.movieImg = (ImageView) convertView.findViewById(R.id.movie_img);
            movieViewHolder.movieName = (TextView) convertView.findViewById(R.id.movie_name);
            movieViewHolder.movieInfo = (TextView) convertView.findViewById(R.id.movie_info);
            movieViewHolder.movieScore = (TextView) convertView.findViewById(R.id.movie_score);
            movieViewHolder.buyTicket = (Button) convertView.findViewById(R.id.buy_ticket);

            convertView.setTag(movieViewHolder);
        } else {
            movieViewHolder = (ViewHolder)convertView.getTag();
        }
        //控件设置
        NetworkHelper.sendImgRequest(movieViewHolder.movieImg, movieList.get(position).getImageUrl());//根据url获取电影图片
        movieViewHolder.movieName.setText(movieList.get(position).getTitle());
        movieViewHolder.movieInfo.setText(movieList.get(position).getSummary());
        movieViewHolder.movieScore.setText(movieList.get(position).getRating()+ "分");
        movieViewHolder.buyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击购票
                Log.v("tag","focus buy ticket");
            }
        });

        return convertView;
    }


    public class ViewHolder{
        public ImageView movieImg;
        public TextView movieName;
        public TextView movieInfo;
        public TextView movieScore;
        public Button buyTicket;
    }
}
