package com.hxbj.bijihui.module.quanming.geren;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseFragment;


public class GerenFragment extends BaseFragment implements GerenContract.GerenView{

    private GerenContract.GerenPresenter gerenPresenter;


    private View view;
    private RelativeLayout gerenvideo;
    private ImageView kongzhuangtai;
    private ImageView shangchuanwode;
    private LinearLayout meiyouwode;
    private RelativeLayout gerenmyvideo;
    private ImageView zan;
    private TextView zannum;
    private ImageView fenxiang;
    private TextView chonglu;
    private LinearLayout youwode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_geren, null);
            initView(view);
            initData();
        } else {
            // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }

        return view;
    }

    private void initData() {
        gerenPresenter=new GerenPresenter(this);
        gerenPresenter.start();
    }

    private void initView(View view) {
        gerenvideo = (RelativeLayout) view.findViewById(R.id.gerenvideo);
        kongzhuangtai = (ImageView) view.findViewById(R.id.kongzhuangtai);
        shangchuanwode = (ImageView) view.findViewById(R.id.shangchuanwode);
        meiyouwode = (LinearLayout) view.findViewById(R.id.meiyouwode);
        gerenmyvideo = (RelativeLayout) view.findViewById(R.id.gerenmyvideo);

        zan = (ImageView) view.findViewById(R.id.zan);
        zannum = (TextView) view.findViewById(R.id.zannum);
        fenxiang = (ImageView) view.findViewById(R.id.fenxiang);
        chonglu = (TextView) view.findViewById(R.id.chonglu);
        youwode = (LinearLayout) view.findViewById(R.id.youwode);


    }

    @Override
    public void setResultData(String resultData) {

    }

    @Override
    public void setPresenter(GerenContract.GerenPresenter gerenPresenter) {
        this.gerenPresenter=gerenPresenter;
    }
}
