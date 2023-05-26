package com.mybmr.paperorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.mybmr.paperorderapp.utils.AlertUtils;
import com.mybmr.paperorderapp.utils.LogM;
import com.mybmr.paperorderapp.utils.Pref;
import com.mybmr.paperorderapp.utils.ProgressUtils;
import com.mybmr.paperorderappp.R;

/**
 * Created by Mohit on 05/11/18.
 */

public class SplashActivity extends AppCompatActivity  {

    String merchant_id = "";
    String auth_token = "";
    String url;
    ProgressUtils progressUtils;
    private static final int REQUEST_ACCOUNT = 0;

    private boolean isOwner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressUtils = new ProgressUtils(SplashActivity.this);

        progressUtils.showProgressDialog("Please wait...");


    }







    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
