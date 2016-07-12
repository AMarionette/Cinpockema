package com.c9.cinpockema.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.model.DateUtil;
import com.c9.cinpockema.model.MovieComment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marionette on 2016/5/29.
 */
public class MovieCommentAdapter extends BaseAdapter {
    private ArrayList<MovieComment> movieCommentList;
    private LayoutInflater inflater;

    public MovieCommentAdapter(Context context, ArrayList<MovieComment> movieCommentList){
        inflater = LayoutInflater.from(context);
        this.movieCommentList = movieCommentList;
    }

    @Override
    public int getCount() {
        return movieCommentList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieCommentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieCommentViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.movie_commet, null);
            viewHolder = new MovieCommentViewHolder();
            //实例化viewholder中的控件
            viewHolder.userNameTextView = (TextView) convertView.findViewById(R.id.name_tv);
            viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.movie_comment_rating_bar);
            viewHolder.commentDate = (TextView) convertView.findViewById(R.id.comment_date);
            viewHolder.commentContent = (TextView) convertView.findViewById(R.id.movie_comment_content);
            convertView.setTag(viewHolder);//控件复用
        } else {
            viewHolder = (MovieCommentViewHolder) convertView.getTag();
        }

        //显示评论
        MovieComment movieComment = movieCommentList.get(position);
        viewHolder.userNameTextView.setText(movieComment.getUser().getNickName());
        viewHolder.ratingBar.setRating((float) movieComment.getScore());
        viewHolder.commentDate.setText(DateUtil.longToDateStr(movieComment.getCreateTime()));
        viewHolder.commentContent.setText(movieComment.getContent());

        return convertView;
    }
    //评论中的控件
    public class MovieCommentViewHolder{
        public TextView userNameTextView;
        public RatingBar ratingBar;
        public TextView commentDate;
        public TextView commentContent;
    }


}
