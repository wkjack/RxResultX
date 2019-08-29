package com.wkjack.rxresultx.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wkjack.rxresultx.R;

public class AndroidXActivity extends AppCompatActivity {

    private int type; // 0(回调) / 1(观察者)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_x);

        findViewById(R.id.androidX_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("showContent", type == 1 ? "观察者模式数据" : "回调模式数据");
                setResult(RESULT_OK, intent);
                AndroidXActivity.this.finish();
            }
        });

        type = getIntent().getIntExtra("type", 0);
    }
}
