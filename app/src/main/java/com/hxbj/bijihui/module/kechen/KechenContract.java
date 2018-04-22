package com.hxbj.bijihui.module.kechen;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;
import com.hxbj.bijihui.model.bean.LoginBean;

public interface KechenContract {
    //View
    interface KechenView extends BaseView<KechenPresenter> {
        void setResultData(LoginBean loginBean);
    }


    //Presenter
    interface KechenPresenter extends BasePresenter {
        void start(String keyWord);
    }
}
