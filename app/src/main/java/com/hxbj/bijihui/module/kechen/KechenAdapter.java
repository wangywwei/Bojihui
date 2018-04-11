package com.hxbj.bijihui.module.kechen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxbj.bijihui.R;

import java.util.ArrayList;

public class KechenAdapter extends RecyclerView.Adapter<KechenAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> list;


    public KechenAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kechen_item, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.kecheng_name.setText(list.get(position));
        switch (position){
            case 0:
                holder.suo.setVisibility(View.GONE);
                break;
            case 1:
                holder.suo.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.suo.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.suo.setVisibility(View.VISIBLE);
                break;

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=onItemclickLinter){
                    onItemclickLinter.onItemClicj(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView kecheng_img;
        private TextView kecheng_name;
        private ImageView suo;
        public ViewHolder(View itemView) {
            super(itemView);
            kecheng_img=itemView.findViewById(R.id.kecheng_img);
            kecheng_name=itemView.findViewById(R.id.kecheng_name);
            suo=itemView.findViewById(R.id.suo);
        }
    }

    private OnItemclickLinter onItemclickLinter;

    public void setOnItemclickLinter(OnItemclickLinter onItemclickLinter) {
        this.onItemclickLinter = onItemclickLinter;
    }
    public interface OnItemclickLinter{
        public void onItemClicj(int position);
    }

}
