package com.c9.cinpockema.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.model.Movie;
import com.c9.cinpockema.model.NetworkHelper;

import java.util.ArrayList;

/**
 * Created by marionette on 2016/6/3.
 */
public class MovieGalleryAdapter extends BaseAdapter {
    private ArrayList<Movie> movies;
    private Context context;
    public MovieGalleryAdapter(Context context, ArrayList<Movie> movieList){
        this.context = context;
        movies = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            if (!isContainMovie(movieList.get(i))) {
                movies.add(movieList.get(i));
            }
        }
    }


    private boolean isContainMovie(Movie movie) {
        boolean isContain = false;
        for (int i = 0; i < movies.size(); i++) {
            if (movie.getId() == movies.get(i).getId()) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        NetworkHelper.sendImgRequest(imageView, movies.get(position).getImageUrl());
        imageView.setId(position);
        imageView.setLayoutParams(new Gallery.LayoutParams(240, 360));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundColor(Color.GRAY);
        return imageView;
    }
}
