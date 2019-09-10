package com.wkjack.rxresultx;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class RxResultActivity extends AppCompatActivity {

    private static final int CODE_AROUT = 40354;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_result);

        Intent intent = getIntent();
        String path = intent.getStringExtra("path");

        ARouter.getInstance().build(path)
                .with(intent.getExtras())
                .navigation(this, CODE_AROUT, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        finish();
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(resultCode, data);
        finish();
    }
}