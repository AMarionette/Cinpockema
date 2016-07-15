package com.c9.cinpockema.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.MyselfAdapter;
import com.c9.cinpockema.model.Myself;

import java.util.ArrayList;

/**
 * Created by Ling on 2016/6/10.
 */

public class MyselfFragment extends Fragment implements View.OnClickListener {

    public static ImageView userHead;
    public static ImageView loginedUserHead;
    private static TextView posiTion;


    private ListView myselfListView1;
    private ListView myselfListView2;
    private ListView myselfListView3;
    private ListView myselfListView4;
    private ListView myselfListView5;
    private ListView myselfListView6;
    private ListView myselfListView7;
    private ListView myselfListView8;

    private RelativeLayout Login;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.v("attach:","MyselfFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myself_fragment, null);

        System.out.println(LoginActivity.is_logined);

        userHead = (ImageView) view.findViewById(R.id.userhead);
        posiTion = (TextView) view.findViewById(R.id.position);
        loginedUserHead = (ImageView) view.findViewById(R.id.loginedUserhead);


        myselfListView1 = (ListView) view.findViewById(R.id.myself_listview1);
        myselfListView2 = (ListView) view.findViewById(R.id.myself_listview2);
        myselfListView3 = (ListView) view.findViewById(R.id.myself_listview3);
        myselfListView4 = (ListView) view.findViewById(R.id.myself_listview4);
        myselfListView5 = (ListView) view.findViewById(R.id.myself_listview5);
        myselfListView6 = (ListView) view.findViewById(R.id.myself_listview6);
        myselfListView7 = (ListView) view.findViewById(R.id.myself_listview7);
        myselfListView8 = (ListView) view.findViewById(R.id.myself_listview8);


        MyselfAdapter myselfAdapter1 = new MyselfAdapter(getActivity(), getMyselfList1());
        myselfListView1.setAdapter(myselfAdapter1);
        MyselfAdapter myselfAdapter2 = new MyselfAdapter(getActivity(), getMyselfList2());
        myselfListView2.setAdapter(myselfAdapter2);
        MyselfAdapter myselfAdapter3 = new MyselfAdapter(getActivity(), getMyselfList3());
        myselfListView3.setAdapter(myselfAdapter3);
        MyselfAdapter myselfAdapter4 = new MyselfAdapter(getActivity(), getMyselfList4());
        myselfListView4.setAdapter(myselfAdapter4);
        MyselfAdapter myselfAdapter5 = new MyselfAdapter(getActivity(), getMyselfList5());
        myselfListView5.setAdapter(myselfAdapter5);
        MyselfAdapter myselfAdapter6 = new MyselfAdapter(getActivity(), getMyselfList6());
        myselfListView6.setAdapter(myselfAdapter6);
        MyselfAdapter myselfAdapter7 = new MyselfAdapter(getActivity(), getMyselfList7());
        myselfListView7.setAdapter(myselfAdapter7);
        MyselfAdapter myselfAdapter8 = new MyselfAdapter(getActivity(), getMyselfList8());
        myselfListView8.setAdapter(myselfAdapter8);
        //我的订单
        myselfListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (LoginActivity.is_logined) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), OrderActivity.class);
                    startActivity(intent);
                 //   getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                //    getActivity().finish();
                }
            }
        });
        myselfAdapter1.notifyDataSetChanged();


        myselfListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (LoginActivity.is_logined) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), MassageActivity.class);
                    startActivity(intent);
                 //   getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                //    getActivity().finish();
                };
            }
        });
        myselfAdapter2.notifyDataSetChanged();

        myselfListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (LoginActivity.is_logined) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), VipActivity.class);
                    startActivity(intent);
                 //   getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                 //   getActivity().finish();
                }
            }
        });
        myselfAdapter3.notifyDataSetChanged();

        myselfListView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (LoginActivity.is_logined) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), AchievementActivity.class);
                    startActivity(intent);
                  //  getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                   // getActivity().finish();
                }
            }
        });
        myselfAdapter4.notifyDataSetChanged();

        myselfListView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (LoginActivity.is_logined) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), CouponActivity.class);
                    startActivity(intent);
                   // getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                  //  getActivity().finish();
                }
            }
        });
        myselfAdapter5.notifyDataSetChanged();

        myselfListView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (LoginActivity.is_logined) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), WalletActivity.class);
                    startActivity(intent);
                  //  getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                  //  getActivity().finish();
                }
            }
        });
        myselfAdapter6.notifyDataSetChanged();

        myselfListView7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (LoginActivity.is_logined) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), ShopActivity.class);
                    startActivity(intent);
                  //  getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                   // getActivity().finish();
                }
            }
        });
        myselfAdapter7.notifyDataSetChanged();

        myselfListView8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity().getApplicationContext(), SettingActivity.class);
                startActivity(intent);
               // getActivity().finish();
            }
        });
        myselfAdapter8.notifyDataSetChanged();



        if (!LoginActivity.is_logined) {
            Login = (RelativeLayout) view.findViewById(R.id.login);
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                  //  getActivity().finish();
                }
            });
        }

        if (LoginActivity.is_logined) {
            setHead();
        }

        return view;
    }

    public ArrayList<Myself> getMyselfList1() {
        ArrayList<Myself> myselfs = new ArrayList<Myself>();

        Myself myself0 = new Myself();
        myself0.setMenu("我的订单");
        myselfs.add(myself0);

        return myselfs;
    }
    public ArrayList<Myself> getMyselfList2() {
        ArrayList<Myself> myselfs = new ArrayList<Myself>();

        Myself myself0 = new Myself();
        myself0.setMenu("我的消息");
        myselfs.add(myself0);

        return myselfs;
    }
    public ArrayList<Myself> getMyselfList3() {
        ArrayList<Myself> myselfs = new ArrayList<Myself>();

        Myself myself0 = new Myself();
        myself0.setMenu("会员中心");
        myselfs.add(myself0);

        return myselfs;
    }
    public ArrayList<Myself> getMyselfList4() {
        ArrayList<Myself> myselfs = new ArrayList<Myself>();

        Myself myself0 = new Myself();
        myself0.setMenu("我的成就");
        myselfs.add(myself0);

        return myselfs;
    }
    public ArrayList<Myself> getMyselfList5() {
        ArrayList<Myself> myselfs = new ArrayList<Myself>();

        Myself myself0 = new Myself();
        myself0.setMenu("优惠券");
        myselfs.add(myself0);

        return myselfs;
    }
    public ArrayList<Myself> getMyselfList6() {
        ArrayList<Myself> myselfs = new ArrayList<Myself>();

        Myself myself0 = new Myself();
        myself0.setMenu("钱包");
        myselfs.add(myself0);

        return myselfs;
    }
    public ArrayList<Myself> getMyselfList7() {
        ArrayList<Myself> myselfs = new ArrayList<Myself>();

        Myself myself0 = new Myself();
        myself0.setMenu("商城");
        myselfs.add(myself0);

        return myselfs;
    }
    public ArrayList<Myself> getMyselfList8() {
        ArrayList<Myself> myselfs = new ArrayList<Myself>();

        Myself myself0 = new Myself();
        myself0.setMenu("设置");
        myselfs.add(myself0);

        return myselfs;
    }

    public static void setHead() {
        userHead.setVisibility(View.INVISIBLE);
        loginedUserHead.setVisibility(View.VISIBLE);
        posiTion.setText(LoginActivity.Username);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//
//            case R.id.layout_top:
//                Intent intent = new Intent(getActivity(),LoginActivity.class);
//                startActivity(intent);
//                break;
            default:
                break;
        }
    }
}
