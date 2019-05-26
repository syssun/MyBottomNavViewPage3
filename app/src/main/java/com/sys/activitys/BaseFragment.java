package com.sys.activitys;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sys.R;

public class BaseFragment extends Fragment {
    ImageView navback,navuser;
    TextView titlev;
    protected void initNavBar(View view,boolean ishowback,String title,boolean isuser){
        navback = fd(view,R.id.nav_back);
        navuser = fd(view,R.id.nav_user);
        titlev = fd(view,R.id.nav_title);
        navback.setVisibility(ishowback? View.VISIBLE:View.GONE);
        navuser.setVisibility(isuser?View.VISIBLE:View.GONE);
        titlev.setText(title);
    }
    protected <T extends View> T fd(View view,@IdRes int id){
        return view.findViewById(id);
    }

}
