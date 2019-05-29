package com.sys;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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

public class MainActivity extends FragmentActivity {

    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
    FragmentThree fragmentThree;
    FragmentFour fragmentFour;
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
    }
    private void initView() {
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setLabelVisibilityMode(1);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            hideAllFragment(transaction);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(fragmentOne!=null){
                        transaction.show(fragmentOne);
                    }else{
                        transaction.add(R.id.FramePage,fragmentOne);
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if(fragmentTwo!=null){
                        transaction.show(fragmentTwo);
                    }else{
                        transaction.add(R.id.FramePage,fragmentTwo);
                    }
                    return true;
                case R.id.navigation_notifications:
                    if(fragmentThree!=null){
                        transaction.show(fragmentThree);
                    }else{
                        transaction.add(R.id.FramePage,fragmentThree);
                    }
                    return true;
                case R.id.navigation_donut:
                    if(fragmentFour!=null){
                        transaction.show(fragmentFour);
                    }else{
                        transaction.add(R.id.FramePage,fragmentFour);
                    }
                    return true;
            }
            return false;
        }
    };

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(fragmentOne!=null){
            transaction.hide(fragmentOne);
        }
        if(fragmentTwo!=null){
            transaction.hide(fragmentTwo);
        }
        if(fragmentThree!=null){
            transaction.hide(fragmentThree);
        }
        if(fragmentFour!=null){
            transaction.hide(fragmentFour);
        }
    }
}
