package com.hxbj.bijihui.module.kechen.kechenxiangqing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.video.VideoView;

import java.util.ArrayList;

public class KeChengXQAdapter extends RecyclerView.Adapter<KeChengXQAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> list;


    public KeChengXQAdapter(Context context, ArrayList<String> list) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoView videoView=new VideoView(context);
        holder.bofanyemian.addView(videoView);

        videoView.setStart("http://heixiong-wlf.oss-cn-beijing.aliyuncs.com/videos/outputCut.mp4"
                ,"","");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout bofanyemian;
        public ViewHolder(View itemView) {
            super(itemView);
            bofanyemian=itemView.findViewById(R.id.bofanyemian);
        }
    }
}
