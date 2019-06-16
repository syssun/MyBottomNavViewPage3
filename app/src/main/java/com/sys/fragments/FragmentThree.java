package com.sys.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sys.R;
import com.sys.activitys.BaseFragment;
import com.sys.views.LoadingFrame;

public class FragmentThree extends BaseFragment {
     View view;
    SmartRefreshLayout smartRefreshLayout;
    LoadingFrame frame;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_three, container,false);
        initNavBar(view,false,"three",false);
        smartRefreshLayout = view.findViewById(R.id.refreshLayout);
       // smartRefreshLayout.autoRefresh();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
