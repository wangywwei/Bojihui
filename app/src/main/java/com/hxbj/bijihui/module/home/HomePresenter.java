package com.hxbj.bijihui.module.home;


import com.hxbj.bijihui.model.BojihuiModelImp;
import com.hxbj.bijihui.model.bean.HomeBannerBean;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.network.MyCallBack;

/**
 * <p>
 * Preenter层  主要负责处理程序中的业务逻辑，和 通知View层(通过接口的方式)更新UI
 */
public class HomePresenter implements HomeContract.HomePresenter {
    private HomeContract.HomeView homeView;
    private BojihuiModelImp modelImp;

    public HomePresenter(HomeContract.HomeView homeView) {
        this.homeView = homeView;
        modelImp = new BojihuiModelImp();
        //实例化
        homeView.setPresenter(this);
    }


    @Override
    public void start() {
        //处理相关业务逻辑
        modelImp.getBanner(new MyCallBack<HomeBannerBean>() {
            @Override
            public void onSuccess(HomeBannerBean homeDataBean) {
                //P层是桥梁 将Model层获取的数据交给View层更新UI
                homeView.setResultData(homeDataBean);
            }

            @Override
            public void onFaile(String msg) {

            }
        });

    }

    @Override
    public void userInfo(String iphone) {
        modelImp.getLogin(new MyCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                homeView.setUserinfo(loginBean);
            }

            @Override
            public void onFaile(String msg) {

            }
        }, iphone);
    }
}
