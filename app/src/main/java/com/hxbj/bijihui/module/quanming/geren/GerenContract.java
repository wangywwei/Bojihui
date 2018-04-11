package com.hxbj.bijihui.module.quanming.geren;

import com.hxbj.bijihui.base.BasePresenter;
import com.hxbj.bijihui.base.BaseView;

public class GerenContract {

    interface GerenView extends BaseView<GerenPresenter>{

        void setResultData(String resultData);
    }

    interface GerenPresenter extends BasePresenter{

    }
}
