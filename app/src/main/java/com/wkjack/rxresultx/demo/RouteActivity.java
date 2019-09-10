package com.wkjack.rxresultx.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/aaa/bbb")
public class RouteActivity extends AppCompatActivity {

    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        findViewById(R.id.androidX_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("showContent", type == 1 ? "Arouter观察者返回数据" : "Arouter回调返回数据");
                setResult(RESULT_OK, intent);
                RouteActivity.this.finish();
            }
        });

        type = getIntent().getIntExtra("type", 0);
    }
}
