package com.hxbj.bijihui.module.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxbj.bijihui.R;
import com.hxbj.bijihui.base.BaseActivity;
import com.hxbj.bijihui.module.landing.LandingActivity;
import com.hxbj.bijihui.utils.AppUtils;

/**
 * Created by Allen on 2017/7/10.
 * H5通用界面
 */
public class WebViewCurrencyActivity extends BaseActivity {

    public static Intent getIntent(Context context,String url,String title) {
        Intent intent = new Intent(context, WebViewCurrencyActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        return intent;
    }


    private String url;
    private WebView webView;
    private ImageView user_icon;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_activity);
        AppUtils.setTitle(this);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        if(TextUtils.isEmpty(title)){
            tv_title.setText(R.string.app_name);
        }else{
            tv_title.setText(title);
        }
        webView = (WebView) findViewById(R.id.webview);
        user_icon = (ImageView) findViewById(R.id.user_icon);
        user_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initWebData(url);
    }

    private void initWebData(String html) {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(html);

    }
}
