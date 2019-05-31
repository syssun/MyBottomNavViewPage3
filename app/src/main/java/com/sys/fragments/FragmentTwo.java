package com.sys.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sys.R;
import com.sys.activitys.BaseFragment;
import com.sys.views.LoadingFrame;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentTwo extends BaseFragment {
    View mview;
    String title ;
    TextView v ;
    LoadingFrame frame;
    AVLoadingIndicatorView indicatorView;
    private static final String TEXTKEY="title";
    Timer timer;

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
        initNavBar(mview,false,"two",false);
        v = mview.findViewById(R.id.two_text);
        indicatorView= mview.findViewById(R.id.avi);
        startAVLoading();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopAVLoading();
                    }
                });
            }
        },3000);
        return mview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v.setText(title);
    }
    public void startAVLoading(){
        if(indicatorView !=null)
          indicatorView.show();
    }
    public void stopAVLoading(){
        if(indicatorView !=null)
          indicatorView.hide();
    }
}
