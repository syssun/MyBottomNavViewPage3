package com.sys;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.sys.activitys.BaseActivity;
import com.sys.activitys.BaseFragment;
import com.sys.adapter.ViewPagerAdapter;
import com.sys.entitys.MessageClient;
import com.sys.entitys.MessageServer;
import com.sys.fragments.FragmentFour;
import com.sys.fragments.FragmentOne;
import com.sys.fragments.FragmentThree;
import com.sys.fragments.FragmentTwo;
import com.sys.utils.TcpClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity {

    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    FragmentOne fragmentOne;
   // FragmentTwo fragmentTwo;
    //FragmentThree fragmentThree;
    FragmentFour fragmentFour;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        //注册EventBus
        //EventBus.getDefault().register(this);
        //链接服务端
        //TcpClient.startClient( "localhost", 9088);
    }


    private void initData() {
        ArrayList<Integer> images = new ArrayList<Integer>();
        images.add(R.mipmap.banner_four);
        fragmentOne = FragmentOne.newInstance(images);
        //fragmentTwo=FragmentTwo.newInstance("通知");
       // fragmentThree = new FragmentThree();
        fragmentFour = new FragmentFour();
        fragments = new Fragment[]{fragmentOne,fragmentFour};
        lastfragment=0;
    }
    private void initView() {
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setLabelVisibilityMode(1);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FramePage,fragmentOne).show(fragmentOne).commit();//初始页面显示
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(lastfragment!=0){
                        switchFragment(lastfragment,0);
                        lastfragment=0;
                    }
                    return true;
//
                case R.id.navigation_donut:
                    if(lastfragment!=1){
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }
                    return true;
            }
            return false;
        }
    };
    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false) {
            transaction.add(R.id.FramePage,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }


    //收到服务器的消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageServer(MessageServer messageEvent) {
        Log.d("收到服务端:" ,messageEvent.getMsg());
        Toast.makeText(this,messageEvent.getMsg(),Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    //收到客户端的消息
    public void MessageClient(MessageClient messageEvent) {
        Log.d("客户端收到:" ,messageEvent.getMsg());
        Toast.makeText(this,messageEvent.getMsg(),Toast.LENGTH_SHORT).show();
    }

}
