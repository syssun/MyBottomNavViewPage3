package com.sys.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sys.R;
import com.sys.entitys.HomeGrid;

import java.util.ArrayList;

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
                Toast.makeText(context,homeGrid.getTitle(),Toast.LENGTH_SHORT).show();
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
