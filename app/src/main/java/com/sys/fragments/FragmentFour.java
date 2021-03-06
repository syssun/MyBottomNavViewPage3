package com.sys.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sys.R;
import com.sys.activitys.BaseFragment;
import com.sys.activitys.LoginActivity;
import com.sys.adapter.InfoGridAdapter;
import com.sys.entitys.HomeGrid;
import com.sys.entitys.InfoGrid;

import java.util.ArrayList;

public class FragmentFour extends BaseFragment {
    View view;
    RecyclerView recyclerView;
    Button logout;
    InfoGridAdapter infoGridAdapter;
    ArrayList<InfoGrid> infoGrids = new ArrayList<>();
    SmartRefreshLayout smartRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_four, container,false);
        initview();
        return view;
    }
    void initview(){
        infoGrids.clear();
        initNavBar(view,false,"我的",false);
        recyclerView = view.findViewById(R.id.info_grid);
        logout = view.findViewById(R.id.btn_logout);
        smartRefreshLayout = view.findViewById(R.id.f_refreshLayout);
        smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        infoGrids.add(new InfoGrid("name","姓名","syssunyues"));
        infoGrids.add(new InfoGrid("company","公司","极地熊"));
        infoGrids.add(new InfoGrid("dept","部门","信息部"));
        infoGrids.add(new InfoGrid("sex","性别","男"));

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("FILE_NAME", 0);
        String ip = sharedPreferences.getString("IP","192.168.6.131");
        String qq = sharedPreferences.getString("QQ","");
        String WeChat = sharedPreferences.getString("WeChat","");
        String autoctl = sharedPreferences.getString("autoctl","");

        infoGrids.add(new InfoGrid("PORT","PORT","8080",false));
        infoGrids.add(new InfoGrid("IP","IP",ip,true));
        infoGrids.add(new InfoGrid("QQ","QQ",qq,true));
        infoGrids.add(new InfoGrid("WeChat","WeChat",WeChat,true));
        infoGrids.add(new InfoGrid("autoctl","其它命令",autoctl,true));
        infoGridAdapter = new InfoGridAdapter(getActivity(),infoGrids);
        recyclerView.setAdapter(infoGridAdapter);



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initview();
                smartRefreshLayout.finishRefresh();
            }
        });



    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    /**
     * 每次经页面都会刷新
     */
    @Override
    public void onStart() {
        super.onStart();
        smartRefreshLayout.autoRefresh();
    }



}
