package com.hxbj.bijihui.module.quanming.paihang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseFragment;
import com.hxbj.bijihui.model.bean.Kecheng;
import com.hxbj.bijihui.module.kechen.KechenAdapter;
import com.hxbj.bijihui.video.VideoQuanpingActivity;
import com.hxbj.bijihui.video.VideoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;


public class PaihangFragment extends BaseFragment implements PaihangContract.PaihangView,PaihangAdapter.VideoListener, VideoView.OnItemclickLinter  {

    private PaihangContract.PaihangPresenter paihangPresenter;

    private View view;
    private XRecyclerView paihangrecyclerview;
    private PaihangAdapter paihangAdapter;
    private ArrayList<Kecheng> list = new ArrayList<>();
    private VideoView videoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_paihang, null);
            initView(view);
            initData();
        } else {
            // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }

        return view;
    }

    private void initData() {
        paihangPresenter = new PaihangPresenter(this);
        paihangPresenter.start();
    }

    private void initView(View view) {
        paihangrecyclerview = (XRecyclerView) view.findViewById(R.id.paihangrecyclerview);
        for (int i = 0; i <10 ; i++) {
            list.add(new Kecheng());
        }
        paihangAdapter = new PaihangAdapter(getActivity(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        paihangrecyclerview.setLayoutManager(layoutManager);
        paihangrecyclerview.setNestedScrollingEnabled(false);
        paihangrecyclerview.setAdapter(paihangAdapter);
        paihangAdapter.setVideoListener(this);
    }

    @Override
    public void setResultData(String resultData) {

    }

    @Override
    public void setPresenter(PaihangContract.PaihangPresenter paihangPresenter) {
        this.paihangPresenter = paihangPresenter;
    }

    private String url;
    private String imgurl;
    private String title;
    private int jindu;
    @Override
    public void playVideo(String url, String imgurl, String title, int position, RelativeLayout bofanyemian) {
        this.url = url;
        this.imgurl = imgurl;
        this.title = title;
        if (position == -1) {
            return;
        }
        if (videoView!=null){
            videoView.onDestroy();
            videoView=null;
        }
        videoView = new VideoView(getActivity());
        videoView.setOnItemclickLinter(this);
        videoView.onDestroy();
        bofanyemian.addView(videoView);
        //初始化所有数据
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelect(false);
        }
        list.get(position).setSelect(true);
        paihangAdapter.notifyDataSetChanged();

        videoView.setStart(url,imgurl,title);
        this.jindu = videoView.getProgress();
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void onDestroy() {
        if (videoView!=null){
            videoView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onItemClicj(View view) {
        startActivityForResult(VideoQuanpingActivity.getIntent(getActivity(),
                url,imgurl,title,jindu),100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==getActivity().RESULT_OK){
            Bundle bundle = data.getExtras();
            int jindu =bundle.getInt("jindu");
            videoView.setJindu(jindu);
        }
    }
}
