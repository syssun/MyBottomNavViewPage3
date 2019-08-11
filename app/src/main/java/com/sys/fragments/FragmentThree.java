package com.sys.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sys.R;
import com.sys.activitys.BaseFragment;
import com.sys.views.LoadingFrame;

public class FragmentThree extends BaseFragment {
     View view;
    SmartRefreshLayout smartRefreshLayout;
   WebView webView;
    ProgressBar progressBar;
    WebSettings mSetting;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_three, container,false);
        initNavBar(view,false,"记录",false);
        smartRefreshLayout = view.findViewById(R.id.refreshLayout);

        progressBar = view.findViewById(R.id.webview_pro_bar);
        progressBar.setMax(100);
        //smartRefreshLayout.autoRefresh();
        // smartRefreshLayout.seth
        webView = view.findViewById(R.id.thwebview);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d("pro",newProgress+"");
                progressBar.setProgress(newProgress);
                if(newProgress>=100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.imooc.com/");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
