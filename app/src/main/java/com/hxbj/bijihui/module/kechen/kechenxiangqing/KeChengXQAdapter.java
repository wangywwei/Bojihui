package com.hxbj.bijihui.module.kechen.kechenxiangqing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.model.bean.GuanVideoBean;
import com.hxbj.bijihui.model.bean.Kecheng;

import java.util.ArrayList;

public class KeChengXQAdapter extends RecyclerView.Adapter<KeChengXQAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GuanVideoBean.DataBean> list;


    public KeChengXQAdapter(Context context, ArrayList<GuanVideoBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kechengxq_item, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        if (list.get(position).isSelect()) {
            holder.bofanyemian.setVisibility(View.VISIBLE);
            holder.bofanyemian2.setVisibility(View.GONE);
        } else {
            holder.bofanyemian2.setVisibility(View.VISIBLE);
            holder.bofanyemian.setVisibility(View.GONE);
        }

        holder.bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                m_VideoListener.playVideo(list.get(position).getVideoUrl(),
                        "",
                        list.get(position).getTitle(),
                        position,
                        holder.bofanyemian);
            }
        });

    }

    private VideoListener m_VideoListener;

    public void setVideoListener(VideoListener m_VideoListener) {
        this.m_VideoListener = m_VideoListener;
    }

    public interface VideoListener {
        //当前页面播放
        void playVideo(String url, String imgurl, String title, int position, RelativeLayout bofanyemian);

        //是否正在播放
        boolean isPlaying();

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout bofanyemian;
        private ImageView beijing;
        private ImageView bofang;
        private RelativeLayout bofanyemian2;

        public ViewHolder(View itemView) {
            super(itemView);
            bofanyemian = itemView.findViewById(R.id.bofanyemian);
            beijing = itemView.findViewById(R.id.beijing);
            bofang = itemView.findViewById(R.id.bofang);
            bofanyemian2 = itemView.findViewById(R.id.bofanyemian2);
        }
    }
}
