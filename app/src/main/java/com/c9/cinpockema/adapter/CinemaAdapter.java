package com.c9.cinpockema.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.c9.cinpockema.R;
import com.c9.cinpockema.model.Cinema;



import java.util.ArrayList;
import java.util.List;


/**
 * Created by a694393453 on 2016/4/21.
 */
public class CinemaAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Cinema> cinemaList;
    private LatLng myPositionLatLng;//当前经纬度
    public CinemaAdapter(Context context, List<Cinema> cinemaList, LatLng myPositionLatLng) {
        inflater = LayoutInflater.from(context);
        this.cinemaList = cinemaList;
        this.myPositionLatLng = myPositionLatLng;
    }

    public CinemaAdapter(Context context, List<Cinema> cinemaList) {
        inflater = LayoutInflater.from(context);
        this.cinemaList = cinemaList;
    }

    @Override
    public int getCount() {
        return cinemaList.size();
    }

    @Override
    public Object getItem(int position) {
        return cinemaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CinemaViewHolder cinemaViewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cinema_item,null);
            cinemaViewHolder = new CinemaViewHolder();
            cinemaViewHolder.cinemaName = (TextView) convertView.findViewById(R.id.cinema_name);
            cinemaViewHolder.cinemaLocation = (TextView) convertView.findViewById(R.id.cinema_location);
            cinemaViewHolder.cinemaDistance = (TextView) convertView.findViewById(R.id.cinema_distance);
            convertView.setTag(cinemaViewHolder);
        } else {
            cinemaViewHolder = (CinemaViewHolder) convertView.getTag();
        }
        cinemaViewHolder.cinemaName.setText(cinemaList.get(position).getName());
        cinemaViewHolder.cinemaLocation.setText(cinemaList.get(position).getAddress());
        if (myPositionLatLng != null) {
            //影院坐标
            LatLng cinemaLatlng = new LatLng(cinemaList.get(position).getLatitude(), cinemaList.get(position).getLongitude());
            // 计算量坐标点距离
            double dis =AMapUtils.calculateLineDistance(myPositionLatLng, cinemaLatlng);
            if (dis / 1000 >= 1) {
                cinemaViewHolder.cinemaDistance.setText((int)(dis/1000) + "千米");
            } else {
            cinemaViewHolder.cinemaDistance.setText(dis + "米");
            }
        } else {
            cinemaViewHolder.cinemaDistance.setVisibility(View.GONE);
        }
        //Log.v("size:","size is"+cinemaList.size());
        return convertView;
    }

    //展示电影简略信息
    public class CinemaViewHolder {
        public TextView cinemaName;
        public TextView cinemaLocation;
        public TextView cinemaDistance;
    }
}

