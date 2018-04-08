package com.hxbj.bijihui.module.home;

import android.view.View;
import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseFragment;
import com.hxbj.bijihui.model.bean.HomeDataBean;

public class HomeFragment extends BaseFragment implements HomeContract.HomeView {
    //TODO mHomePresenter未初始化
    private HomeContract.HomePresenter mHomePresenter;

    @Override
    public void setPresenter(HomeContract.HomePresenter presenter) {
        mHomePresenter = presenter;
    }


    @Override
    public void setResultData(HomeDataBean bean) {
        //更新UI

    }

    @Override
    protected void initData() {
        //通过P层处理相关业务逻辑
        mHomePresenter=new HomePresenter(this);
        mHomePresenter.start();
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.home_fragment;
    }
}
