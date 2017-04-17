package com.android.shopback.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.shopback.R;
import com.android.shopback.databinding.WebviewBinding;
/**
 * Created by dev on 17/04/17.
 */

public class WebViewActivity extends AppCompatActivity {

    private WebviewBinding webviewBinding;

    @Override protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState );
        webviewBinding =
               DataBindingUtil.
                       setContentView(this, R.layout.webview);

        webviewBinding.webView.setWebViewClient(new MyBrowser());
        webviewBinding.webView.getSettings().setLoadsImagesAutomatically(true);
        webviewBinding.webView.getSettings().setJavaScriptEnabled(true);

        webviewBinding.webView.loadUrl("http://www.cathaycineplexes.com.sg/");
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webviewBinding.progressBar.setVisibility(View.GONE);
        }
    }
}
