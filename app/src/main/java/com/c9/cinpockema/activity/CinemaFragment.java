package com.c9.cinpockema.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.CinemaAdapter;
import com.c9.cinpockema.model.Cinema;

import java.util.ArrayList;

/**
 * Created by a694393453 on 2016/4/10.
 */
public class CinemaFragment extends Fragment {
    private ListView cinemaListView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cinema_fragment, null);
        cinemaListView = (ListView) view.findViewById(R.id.cinema_listview);

        CinemaAdapter cinemaAdapter = new CinemaAdapter(getActivity(), getCinemaList());
        cinemaListView.setAdapter(cinemaAdapter);
        //跳转到影院详情页面
        cinemaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity().getApplicationContext(), CinemaInfo.class);
                startActivity(intent);
            }
        });
        //更新
        cinemaAdapter.notifyDataSetChanged();
        return view;
        //return super.onCreateView(inflater,container,savedInstanceState);
    }

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
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }
}


