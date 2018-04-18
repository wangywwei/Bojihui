package com.hxbj.bijihui.module.kechen;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;

public interface KechenContract {
    //View
    interface KechenView extends BaseView<KechenPresenter> {
        void setResultData(String dataBean);
    }


    //Presenter
    interface KechenPresenter extends BasePresenter {

    }
}
