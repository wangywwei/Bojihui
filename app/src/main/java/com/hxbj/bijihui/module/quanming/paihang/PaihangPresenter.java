package com.hxbj.bijihui.module.quanming.paihang;

import com.hxbj.bijihui.model.BojihuiModelImp;

public class PaihangPresenter implements PaihangContract.PaihangPresenter {
    private PaihangContract.PaihangView paihangView;
    private BojihuiModelImp bojihuiModelImp;

    public PaihangPresenter(PaihangContract.PaihangView paihangView) {
        this.paihangView = paihangView;
        bojihuiModelImp=new BojihuiModelImp();
        paihangView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
