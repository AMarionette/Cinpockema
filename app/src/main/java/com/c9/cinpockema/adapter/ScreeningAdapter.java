package com.c9.cinpockema.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.activity.SelectSeatActivity;
import com.c9.cinpockema.model.DateUtil;
import com.c9.cinpockema.model.Screening;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by marionette on 2016/6/4.
 */
public class ScreeningAdapter extends BaseAdapter {

    private ArrayList<Screening> screeningArrayList;//场次列表
    private LayoutInflater inflater;
    private Context context;

    public ScreeningAdapter(Context context, ArrayList<Screening> screenings) {
        inflater = LayoutInflater.from(context);
        screeningArrayList = screenings;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (screeningArrayList == null) {
            return 0;
        }
        return screeningArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return screeningArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScreeningViewHolder screeningViewHolder = null;
        if (convertView == null) {
            screeningViewHolder = new ScreeningViewHolder();
            convertView = inflater.inflate(R.layout.screening, null);
            //获取控件实例
            screeningViewHolder.startTime = (TextView) convertView.findViewById(R.id.start_time);
            screeningViewHolder.hallName = (TextView) convertView.findViewById(R.id.hall_name);
            screeningViewHolder.language = (TextView) convertView.findViewById(R.id.language);
            screeningViewHolder.price = (TextView) convertView.findViewById(R.id.price);
            screeningViewHolder.buyTicket = (Button) convertView.findViewById(R.id.buy_ticket);

            convertView.setTag(screeningViewHolder);
        } else {
            screeningViewHolder = (ScreeningViewHolder) convertView.getTag();
        }

        screeningViewHolder.startTime.setText(DateUtil.longToDateStr(screeningArrayList.get(position).getStartTime()));
        screeningViewHolder.hallName.setText(screeningArrayList.get(position).getHall().getName());
        screeningViewHolder.language.setText(screeningArrayList.get(position).getRunningTime() + "分");
        screeningViewHolder.price.setText("50元");
//        screeningViewHolder.buyTicket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //点击购票
//                Log.v("tag","buy ticket");
//                Intent intent = new Intent(context, SelectSeatActivity.class);
//                context.startActivity(intent);
//            }
//        });

        return convertView;
    }


    public class ScreeningViewHolder{
        public TextView startTime;
        public TextView hallName;
        public TextView language;
        public TextView price;
        public Button buyTicket;
    }
}
