package com.hxbj.bijihui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hxbj.bijihui.module.HomeActivity;

public class LodingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);

        Intent intent=new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
