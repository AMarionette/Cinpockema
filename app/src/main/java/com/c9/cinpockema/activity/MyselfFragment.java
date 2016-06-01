package com.c9.cinpockema.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.c9.cinpockema.R;


/**
 * Created by a694393453 on 2016/4/10.
 */
public class MyselfFragment extends Fragment  {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_myself,null);
        View signin = view.findViewById(R.id.imageButton);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
//        View headLayout = view.findViewById(R.id.layout_top);
//        headLayout.setOnClickListener(this);
        return view;

 //       return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

   // @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
////
//            case R.id.imageButton:
//
//                break;
//            default:
//                break;
//        }
//    }
}
