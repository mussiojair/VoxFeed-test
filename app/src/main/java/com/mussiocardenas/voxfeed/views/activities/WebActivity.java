package com.mussiocardenas.voxfeed.views.activities;

import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.mussiocardenas.voxfeed.R;
import com.mussiocardenas.voxfeed.presenters.CampaignElement;

public class WebActivity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressBar mProgressBar;
    private Guideline mGuideline;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new VoxFeedWebViewClient());

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mGuideline = (Guideline) findViewById(R.id.guideline);


        Bundle extras = getIntent().getExtras();
        mUrl = extras.getString(CampaignElement.POST_LINK.toString());

        if ( mUrl != null){
            mWebView.loadUrl( mUrl );
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
        }
        return true;
    }


    private class VoxFeedWebViewClient extends WebViewClient {


        public boolean shouldOverrideUrlLoading(WebView webView, String url){
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url){
            view.setVisibility(View.VISIBLE);

            mProgressBar.setVisibility(View.GONE);
            mGuideline.setVisibility(View.GONE);

        }
    }
}
