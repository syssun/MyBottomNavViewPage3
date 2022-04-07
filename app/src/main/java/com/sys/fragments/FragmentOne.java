package com.sys.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sys.R;
import com.sys.activitys.BaseFragment;
import com.sys.adapter.HomeGridAdapter;
import com.sys.entitys.HomeGrid;
import com.sys.loader.GlideImageLoader;
import com.sys.views.LoadingFrame;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class FragmentOne extends BaseFragment {
    ArrayList<Integer> images ;
    private static final String BUNKEY="images";
    RecyclerView recyclerView;
    View view;
    Banner banner;
    HomeGridAdapter homeGridAdapter;
    LoadingFrame frame;
    public static  FragmentOne newInstance(ArrayList<Integer> images){
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(BUNKEY,images);
        FragmentOne fragmentOne = new FragmentOne();
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args =  getArguments();
        images =  args.getIntegerArrayList(BUNKEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one,container,false);
        initNavBar(view,false,"首页",false);
        banner = view.findViewById(R.id.banner);
        recyclerView = view.findViewById(R.id.rv_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        ArrayList<HomeGrid> homeGrids = new ArrayList<>();
        homeGrids.add(new HomeGrid(R.mipmap.suoping,"锁屏","lockscreen"));
        homeGrids.add(new HomeGrid(R.mipmap.shutdow,"60S关机","shutdown"));
        homeGrids.add(new HomeGrid(R.mipmap.canceo,"取消关机","canceshutdow"));
        homeGrids.add(new HomeGrid(R.mipmap.qqo,"打开QQ","openqq"));
        homeGrids.add(new HomeGrid(R.mipmap.qqc,"关闭QQ","closeqq"));
        homeGrids.add(new HomeGrid(R.mipmap.jisuanqi,"计算器","calc"));
        homeGrids.add(new HomeGrid(R.mipmap.wechat,"打开微信","openwechat"));
        homeGrids.add(new HomeGrid(R.mipmap.wechatt,"关闭微信","closewechat"));
        homeGrids.add(new HomeGrid(R.mipmap.task,"任务管理器","taskmgr"));
        homeGrids.add(new HomeGrid(R.mipmap.seleep,"系统睡眠","seleep"));

        homeGridAdapter = new HomeGridAdapter(getActivity(),homeGrids);
        recyclerView.setAdapter(homeGridAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        banner.setDelayTime(4000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();




    }
    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }




}
