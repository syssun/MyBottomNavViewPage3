package com.sys;
import android.app.Application;
import android.content.Context;

import com.sys.socket.SocketClient;
/**
 * 初始化socket
 * 启动一个线程去连接
 */
public class MyApplication  extends Application {
    private static Context context;
    SocketClient socketClient;
    @Override
    public void onCreate() {
        super.onCreate();
        socketClient = SocketClient.instence();
        socketClient.start();
        if(context == null ){
            context = getApplicationContext();
        }
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        if(socketClient !=null) {
            socketClient.stop();
        }
    }
    public static Context getContext(){
        return context;
    }
}
