package com.igk.websitesandvideosshortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowWeb extends AppCompatActivity {
    private WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web);
        browser = findViewById(R.id.webView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("web");
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(url);
        browser.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
    }

    @Override
    public void onBackPressed() {
        if(browser.canGoBack())
            browser.goBack();
        else
        super.onBackPressed();
    }
}