package com.c9.cinpockema.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.c9.cinpockema.R;
import com.c9.cinpockema.model.Constant;
import com.c9.cinpockema.model.FastJsonParser;
import com.c9.cinpockema.model.JsonStringCallBack;
import com.c9.cinpockema.model.MD5;
import com.c9.cinpockema.model.NetworkHelper;
import com.c9.cinpockema.model.User;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.SYSTEM_ALERT_WINDOW;

public class LoginActivity extends AppCompatActivity {

    public static boolean is_logined = false;
    private RequestQueue mRequestQueue;
    private JsonObjectRequest mJsonObjectRequest;
    private  StringRequest mStringRequest;
    private Context mContext;
//    private User user;
//    private int userId = 0;
    public static String Username = null;
    public static String Password = null;
    private String MD5string;
    private int statuscode;
    private boolean isRight = false;
    // UI references.
    private AutoCompleteTextView mUserView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private CheckBox mcb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //actionbar设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ActionBar));
        actionBar.setTitle("登录");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 开启一个子线程，进行网络操作，等待有返回结果，使用handler通知UI


        // Set up the login form.
        mUserView = (AutoCompleteTextView) findViewById(R.id.user);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });
        mcb = (CheckBox)findViewById(R.id.checkBox);
        final SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        if (sp.getBoolean("is_remember", true)) {
            mcb.setChecked(true);
            mUserView.setText(sp.getString("user", ""));
            MD5string = sp.getString("password", "");
            mPasswordView.setText(MD5string);
        } else {
            mUserView.setText(null);
            mPasswordView.setText(null);
        }


        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mcb.isChecked()) {
                    sp.edit().putBoolean("is_remember", true).commit();
                    System.out.println("checkbox is changing to true");
                } else {
                    sp.edit().putBoolean("is_remember", false).commit();
                    System.out.println("checkbox is changing to false");
                }
            }
        });

        final Button mUserBtn = (Button) findViewById(R.id.login_button);
        mUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        login();
                        if (is_logined == true)  {
                        MyselfFragment.setHead();
                        }
                    }
                }).start();
            }
        });
        Button mRegisterBtn = (Button) findViewById(R.id.register_button);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Username = mUserView.getText().toString();
                Password = mPasswordView.getText().toString();
              //  MD5string = MD5.getInstance().getMD5(Password);
                if (attempt()) register();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void login() {
        Username = mUserView.getText().toString();
        Password = mPasswordView.getText().toString();
        Log.i(Username,Password);
        String url = "http://119.29.93.200/api/session";
        try {
            HttpUriRequest request = new HttpPost(url);

            String credentials = Username + ":" + Password;

            String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            request.addHeader("Authorization", "Basic " + base64EncodedCredentials);

            HttpClient httpclient = new DefaultHttpClient();

            HttpResponse httpResponse =  httpclient.execute(request);

            statuscode = httpResponse.getStatusLine().getStatusCode();

            if (statuscode == 201) {
                statuscode = 201;
                Log.i("response",httpResponse.getStatusLine().toString());
            } else {
                statuscode = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(statuscode == 201) {

                    //登录成功后更改用户头像和用户名
                    //TODO
            is_logined = true;

            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();

            Looper.prepare();
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
            Looper.loop();
                } else {
            Looper.prepare();
            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
            Looper.loop();
        }

    }





    /**
     * 利用JsonObjectRequest实现Post请求JSONObject
     */
    private void register() {
        String url = "http://119.29.93.200/api/users";
        mContext = this;
        mRequestQueue = Volley.newRequestQueue(mContext);
        JSONObject object = new JSONObject();
        try {
            object.put("username", Username);
            object.put("password", Password);
            Log.i("register", "登录上传的信息" + object.toString());
            mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                    object, register_createMyReqSuccessListener(),
                    register_createMyReqErrorListener());
            mRequestQueue.add(mJsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Response.Listener<JSONObject> register_createMyReqSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("register", "volley_post_JsonObjectRequest请求结果:"
                            + response);
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


    private Response.ErrorListener register_createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error) {
                    Log.i("register",
                            "volley_post_JsonObjectRequest请求错误:" + error.toString()
                    );
                    Toast.makeText(LoginActivity.this, "该用户名已被注册", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }



    private boolean attempt() {
        if (Username.length() == 0 || Username.length() <= 3 || Username.length() >= 10) {
            Toast.makeText(LoginActivity.this, "该用户名不可用", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Password.length() <= 6 ) {
            Toast.makeText(LoginActivity.this, "密码不能少于6位", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
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

