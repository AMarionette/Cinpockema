package com.c9.cinpockema.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;

import com.c9.cinpockema.R;
import com.c9.cinpockema.model.Myself;
import com.c9.cinpockema.model.Order;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;




public class OrderActivity extends AppCompatActivity {


    private static final String url = "http://119.29.93.200/api/orders";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //actionbar设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ActionBar));
        actionBar.setTitle("我的订单");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        getOder();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }


    public void getOder() {
//        Log.i(LoginActivity.Username, LoginActivity.Password);

//        try {
////            HttpUriRequest request = new HttpPost(url);
////
////            String credentials = LoginActivity.Username + ":" + LoginActivity.Password;
////
////            String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
////
////            request.addHeader("Authorization", "Basic " + base64EncodedCredentials);
////
////            HttpClient httpclient = new DefaultHttpClient();
////
////            HttpResponse httpResponse = httpclient.execute(request);
////
////
////            int = httpResponse.getStatusLine().getStatusCode();
//
////            if (statuscode == 201) {
////                statuscode = 201;
////                Log.i("response", httpResponse.getStatusLine().toString());
////            } else {
////                statuscode = 0;
////
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {

                    connection = (HttpURLConnection)((new URL(url.toString()).openConnection()));

                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.i("response", response.toString());
//                    tmp = response.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();


    }

    public ArrayList<Order> getMyselfList1() {
        ArrayList<Order> orders = new ArrayList<Order>();

        Order order0 = new Order();
        order0.getMovie();

        return orders;
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
