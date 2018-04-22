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
import com.hxbj.bijihui.constants.Urls;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.DianzanBean;
import com.hxbj.bijihui.model.bean.GuanVideoBean;
import com.hxbj.bijihui.model.bean.Kecheng;
import com.hxbj.bijihui.network.HttpFactory;
import com.hxbj.bijihui.network.MyCallBack;
import com.hxbj.bijihui.utils.GlidUtils;
import com.hxbj.bijihui.video.VideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaihangAdapter extends RecyclerView.Adapter<PaihangAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GuanVideoBean.DataBean> list;


    public PaihangAdapter(Context context, ArrayList<GuanVideoBean.DataBean> list) {
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

        if (list.get(position).getThumbType() == 0) {
            holder.zan.setImageResource(R.drawable.zan_1);
            holder.zannum.setTextColor(context.getResources().getColor(R.color.color_C9C9C9));
            ISzannum1 = false;
        } else {
            holder.zan.setImageResource(R.drawable.zan);
            holder.zannum.setTextColor(context.getResources().getColor(R.color.color_F2B95A));
            ISzannum1 = true;
        }

        holder.paiming_name.setText(list.get(position).getNickname());
        if (list.get(position).getSortType().equals("thump")) {
            holder.paiming.setText("当前排名No." + (position + 1));
        } else {
            holder.paiming.setText(list.get(position).getCreateTime());
        }


        GlidUtils.setGrid2(context, list.get(position).getPicUrl(), holder.paiming_touxiang);
        holder.paiming_lianji.setText(list.get(position).getLevel());


        holder.zannum.setText("" + list.get(position).getThumb());
        holder.dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ISzannum1) {
                    ISzannum1 = false;
                    dinazan(list.get(position).getId(), "0", holder.zan, holder.zannum);
                } else {
                    ISzannum1 = true;
                    dinazan(list.get(position).getId(), "1", holder.zan, holder.zannum);
                }

            }
        });

        holder.bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_VideoListener.playVideo(list.get(position).getVideoUrl(),
                        list.get(position).getCoverUrl(),
                        list.get(position).getTitle(),
                        position,
                        holder.paimingvideo);
            }
        });

    }

    private void dinazan(String id, String thumbType, final ImageView zan, final TextView zannum) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", MyApp.instance.getId());
        map.put("id", id);
        map.put("thumbType", thumbType);
        HttpFactory.create().post(Urls.UPDATETHUMB, map, new MyCallBack<DianzanBean>() {
            @Override
            public void onSuccess(DianzanBean dianzanBean) {
                if (dianzanBean.getData().getThumbType()==0) {
                    zan.setImageResource(R.drawable.zan_1);
                    zannum.setTextColor(context.getResources().getColor(R.color.color_C9C9C9));
                    zannum.setText(dianzanBean.getData().getThumb() + "");
                } else {
                    zan.setImageResource(R.drawable.zan);
                    zannum.setTextColor(context.getResources().getColor(R.color.color_F2B95A));
                    zannum.setText(dianzanBean.getData().getThumb() + "");
                }
            }

            @Override
            public void onFaile(String msg) {

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
            paiming = itemView.findViewById(R.id.paiming);
            paimingvideo = itemView.findViewById(R.id.paimingvideo);
            paiming_touxiang = itemView.findViewById(R.id.paiming_touxiang);
            paiming_name = itemView.findViewById(R.id.paiming_name);
            paiming_lianji = itemView.findViewById(R.id.paiming_lianji);
            zannum = itemView.findViewById(R.id.zannum);
            zan = itemView.findViewById(R.id.zan);
            beijing = itemView.findViewById(R.id.beijing);
            bofang = itemView.findViewById(R.id.bofang);
            bofanyemian2 = itemView.findViewById(R.id.bofanyemian2);
            dianzan = itemView.findViewById(R.id.dianzan);
        }
    }
}
