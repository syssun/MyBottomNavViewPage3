package com.sys.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sys.R;
import com.sys.activitys.BaseFragment;
import com.sys.views.LoadingFrame;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FragmentTwo extends BaseFragment {
    View mview;
    String title ;
    TextView v ;
    LoadingFrame frame;
    private static final String TEXTKEY="title";
    Timer timer;
    SmartRefreshLayout smartRefreshLayout;
    public static FragmentTwo newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(TEXTKEY,title);
        FragmentTwo fragmentTwo = new FragmentTwo();
        fragmentTwo.setArguments(bundle);
        return fragmentTwo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString(TEXTKEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mview = inflater.inflate(R.layout.fragment_two, container,false);
        initNavBar(mview,false,"通知",false);
        v = mview.findViewById(R.id.two_text);
        v.setText("通知");
        smartRefreshLayout = mview.findViewById(R.id.refreshLayout);
        //smartRefreshLayout.autoRefresh();
       // smartRefreshLayout.seth
        return mview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v.setText(title);
        doGet();
    }
    public void doGet(){
        String url = "http://www.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("dogetonfailus", "onFailure: ");
                Log.getStackTraceString(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.d("doget", "onResponse: " + str);

                //处理完成后给handler发送消息
                Message msg = new Message();
                msg.what = 0;
                msg.obj=str;
                handler.sendMessage(msg);
            }
        });
    }

    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if(msg.what==0){
               //v.setText(msg.obj.toString());
           }
        }
    };


}
