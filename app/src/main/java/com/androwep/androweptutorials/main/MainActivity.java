package com.androwep.androweptutorials.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androwep.androweptutorials.R;
import com.androwep.androweptutorials.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private WebView webView;
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);


        webView = mainBinding.webViewIDmain;
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.androwep.com");
    }

}
