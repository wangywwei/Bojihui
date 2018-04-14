package com.hxbj.bijihui.module.quanming.paihang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.model.bean.Kecheng;
import com.hxbj.bijihui.video.VideoView;

import java.util.ArrayList;

public class PaihangAdapter extends RecyclerView.Adapter<PaihangAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Kecheng> list;


    public PaihangAdapter(Context context, ArrayList<Kecheng> list) {
        this.context = context;
        this.list = list;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.paiming_item, null);
        RecyclerView.LayoutParams paiming_item = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(paiming_item);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    private int zannum1=10;
    private boolean ISzannum1;
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (list.get(position).isSelect()) {
            holder.paimingvideo.setVisibility(View.VISIBLE);
            holder.bofanyemian2.setVisibility(View.GONE);
        } else {
            holder.bofanyemian2.setVisibility(View.VISIBLE);
            holder.paimingvideo.setVisibility(View.GONE);
        }
        holder.zannum.setText(""+zannum1);
        holder.dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ISzannum1){
                    ISzannum1=false;
                    zannum1=zannum1-1;
                    holder.zan.setImageResource(R.drawable.zan_1);
                    holder.zannum.setTextColor(context.getResources().getColor(R.color.color_C9C9C9));
                    holder.zannum.setText(zannum1+"");
                }else {
                    ISzannum1=true;
                    zannum1=zannum1+1;
                    holder.zan.setImageResource(R.drawable.zan);
                    holder.zannum.setTextColor(context.getResources().getColor(R.color.color_F2B95A));
                    holder.zannum.setText(zannum1+"");
                }

            }
        });

        holder.bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_VideoListener.playVideo("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/videos/outputCut.mp4",
                        "",
                        "",
                        position,
                        holder.paimingvideo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView paiming;
        private RelativeLayout paimingvideo;
        private ImageView paiming_touxiang;
        private TextView paiming_name;
        private TextView paiming_lianji;
        private TextView zannum;
        private ImageView zan;
        private LinearLayout dianzan;
        private ImageView beijing;
        private ImageView bofang;
        private RelativeLayout bofanyemian2;
        public ViewHolder(View itemView) {
            super(itemView);
            paiming=itemView.findViewById(R.id.paiming);
            paimingvideo=itemView.findViewById(R.id.paimingvideo);
            paiming_touxiang=itemView.findViewById(R.id.paiming_touxiang);
            paiming_name=itemView.findViewById(R.id.paiming_name);
            paiming_lianji=itemView.findViewById(R.id.paiming_lianji);
            zannum=itemView.findViewById(R.id.zannum);
            zan=itemView.findViewById(R.id.zan);
            beijing = itemView.findViewById(R.id.beijing);
            bofang = itemView.findViewById(R.id.bofang);
            bofanyemian2 = itemView.findViewById(R.id.bofanyemian2);
            dianzan = itemView.findViewById(R.id.dianzan);
        }
    }
}
