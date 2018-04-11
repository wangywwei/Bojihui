package com.hxbj.bijihui.module.kechen.kechenxiangqing;

import com.hxbj.bijihui.model.BojihuiModelImp;

public class KechengXQPresenter implements KechengXQContract.KechengXQPresenter {
    private KechengXQContract.KechengXQView kechengXQView;
    private BojihuiModelImp modelImp;

    public KechengXQPresenter(KechengXQContract.KechengXQView kechengXQView) {
        this.kechengXQView = kechengXQView;
        modelImp=new BojihuiModelImp();
        kechengXQView.setPresenter(this);

    }

    @Override
    public void start() {

    }
}
