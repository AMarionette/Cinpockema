package com.c9.cinpockema.activity;

import android.graphics.Color;
import android.preference.PreferenceActivity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;



import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.c9.cinpockema.R;
import com.c9.cinpockema.adapter.TabDb;
import com.c9.cinpockema.model.LocationUtils;


public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener,View.OnClickListener {

    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化百度地图组件，每个activity使用前必须
        SDKInitializer.initialize(getApplicationContext());
        //设置没有标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //布局
        setContentView(R.layout.activity_main);

        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        tabHost.setup(this, this.getSupportFragmentManager(), R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        initTab();

    }

    //初始化底部栏状态
    private void initTab() {
        String tabs[] = TabDb.getTabsText();
        for(int i = 0; i < tabs.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, TabDb.getContentFragments()[i],null);
            tabHost.setTag(i);
        }
    }


    //tab切换
    @Override
    public void onTabChanged(String tabId) {
//        //test
//        // 天安门坐标
//        double mLat1 = 39.915291;
//        double mLon1 = 116.403857;
//        // 百度大厦坐标
//        double mLat2 = 40.056858;
//        double mLon2 = 200.308194;
//        LatLng pt1 = new LatLng(mLat1, mLon1);
//        LatLng pt2 = new LatLng(mLat2, mLon2);
//        int dis = LocationUtils.getDistance(pt1, pt2);
//        Log.v("distance:",Integer.toString(dis));
        Log.v("tag id is:", tabId);
        updateTab();
    }

    //更新tab
    private void updateTab() {
        TabWidget tabWidget = tabHost.getTabWidget();
        for(int i = 0; i < tabWidget.getChildCount(); i++) {
            View view = tabWidget.getChildAt(i);
            ImageView imageView = (ImageView) view.findViewById(R.id.tabImg);
            TextView textView = (TextView) view.findViewById(R.id.tabText);
            if (i == tabHost.getCurrentTab()) {
                textView.setTextColor(Color.RED);
                imageView.setImageResource(TabDb.getTabImgLight()[i]);
            } else {
                textView.setTextColor(Color.GRAY);
                imageView.setImageResource(TabDb.getTabsImgNormal()[i]);
            }
        }

    }


    //获得每个底部栏标签的视图
    private View getTabView(int index) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.footer_tabs,null);
        TextView textView = (TextView) view.findViewById(R.id.tabText);
        ImageView imageView = (ImageView) view.findViewById(R.id.tabImg);
        textView.setText(TabDb.getTabsText()[index]);
        //初始化操作，默认选择第一个底部栏标签
        if (index == 0) {
            textView.setTextColor(Color.RED);
            imageView.setImageResource(TabDb.getTabImgLight()[index]);
        } else {
            textView.setTextColor(Color.GRAY);
            imageView.setImageResource(TabDb.getTabsImgNormal()[index]);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            default:
                break;
        }
    }
}
