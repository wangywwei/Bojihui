package com.hxbj.bijihui.module.kechen;

import com.hxbj.bijihui.model.BojihuiModelImp;

public class KechenPresenter implements KechenContract.KechenPresenter{
    private KechenContract.KechenView kechenView;
    private BojihuiModelImp modelImp;


    public KechenPresenter(KechenContract.KechenView kechenView) {
        this.kechenView = kechenView;
        modelImp=new BojihuiModelImp();
        kechenView.setPresenter(this);
    }

    @Override
    public void start() {


    }
}
