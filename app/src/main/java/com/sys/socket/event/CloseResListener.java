package com.sys.socket.event;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.sys.MyApplication;
import com.sys.fragments.FragmentOne;

import io.socket.emitter.Emitter;

/**
 * @ProjectName: MyBottomNavViewPage3
 * @Package: com.sys.socket.event
 * @ClassName: CloseResListener
 * @Description: 描述
 * @Author: ©sys
 * @CreateDate: 2020/12/16 10:30
 * @Version: v1.0
 */
public class CloseResListener implements Emitter.Listener {
    @Override
    public void call(Object... o) {
        if (o != null) {
            String str = String.valueOf(o[0]);
            Log.d("AAA", str);

            if (!"0".equals(str) && !"1".equals(str)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        android.os.Message msg = FragmentOne.mHandler.obtainMessage();
                        msg.what = 1;
                        msg.obj=str ;
                        msg.sendToTarget();
                    }
                }).start();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        android.os.Message msg = FragmentOne.mHandler.obtainMessage();
                        msg.what = 0;
                        msg.obj=str ;
                        msg.sendToTarget();
                    }
                }).start();
            }

        }
    }
}
