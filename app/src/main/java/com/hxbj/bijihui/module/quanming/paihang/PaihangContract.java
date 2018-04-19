package com.hxbj.bijihui.module.quanming.paihang;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;
import com.hxbj.bijihui.model.bean.GuanVideoBean;

public class PaihangContract {

    interface PaihangView extends BaseView<PaihangPresenter>{
        void setResultData(GuanVideoBean guanVideoBean);
    }


    interface PaihangPresenter extends BasePresenter{
        void start(String pageCurrent, String pageSize,String sortType);
    }
}
