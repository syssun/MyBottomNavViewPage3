package com.sys.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.sys.R;

public class InfoEditActivity extends BaseActivity{
    EditText editText,editTexthide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_edit);
        initNavBar(true,"首页",false);

        editText = fd(R.id.infoedit_input);
        editTexthide = fd(R.id.infoedit_hide);

        Intent intent = getIntent();
        String action = intent.getAction();
        if(action.equals("info")){
            String result = intent.getStringExtra("skey");
            editTexthide.setText(result);
       }
    }


    public void onClick(View view) {
    }
}
