package com.hxbj.bijihui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutId(), null);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    /*
    * 显示隐藏处理
    * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser)
            startLoadData();
        else
            stopLoadData();

        super.setUserVisibleHint(isVisibleToUser);
    }

    protected void stopLoadData() {
    }
    protected void startLoadData(){};

    /**
     * Fragment初始化数据
     */
    protected abstract void initData();

    /**
     * Fragment初始化View控件
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 加载的Fragment的布局
     *
     * @return
     */
    public abstract int getFragmentLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
