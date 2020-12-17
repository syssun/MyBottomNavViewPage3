package com.sys.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentFour extends BaseFragment {
    View view;
    RecyclerView recyclerView;
    //Button logout;
    InfoGridAdapter infoGridAdapter;
    ArrayList<InfoGrid> infoGrids = new ArrayList<>();
    SmartRefreshLayout smartRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_four, container, false);
        initNavBar(view, false, "我的", false);
        recyclerView = view.findViewById(R.id.info_grid);
        // logout = view.findViewById(R.id.btn_logout);
        smartRefreshLayout = view.findViewById(R.id.f_refreshLayout);
        smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //initview();
                smartRefreshLayout.finishRefresh();
            }
        });
        initview();
        return view;
    }
   void initList(){
       infoGrids.clear();
       infoGrids.add(new InfoGrid("name", "名称", "状态"));
       infoGrids.add(new InfoGrid("ANDROID-001", "ANDROID-001", "离线", false));
       infoGrids.add(new InfoGrid("PC-HUAWEI-001", "PC-HUAWEI-001", "离线", false));
       infoGrids.add(new InfoGrid("PIE-001", "PIE-001", "离线", false));
   }
    void initview() {
        Log.d("AAA", "SSS:INITVIEW");
        initList();
        infoGridAdapter = new InfoGridAdapter(getActivity(), infoGrids);
        recyclerView.setAdapter(infoGridAdapter);


//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), LoginActivity.class));
//                getActivity().finish();
//            }
//        });


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
    //public
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                String str = (String) msg.obj;
                Log.d("AAA", "four:" + str);
                if (msg.what == 1) {
                    JSONArray cc = new JSONArray(str);
                    if (cc != null && cc.length() > 0) {
                        JSONObject J = new JSONObject((String) cc.get(0));
                        JSONArray c = new JSONArray(J.getString("all-users"));
                        List<String> ls = new ArrayList<>();
                        for (int i = 0; i < c.length(); i++) {
                            ls.add(c.getString(i));
                        }
                        for (int k=0;k<infoGrids.size();k++) {
                            InfoGrid infoGrid = infoGrids.get(k);
                            boolean f = false ;
                            if(ls.contains(infoGrid.getLabel()) && !infoGrid.getFlag()){ //包含且离线
                                infoGrid.setValue("在线");
                                infoGrid.setFlag(true);
                                f = true;
                            }else if(!ls.contains(infoGrid.getLabel()) && infoGrid.getFlag()){ //不包含切实在线状态
                                infoGrid.setValue("离线");
                                infoGrid.setFlag(false);
                                f = true ;
                            }
                            if(f){
                                infoGridAdapter.notifyItemRangeChanged(k, infoGrids.size());
                            }
                        }
                    }
                }
            } catch (Exception e) {

            }

        }
    };

}
