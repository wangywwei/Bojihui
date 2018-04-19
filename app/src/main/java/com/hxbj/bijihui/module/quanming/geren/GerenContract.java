package com.hxbj.bijihui.module.quanming.geren;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;
import com.hxbj.bijihui.model.bean.GuanVideoBean;

public class GerenContract {

    interface GerenView extends BaseView<GerenPresenter>{

        void setResultData(GuanVideoBean guanVideoBean);
    }

    interface GerenPresenter extends BasePresenter{

    }
}
