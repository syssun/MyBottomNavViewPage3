package com.sys.socket;

import android.util.Log;

import com.sys.socket.event.CloseResListener;
import com.sys.socket.event.OnlineListener;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import io.socket.client.IO;
import io.socket.client.Socket;
/**
 * @ProjectName: MyBottomNavViewPage3
 * @Package: com.sys.socket
 * @ClassName: SocketClient
 * @Description: 描述
 * @Author: ©sys
 * @CreateDate: 2020/12/16 10:28
 * @Version: v1.0
 */
public class SocketClient {
    private Socket socket;
    private volatile static SocketClient socketClient;
    public static synchronized SocketClient instence(){
        if(socketClient==null){
            socketClient = new SocketClient();
        }
        return  socketClient;
    }

    public  Socket getSocket(){
        if(socket==null || !socket.connected()){
            initSocket();
        }
        return socket;
    }



    private SocketClient(){}
    Timer timer = new Timer();
    public void start(){
        timer.schedule(task,0,5000);
    }
    public void stop(){
        if(timer !=null){
            timer.cancel();
            if(socket !=null){
                socket.disconnect();
                socket.close();
            }
        }
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            try {
                if(socket ==null || !socket.connected()) {
                    Log.d("AAA","客户端正在重新连接...");
                    initSocket();
                }else{
                    Log.d("AAA","已连接...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private static String userID = "ANDROID-001";

    private void initSocket() {
        try {
            if(socket!=null && socket.connected()){
                return;
            }
            socket = IO.socket("http://106.12.54.207:9092");
            //socket = IO.socket("http://192.168.3.24:9092");
            socket.connect();
            socket.on(Socket.EVENT_CONNECT, args -> socket.emit("login",userID));
            socket.on(Socket.EVENT_CONNECT_ERROR, args -> {
                for (Object o : args) {
                    Log.e("AAA", "活动连接错误2" + o.toString());
                }
            });
            socket.on(Socket.EVENT_CONNECT_TIMEOUT, args -> Log.e("AAA", "活动连接超时3"));
            socket.on(Socket.EVENT_DISCONNECT, args -> Log.e("AAA", "断开连接4"));
            socket.on(Socket.EVENT_CONNECTING, args -> {
                for (Object o : args) {
                    Log.e("AAA", "活动连接5" + o.toString());
                }
            });
            socket.on(Socket.EVENT_ERROR, args -> Log.e("AAA", "事件错误6"));
            socket.on("msg", new CloseResListener());
            socket.on("pc-close-res", new CloseResListener());
            socket.on("online-users", new OnlineListener());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR", "抛出异常：" + e.getMessage());
        }
    }
}
