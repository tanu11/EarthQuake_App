package com.example.tanvi.networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("message");
        WebView webView= (WebView) findViewById(R.id.webview);

        webView.loadUrl(url);


    }
}
