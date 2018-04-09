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
     * 加载的Fragment的布局
     *
     * @return
     */


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
