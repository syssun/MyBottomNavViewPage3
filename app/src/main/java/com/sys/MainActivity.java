package com.sys;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
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
    private ViewPager viewPager;

    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLisenter();
    }



    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setLabelVisibilityMode(1);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setupViewPager(viewPager);

    }
    private void initLisenter() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(i);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        ArrayList<Integer> images = new ArrayList<Integer>();
        images.add(R.mipmap.banner_one);
        images.add(R.mipmap.banner_two);
        images.add(R.mipmap.banner_three);
        images.add(R.mipmap.banner_four);

        adapter.addFragment(FragmentOne.newInstance(images));
        adapter.addFragment(FragmentTwo.newInstance("TWO ACTIVITY"));
        adapter.addFragment(new FragmentThree());
        adapter.addFragment(new FragmentFour());
        viewPager.setAdapter(adapter);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_donut:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

}
