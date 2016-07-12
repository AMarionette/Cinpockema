package com.c9.cinpockema.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.c9.cinpockema.R;

public class AchievementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //actionbar设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ActionBar));
        actionBar.setTitle("我的成就");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
    }
    //actionbar上的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回按钮
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
