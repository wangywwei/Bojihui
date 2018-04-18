package com.hxbj.bijihui.module.landing;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;
import com.hxbj.bijihui.model.bean.LoginBean;

public class LandingContract {

    interface LancingView extends BaseView<LandingPresenter>{
        void setResultData(String token);
        void setUserinfo(LoginBean loginBean);
    }

    interface LandingPresenter extends BasePresenter{
        void start(String iphone,String yanzhengma);
        void userInfo(String iphone);
    }

}
