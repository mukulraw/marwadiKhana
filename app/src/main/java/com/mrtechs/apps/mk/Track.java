package com.mrtechs.apps.mk;

import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Track extends AppCompatActivity {

    Toolbar toolbar;

    WebView myWebView;

    WebSettings webSettings;

    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        progress = (ProgressBar)findViewById(R.id.progress);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        toolbar.setNavigationIcon(R.drawable.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        myWebView = (WebView) findViewById(R.id.web);


        if (myWebView != null) {
            myWebView.setVisibility(View.GONE);
        }

        if (myWebView != null) {
            webSettings = myWebView.getSettings();
        }

        webSettings.setJavaScriptEnabled(true);

        myWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        myWebView.getSettings().setAppCacheEnabled(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);


        myWebView.setWebViewClient(new MyWebViewClient());

        //Add a JavaScriptInterface, so I can make calls from the web to Java methods
        myWebView.addJavascriptInterface(new myJavaScriptInterface(), "CallToAnAndroidFunction");

        myWebView.setWebChromeClient(new MyWebChromeClient());

        progress.setVisibility(View.VISIBLE);

        myWebView.loadUrl("http://marwadikhana.com/index.php/track-order/");

    }

    public class myJavaScriptInterface {
        @JavascriptInterface
        public void setVisible() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    myWebView.setVisibility(View.VISIBLE);
                }
            });
        }

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            myWebView.loadUrl(url);

            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            myWebView.loadUrl("javascript:(function(){" + "document.getElementById('show').style.display ='none';" + "})()");
            //Call to a function defined on my myJavaScriptInterface
            myWebView.loadUrl("javascript: window.CallToAnAndroidFunction.setVisible()");
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        //display alert message in Web View
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            new AlertDialog.Builder(view.getContext())
                    .setMessage(message).setCancelable(true).show();
            result.confirm();
            return true;
        }


        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

            if (newProgress == 100)
            {
                progress.setVisibility(View.GONE);
            }



        }

    }

}
