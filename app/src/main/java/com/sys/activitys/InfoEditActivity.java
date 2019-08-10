package com.sys.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        editText = fd(R.id.infoedit_input);
        editTexthide = fd(R.id.infoedit_hide);

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();


        Intent intent = getIntent();
        String action = intent.getAction();
        if(action.equals("info")){
            String result = intent.getStringExtra("skey");
            String svalue = intent.getStringExtra("svalue");
            editTexthide.setText(result);
            editText.setText(svalue);
            initNavBar(true,result,false);
       }
    }
    public void onClick(View view) {
        String key =editTexthide.getText().toString();
        String value = editText.getText().toString();
        SharedPreferences sp = this.getSharedPreferences("FILE_NAME", 0);//获取实列
        SharedPreferences.Editor ed = sp.edit();//获取编辑对象
        ed.putString(key,value);//存值
        ed.commit(); //提交
        finish();
    }
}
