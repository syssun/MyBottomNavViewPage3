package com.sys.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sys.R;
import com.sys.activitys.InfoEditActivity;
import com.sys.entitys.InfoGrid;
import java.util.ArrayList;

public class InfoGridAdapter extends RecyclerView.Adapter<InfoGridAdapter.ViewHoler> {
    Context context;
    TextView tvlable,tvvalue;
    ImageView imageView;
    ArrayList<InfoGrid> infoGrids;
    public InfoGridAdapter(Context context,ArrayList<InfoGrid> infoGrids){
        this.context = context;
        this.infoGrids = infoGrids ;
    }
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new InfoGridAdapter.ViewHoler(LayoutInflater.from(context).inflate(R.layout.info_grid,viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
            final InfoGrid infoGrid = infoGrids.get(i);
            tvlable.setText(infoGrid.getLabel());
            tvvalue.setText(infoGrid.getValue());
            imageView.setVisibility(infoGrid.getFlag()?View.VISIBLE:View.GONE);

            viewHoler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if("IP".equals(infoGrid.getCode())
                            || "PORT".equals(infoGrid.getCode())
                            || "QQ".equals(infoGrid.getCode())
                            || "WeChat".equals(infoGrid.getCode())
                            || "autoctl".equals(infoGrid.getCode()) ){
                        Intent intent = new Intent(context, InfoEditActivity.class);
                        intent.setAction("info");
                        intent.putExtra("skey",infoGrid.getCode());
                        intent.putExtra("svalue",infoGrid.getValue());
                        context.startActivity(intent);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return infoGrids.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder{

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvlable = itemView.findViewById(R.id.tv_label);
            tvvalue = itemView.findViewById(R.id.tv_value);
            imageView = itemView.findViewById(R.id.info_more);
        }
    }
}
