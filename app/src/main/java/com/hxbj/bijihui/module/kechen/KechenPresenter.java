package com.hxbj.bijihui.module.kechen;

import com.hxbj.bijihui.global.MyApp;
import com.hxbj.bijihui.model.BojihuiModelImp;
import com.hxbj.bijihui.model.bean.LoginBean;
import com.hxbj.bijihui.network.MyCallBack;
import com.hxbj.bijihui.utils.ToastUtils;

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

    @Override
    public void start(String keyWord) {
        modelImp.userAuthInfo(new MyCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                if (loginBean.getCode()==2000){
                    kechenView.setResultData(loginBean);
                    ToastUtils.showToast(MyApp.mContext,"激活成功！");
                }else{
                    ToastUtils.showToast(MyApp.mContext,loginBean.getMsg());
                }

            }

            @Override
            public void onFaile(String msg) {

            }
        },keyWord);
    }
}
