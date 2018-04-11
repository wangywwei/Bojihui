package com.hxbj.bijihui.module.geren;

import com.hxbj.bijihui.model.BojihuiModelImp;

public class LianxiPresenter implements LianxiContract.LianxiPresenter {
    private LianxiContract.LianxiView lianxiView;
    private BojihuiModelImp bojihuiModelImp;

    public LianxiPresenter(LianxiContract.LianxiView lianxiView) {
        this.lianxiView = lianxiView;
        bojihuiModelImp=new BojihuiModelImp();
        lianxiView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
