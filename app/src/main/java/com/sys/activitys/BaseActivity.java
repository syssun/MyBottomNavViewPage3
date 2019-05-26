package com.sys.activitys;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sys.R;

public class BaseActivity extends Activity {
    ImageView navback,navuser;
    TextView titlev;
    protected void initNavBar(boolean ishowback,String title,boolean isuser){
        navback = fd(R.id.nav_back);
        navuser = fd(R.id.nav_user);
        titlev = fd(R.id.nav_title);
        navback.setVisibility(ishowback?View.VISIBLE:View.GONE);
        navuser.setVisibility(isuser?View.VISIBLE:View.GONE);
        titlev.setText(title);
    }
    protected <T extends View> T fd(@IdRes int id){
        return findViewById(id);
    }

}
