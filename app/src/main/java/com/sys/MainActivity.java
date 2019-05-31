package com.sys;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.sys.activitys.BaseActivity;
import com.sys.activitys.BaseFragment;
import com.sys.adapter.ViewPagerAdapter;
import com.sys.fragments.FragmentFour;
import com.sys.fragments.FragmentOne;
import com.sys.fragments.FragmentThree;
import com.sys.fragments.FragmentTwo;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity {

    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
    FragmentThree fragmentThree;
    FragmentFour fragmentFour;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }
    private void initData() {
        ArrayList<Integer> images = new ArrayList<Integer>();
        images.add(R.mipmap.banner_one);
        images.add(R.mipmap.banner_two);
        images.add(R.mipmap.banner_three);
        images.add(R.mipmap.banner_four);
        fragmentOne = FragmentOne.newInstance(images);
        fragmentTwo=FragmentTwo.newInstance("two");
        fragmentThree = new FragmentThree();
        fragmentFour = new FragmentFour();
        fragments = new Fragment[]{fragmentOne,fragmentTwo,fragmentThree,fragmentFour};
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
                case R.id.navigation_dashboard:
                    if(lastfragment!=1){
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }

                    return true;
                case R.id.navigation_notifications:
                    if(lastfragment!=2){
                        switchFragment(lastfragment,2);
                        lastfragment=2;
                    }

                    return true;
                case R.id.navigation_donut:
                    if(lastfragment!=3){
                        switchFragment(lastfragment,3);
                        lastfragment=3;
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

}
