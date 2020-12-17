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
import com.sys.socket.SendMsg;
import com.sys.socket.SocketClient;

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
                SendMsg.lockScreen(homeGrid.getCtl());

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
