package com.mybmr.paperorderapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mybmr.paperorderappp.R;

public class MainActivity1 extends Activity
{	
	WebView webView;
	ProgressDialog mProgress;
	String url_payment;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);

		//Get webview
		webView = (WebView) findViewById(R.id.webView1);

		//startWebView("https://paperapp.mybmr.com/");
        startWebView("https://www.google.com");
	}
	
	private void startWebView(String url) {
        
        //Create new webview Client to show progress dialog
        //When opening a url or click on link
         
        webView.setWebViewClient(new WebViewClient() {     
            ProgressDialog progressDialog;
          
            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {             
                view.loadUrl(url);
                return true;
            }
        
            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(MainActivity1.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
             
        });
          
         // Javascript inabled on webview 
        webView.getSettings().setJavaScriptEnabled(true);
         
        // Other webview options
        /*
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        */
         
        /*
         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webview.loadData(summary, "text/html", null);
         */
         
        //Load url in webview
        webView.loadUrl(url);
	}
	
	// Open previous opened link from history on webview when back button pressed
    
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
        	AlertDialog.Builder alert_delete = new AlertDialog.Builder(MainActivity1.this);
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
    
    @Override
    public void onResume() {
      super.onResume();
    }

    @Override
    public void onPause() {
      super.onPause();
    }

    /** Called before the activity is destroyed. */
    @Override
    public void onDestroy() {
      super.onDestroy();
    }

}
