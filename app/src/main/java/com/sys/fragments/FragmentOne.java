package com.sys.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.sys.MyApplication;
import com.sys.R;
import com.sys.activitys.BaseFragment;
import com.sys.adapter.HomeGridAdapter;
import com.sys.entitys.HomeGrid;
import com.sys.loader.GlideImageLoader;
import com.sys.views.LoadingFrame;
import com.youth.banner.Banner;

import java.util.ArrayList;

import io.socket.emitter.Emitter;

public class FragmentOne extends BaseFragment {
    ArrayList<Integer> images;
    private static final String BUNKEY = "images";
    RecyclerView recyclerView;
    View view;
    Banner banner;
    HomeGridAdapter homeGridAdapter;
    static  ImageView imageView;
    static Context context;

    public static FragmentOne newInstance(ArrayList<Integer> images) {
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(BUNKEY, images);
        FragmentOne fragmentOne = new FragmentOne();
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        images = args.getIntegerArrayList(BUNKEY);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        initNavBar(view, false, "PC控制面板", false);
        banner = view.findViewById(R.id.banner);
        imageView = view.findViewById(R.id.pc_image);

        recyclerView = view.findViewById(R.id.rv_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        ArrayList<HomeGrid> homeGrids = new ArrayList<>();
        homeGrids.add(new HomeGrid(R.mipmap.suoping, "锁屏", "lock_screen"));
        homeGrids.add(new HomeGrid(R.mipmap.shutdow, "立即关机", "shutdown_now"));
        homeGrids.add(new HomeGrid(R.mipmap.shutdownfo, "60S关机", "shutdown60"));
        homeGrids.add(new HomeGrid(R.mipmap.canceo, "取消关机", "cancel_shutdown"));
        homeGrids.add(new HomeGrid(R.mipmap.seleep, "电脑休眠", "sleep"));
        homeGrids.add(new HomeGrid(R.mipmap.take_photo, "PC拍照", "take_photo"));
        homeGridAdapter = new HomeGridAdapter(getActivity(), homeGrids);
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

    /*
     * Function   :   实现handleMessage()方法，用于接收Message，刷新UI
     */
    public static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            if(msg.what==0){
                toast(str);
            }else {
                refreshImage(str);
            }
        }
    };

    public static void refreshImage(String strBase64) {
        byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
    }
    public static void toast(String str){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }
}
