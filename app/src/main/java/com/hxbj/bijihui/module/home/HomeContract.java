package com.hxbj.bijihui.module.home;


import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;
import com.hxbj.bijihui.model.bean.HomeBannerBean;
import com.hxbj.bijihui.model.bean.LoginBean;

/**
 * 契约类  将 M层 V层, P层 对应的接口都放里面
 */
public interface HomeContract {

    //View
    interface HomeView extends BaseView<HomePresenter> {
        void setResultData(HomeBannerBean dataBean);
        void setUserinfo(LoginBean loginBean);
    }


    //Presenter
    interface HomePresenter extends BasePresenter {
        void userInfo(String iphone);

    }


}
