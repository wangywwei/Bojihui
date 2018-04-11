package com.hxbj.bijihui.module.quanming.paihang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseFragment;
import com.hxbj.bijihui.module.kechen.KechenAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;


public class PaihangFragment extends BaseFragment implements PaihangContract.PaihangView {

    private PaihangContract.PaihangPresenter paihangPresenter;

    private View view;
    private XRecyclerView paihangrecyclerview;
    private PaihangAdapter kechenAdapter;
    private ArrayList<String> list = new ArrayList<>();

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
        list.add("体验课程");
        list.add("Lv.1  初级课程");
        list.add("Lv.2  中级课程");
        list.add("Lv.2  高级课程");
        kechenAdapter = new PaihangAdapter(getActivity(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        paihangrecyclerview.setLayoutManager(layoutManager);
        paihangrecyclerview.setNestedScrollingEnabled(false);
        paihangrecyclerview.setAdapter(kechenAdapter);


    }

    @Override
    public void setResultData(String resultData) {

    }

    @Override
    public void setPresenter(PaihangContract.PaihangPresenter paihangPresenter) {
        this.paihangPresenter = paihangPresenter;
    }
}
