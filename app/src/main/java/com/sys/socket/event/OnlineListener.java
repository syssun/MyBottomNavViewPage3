package com.sys.socket.event;

import android.content.SharedPreferences;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.sys.MainActivity;
import com.sys.MyApplication;
import com.sys.R;
import com.sys.fragments.FragmentFour;
import com.sys.fragments.FragmentOne;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.socket.emitter.Emitter;

/**
 * @ProjectName: MyBottomNavViewPage3
 * @Package: com.sys.socket.event
 * @ClassName: OnlineListener
 * @Description: 描述-在线用户数
 * @Author: ©sys
 * @CreateDate: 2020/12/16 11:29
 * @Version: v1.0
 */
public class OnlineListener implements Emitter.Listener {

    @Override
    public void call(Object... o) {
        try {
            if (o != null) {
                Log.d("AAA", JSON.toJSONString(o));
                String str = JSON.toJSONString(o);
                FragmentFour s = MainActivity.fragmentFour;
                if (s != null) {
                    Message msg = s.mHandler.obtainMessage();
                    msg.what = 1;
                    msg.obj = str;
                    msg.sendToTarget();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
