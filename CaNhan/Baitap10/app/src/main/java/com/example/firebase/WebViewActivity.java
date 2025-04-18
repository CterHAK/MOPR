package com.example.firebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.databinding.ActivityWebviewBinding;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebviewBinding binding;

    @SuppressLint({"SetJavaScriptEnabled","WebViewApiAvailability"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        binding.webview.getSettings().setLoadWithOverviewMode(true);
        binding.webview.getSettings().setUseWideViewPort(true);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.setWebViewClient(new WebViewClient());
        binding.webview.getSettings().setBuiltInZoomControls(true);
        binding.webview.getSettings().setDomStorageEnabled(true);
        binding.webview.getSettings().setDatabaseEnabled(true);
        binding.webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        binding.webview.setWebChromeClient(new WebChromeClient());
        binding.webview.loadUrl("https://online.hcmute.edu.vn/");

    }
}
