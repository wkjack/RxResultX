package com.wkjack.rxresultx;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.launcher.ARouter;

public class RxResultActivity extends AppCompatActivity {

    private static final int CODE_AROUT = 40354;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_result);

        Intent intent = getIntent();
        String path = intent.getStringExtra("_rx_path");
        int enterAnim = intent.getIntExtra("_rx_enterAnim", -1);
        int exitAnim = intent.getIntExtra("_rx_exitAnim", -1);

        ARouter.getInstance().build(path)
                .with(intent.getExtras())
                .withTransition(enterAnim, exitAnim)
                .navigation(this, CODE_AROUT, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        DegradeService degradeService = ARouter.getInstance().navigation(DegradeService.class);
                        if (null != degradeService) {
                            degradeService.onLost(RxResultActivity.this, postcard);
                        }
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