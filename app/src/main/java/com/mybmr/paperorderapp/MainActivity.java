package com.mybmr.paperorderapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mybmr.paperorderapp.utils.Pref;
import com.mybmr.paperorderappp.R;
import com.rollbar.android.Rollbar;


public class MainActivity extends Activity {
    private WebView webView;

    Activity activity;
    private ProgressDialog progDailog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Log.d("mk", Pref.getValue(MainActivity.this,"a_token",""));
        Log.d("mk", Pref.getValue(MainActivity.this,"m_id",""));

        activity = this;

        Rollbar.init(activity);
        //Rollbar.instance().error(new Exception("This is a test error"));

        progDailog = ProgressDialog.show(activity, "Loading", "Please wait...", true);
        progDailog.setCancelable(false);

        webView = (WebView) findViewById(R.id.webView1);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
            @Override
            public void onReceivedSslError(WebView v, SslErrorHandler handler, SslError er)
            {
                handler.proceed();
            }
        });
        String url = "https://paperapp.mybmr.com?merchant_id="+Pref.getValue(MainActivity.this,"m_id","")
                +"&access_token="+Pref.getValue(MainActivity.this,"a_token","")+"&en="+getString(R.string.environment);

        /*String url = "https://192.168.1.50:8060?merchant_id="+Pref.getValue(MainActivity.this,"m_id","")
                +"&access_token="+Pref.getValue(MainActivity.this,"a_token","")+"&en="+getString(R.string.environment);*/
        Log.d("mk","url="+url);
        webView.loadUrl(url);
    }

    @Override
    // Detect when the back button is pressed
    public void onBackPressed()
    {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            // Let the system handle the back button
            AlertDialog.Builder alert_delete = new AlertDialog.Builder(MainActivity.this);
            alert_delete.setTitle("Exit");
            alert_delete.setMessage("Are you sure you want to exit ?");
            alert_delete.setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    //super.onBackPressed();
                    finish();
                }
            });
            alert_delete.setNegativeButton("No",
                    new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which)
                        {
                            Log.d("logout", "no");
                        }
                    });
            alert_delete.show();
        }
    }

}
