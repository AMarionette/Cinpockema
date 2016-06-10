package com.c9.cinpockema.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.model.Myself;

import java.util.ArrayList;

/**
 * Created by Ling on 2016/6/10 0010.
 */
public class MyselfAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<Myself> myselfList;

    public MyselfAdapter(Context context, ArrayList<Myself> myselfList) {
        inflater = LayoutInflater.from(context);
        this.myselfList = myselfList;
    }
    @Override
    public int getCount() {
        return myselfList.size();
    }

    @Override
    public Object getItem(int position) {
        return myselfList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyselfViewHolder myselfViewHolder = null;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.myself_item,null);
            myselfViewHolder = new MyselfViewHolder();
            myselfViewHolder.menuName = (TextView) convertView.findViewById(R.id.menu_name);
        } else {
            myselfViewHolder = (MyselfViewHolder) convertView.getTag();
        }
        myselfViewHolder.menuName.setText(myselfList.get(position).getMenu());
        return convertView;
    }



    public class MyselfViewHolder{
        public TextView menuName;
    }


}
