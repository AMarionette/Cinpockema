package com.c9.cinpockema.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.CinemaAdapter;
import com.c9.cinpockema.model.Cinema;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by a694393453 on 2016/4/10.
 */
public class CinemaFragment extends Fragment {
    private ListView cinemaListView;
    private String currentAddress = "无";
    private TextView currentPosition;

    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.v("attach", "Cinema Fragment");
        //位置请求和网络请求
        AMapLocationListener mAMapLocationListener = new AMapLocationListener(){
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        amapLocation.getLatitude();//获取纬度
                        Log.i("Latitude",Double.toString(amapLocation.getLatitude()));
                        amapLocation.getLongitude();//获取经度
                        Log.i("Longitude",Double.toString(amapLocation.getLongitude()));
                        amapLocation.getAccuracy();//获取精度信息
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date date = new Date(amapLocation.getTime());
//                        df.format(date);//定位时间
                          currentAddress = amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                        amapLocation.getCountry();//国家信息
//                        amapLocation.getProvince();//省信息
//                        amapLocation.getCity();//城市信息
//                        amapLocation.getDistrict();//城区信息
//                        amapLocation.getStreet();//街道信息
//                        amapLocation.getStreetNum();//街道门牌号信息
//                        amapLocation.getCityCode();//城市编码
//                        amapLocation.getAdCode();//地区编码
//                        amapLocation.getAoiName();//获取当前定位点的AOI信息
                        currentPosition.setText(amapLocation.getAddress());
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError","location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        };

        mLocationClient = new AMapLocationClient(MyApplication.getContext());
        initOption();
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);

        mLocationClient.startLocation();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cinema_fragment, null);

        currentPosition = (TextView) view.findViewById(R.id.current_location_tv);
        currentPosition.setText(currentAddress);
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
        for (int i = 0; i < 10; i++) {
            Cinema cinema2 = new Cinema();
            cinema2.setName("映联万和影城");
            cinema2.setAddress("海珠区新港东路618号南丰汇广场3楼");
            cinemas.add(cinema2);
        }
        return cinemas;
    }


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }


    private void initOption() {
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);

        if(mLocationOption.isOnceLocationLatest()){
            mLocationOption.setOnceLocationLatest(true);
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
//如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会。
        }

//设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

}


