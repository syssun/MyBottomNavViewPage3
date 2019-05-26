package com.sys.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sys.MainActivity;
import com.sys.R ;
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initNavBar(false,"登录",false);
    }



    public void onClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }


}
