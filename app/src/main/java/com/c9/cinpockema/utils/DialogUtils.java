package com.c9.cinpockema.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.c9.cinpockema.R;

/**
 * Created by marionette on 2016/7/13.
 */
public class DialogUtils {
    public static void showLoadingDialog(Context context, String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(context, title,
                    message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }

        progressDialog.show();
    }

    public static void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.progress_layout);
        builder.show();
    }
}
