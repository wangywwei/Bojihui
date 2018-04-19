package com.hxbj.bijihui.module.kechen.kechenxiangqing;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;
import com.hxbj.bijihui.model.bean.GuanVideoBean;

public class KechengXQContract {

    interface KechengXQView extends BaseView<KechengXQPresenter>{
        void setResultData(GuanVideoBean guanVideoBean);

    }

    interface KechengXQPresenter extends BasePresenter{
        void start(String grade,String actionType);
    }
}
