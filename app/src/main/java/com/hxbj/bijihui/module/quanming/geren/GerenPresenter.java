package com.hxbj.bijihui.module.quanming.geren;

import com.hxbj.bijihui.model.BojihuiModelImp;

public class GerenPresenter implements GerenContract.GerenPresenter {

    private GerenContract.GerenView gerenView;
    private BojihuiModelImp bojihuiModelImp;

    public GerenPresenter(GerenContract.GerenView gerenView) {
        this.gerenView = gerenView;
        bojihuiModelImp=new BojihuiModelImp();
        gerenView.setPresenter(this);

    }

    @Override
    public void start() {

    }
}
