package com.sys.socket;

import android.util.Log;

/**
 * @ProjectName: MyBottomNavViewPage3
 * @Package: com.sys.socket
 * @ClassName: SendMsg
 * @Description: 描述
 * @Author: ©sys
 * @CreateDate: 2020/12/16 10:45
 * @Version: v1.0
 */
public class SendMsg {
    private static SocketClient socketClient = SocketClient.instence();
    //发送关机
    public static void lockScreen(String ctl){
        socketClient.getSocket().emit("pc-close",ctl);
        Log.d("AAA","发送pc-close指令");
    }

}
