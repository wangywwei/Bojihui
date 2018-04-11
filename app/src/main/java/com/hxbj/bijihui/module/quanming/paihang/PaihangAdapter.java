package com.hxbj.bijihui.module.quanming.paihang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.video.VideoView;

import java.util.ArrayList;

public class PaihangAdapter extends RecyclerView.Adapter<PaihangAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> list;


    public PaihangAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        VideoView videoView=new VideoView(context);
        holder.paimingvideo.addView(videoView);
        videoView.setStart("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/videos/outputCut.mp4"
                ,"","");

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
        public ViewHolder(View itemView) {
            super(itemView);
            paiming=itemView.findViewById(R.id.paiming);
            paimingvideo=itemView.findViewById(R.id.paimingvideo);
            paiming_touxiang=itemView.findViewById(R.id.paiming_touxiang);
            paiming_name=itemView.findViewById(R.id.paiming_name);
            paiming_lianji=itemView.findViewById(R.id.paiming_lianji);
            zannum=itemView.findViewById(R.id.zannum);
            zan=itemView.findViewById(R.id.zan);
        }
    }
}
