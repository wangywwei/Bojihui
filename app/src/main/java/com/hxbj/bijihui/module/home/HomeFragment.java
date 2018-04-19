package com.hxbj.bijihui.module.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseFragment;
import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.bean.HomeBannerBean;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.utils.LogUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class HomeFragment extends BaseFragment implements HomeContract.HomeView ,HomePack.OnItemclickLinter{
    //TODO mHomePresenter未初始化
    private HomeContract.HomePresenter mHomePresenter;
    private View view;
    private XRecyclerView xrecyclerview;
    private HomedAdapter adapter;
    private HomeBanner homeBanner;
    private HomeMy homeMy;
    private HomePack homePack;

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.home_fragment, null);
            initView(view);
        } else {
            // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

    @Override
    public void setPresenter(HomeContract.HomePresenter presenter) {
        mHomePresenter = presenter;
    }


    @Override
    public void setResultData(HomeBannerBean homeBannerBean) {
        //更新UI
        if (homeBannerBean.getCode()==2000){
            homeBanner.setHomeBannerBean(homeBannerBean);
        }
    }

    @Override
    public void setUserinfo(LoginBean loginBean) {
        if (loginBean.getCode()==2000){
            MyApp.instance.saveLogin(loginBean.getData(),getActivity());
            homeMy.setHomeMyBean(loginBean);
            homePack.setHomePackBean(loginBean);
            upData.upData(loginBean);
        }

    }


    public void initData() {
        //通过P层处理相关业务逻辑
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.start();
        mHomePresenter.userInfo(MyApp.instance.getIphone());
    }


    protected void initView(View view) {
        xrecyclerview = view.findViewById(R.id.xrecyclerview);
        adapter = new HomedAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecyclerview.setLayoutManager(layoutManager);
        xrecyclerview.setNestedScrollingEnabled(false);
        xrecyclerview.setAdapter(adapter);
        //首页轮播图
        homeBanner = new HomeBanner(getActivity());
        xrecyclerview.addHeaderView(homeBanner);

        //首页我的模块
        homeMy = new HomeMy(getActivity());
        xrecyclerview.addHeaderView(homeMy);

        //首页呐喊与打卡
        homePack = new HomePack(getActivity());
        homePack.setOnItemclickLinter(this);
        xrecyclerview.addHeaderView(homePack);




        xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                LogUtils.e("Authorization",MyApp.instance.getAuthorization()+"");
                mHomePresenter.start();
                mHomePresenter.userInfo(MyApp.instance.getIphone());
                xrecyclerview.refreshComplete();//刷新完成
            }

            @Override
            public void onLoadMore() {
                xrecyclerview.refreshComplete();//加载完成
            }
        });

    }


    @Override
    public void onDestroy() {
        if (homeBanner!=null){
            homeBanner.onDestroy();
        }
        if (homePack!=null){
            homePack.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onItemClicj() {
        initData();
    }


    public interface UpData{
        public void upData(LoginBean loginBean);
    }

    private UpData upData;

    public void setUpData(UpData upData) {
        this.upData = upData;
    }

}
