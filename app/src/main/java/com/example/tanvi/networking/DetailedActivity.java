package com.example.tanvi.networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        WebView webView= (WebView) findViewById(R.id.webview);

        webView.loadUrl("http://google.com");


    }
}
