package com.sys.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sys.R;
import com.sys.activitys.BaseFragment;
import com.sys.adapter.InfoGridAdapter;
import com.sys.entitys.HomeGrid;
import com.sys.entitys.InfoGrid;

import java.util.ArrayList;

public class FragmentFour extends BaseFragment {
    View view;
    RecyclerView recyclerView;
    InfoGridAdapter infoGridAdapter;
    ArrayList<InfoGrid> infoGrids = new ArrayList<>();
    SmartRefreshLayout smartRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_four, container,false);
        initNavBar(view,false,"four",false);
        recyclerView = view.findViewById(R.id.info_grid);
        smartRefreshLayout = view.findViewById(R.id.f_refreshLayout);
        smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        infoGrids.add(new InfoGrid("用户名","syssunyues"));
        infoGrids.add(new InfoGrid("公司","极地熊",true));
        infoGrids.add(new InfoGrid("部门","信息部"));
        infoGrids.add(new InfoGrid("性别","男"));
        infoGrids.add(new InfoGrid("用户ID","0001",true));
        infoGridAdapter = new InfoGridAdapter(getActivity(),infoGrids);
        recyclerView.setAdapter(infoGridAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
