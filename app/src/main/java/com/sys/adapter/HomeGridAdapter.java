package com.sys.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sys.R;
import com.sys.entitys.HomeGrid;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.ViewHoler> {
    Context context;
    ImageView imageView;
    TextView textView;
    ArrayList<HomeGrid> homeGrids;
    public HomeGridAdapter (Context context,ArrayList<HomeGrid> homeGrids){
        this.context = context;
        this.homeGrids = homeGrids;
    }
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeGridAdapter.ViewHoler(LayoutInflater.from(context).inflate(R.layout.home_grid,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        final HomeGrid  homeGrid= homeGrids.get(i);
        imageView.setImageResource(homeGrid.getIamge());
        textView.setText(homeGrid.getTitle());
        viewHoler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //autoct
                String apendstr="";
                SharedPreferences sharedPreferences = context.getSharedPreferences("FILE_NAME", 0);
                String ip = sharedPreferences.getString("IP","");
                String qq = sharedPreferences.getString("QQ","");
                String port = sharedPreferences.getString("PORT","");
                String WeChat = sharedPreferences.getString("WeChat","");
                if(port==null || "".equals(port)){
                    Toast.makeText(context,"请先维护API-port",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(ip==null || "".equals(ip)){
                    Toast.makeText(context,"请先维护API-IP",Toast.LENGTH_SHORT).show();
                    return ;
                }
                //openqq  openwechat
                homeGrid.setIp(ip);
                homeGrid.setPort(port);
                if("autoctl".equals(homeGrid.getCtl())){
                    apendstr = sharedPreferences.getString("autoctl","control");
                }
                if("openqq".equals(homeGrid.getCtl())){
                    apendstr = qq;
                    if(apendstr==null || "".equals(apendstr)){
                        Toast.makeText(context,"请先维护QQ安装路径",Toast.LENGTH_SHORT).show();
                        return ;
                    }
                }
                if("openwechat".equals(homeGrid.getCtl())){
                    apendstr = WeChat;
                    if(apendstr==null || "".equals(apendstr)){
                        Toast.makeText(context,"请先维护Wechat安装路径",Toast.LENGTH_SHORT).show();
                        return ;
                    }
                }
                if(apendstr !="") {
                    try {
                        apendstr = URLEncoder.encode(apendstr,"UTF-8");

                    } catch (UnsupportedEncodingException e) {

                    }
                    doGet(homeGrid.getBaseUrl() + "autoctl?ctl=" + apendstr);
                }
                else {
                    doGet(homeGrid.getBaseUrl() + homeGrid.getCtl());
                }
                Log.d("sysinfo",apendstr);
            }
        });
    }
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(context,msg.obj.toString(),Toast.LENGTH_SHORT).show();
            if(msg.what==0){
                Toast.makeText(context,msg.obj.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void doGet(String url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                     .connectTimeout(10, TimeUnit.SECONDS)
                     .readTimeout(20, TimeUnit.SECONDS)
                     .build();
        final Request request = new Request.Builder()
                .url(url)

                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = 0;
                msg.obj="请求失败";
                handler.sendMessage(msg);
                Log.d("dogetonfailus", "onFailure: ");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.d("aaa",str);
                Message msg = new Message();
                msg.what = 0;
                if("1".equals(str)){
                    //处理完成后给handler发送消息
                    msg.obj="操作成功";
                }else{
                    msg.obj="操作失败";
                }
                handler.sendMessage(msg); Log.d("success", "success: ");

            }
        });
    }


    @Override
    public int getItemCount() {
        return homeGrids.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder{

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.grid_img);
            textView = itemView.findViewById(R.id.grid_text);
        }
    }
}
