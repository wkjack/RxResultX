package com.wkjack.rxresultx.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wkjack.rxresultx.RxResult;
import com.wkjack.rxresultx.RxResultCallback;
import com.wkjack.rxresultx.RxResultInfo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private AppCompatTextView showTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTv = findViewById(R.id.showTv);

        findViewById(R.id.androidX_callback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AndroidXActivity.class);
                intent.putExtra("type", 0);

                RxResult.in(MainActivity.this)
                        .start(intent, new RxResultCallback() {
                            @Override
                            public void onResult(RxResultInfo resultInfo) {
                                Intent data = resultInfo.getData();
                                String showContent = data.getStringExtra("showContent");
                                showTv.setText(showContent);
                            }
                        });
            }
        });

        findViewById(R.id.androidX_obser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AndroidXActivity.class);
                intent.putExtra("type", 1);

                RxResult.in(MainActivity.this)
                        .start(intent)
                        .subscribe(new Observer<RxResultInfo>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RxResultInfo resultInfo) {
                                Intent data = resultInfo.getData();
                                String showContent = data.getStringExtra("showContent");
                                showTv.setText(showContent);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        findViewById(R.id.androidX_callback_arouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Postcard postcard = ARouter.getInstance()
                        .build("/aaa/bbb")
                        .withInt("type", 0);

                RxResult.in(MainActivity.this)
                        .start(postcard, new RxResultCallback() {
                            @Override
                            public void onResult(RxResultInfo resultInfo) {
                                if (resultInfo.getResultCode() == RESULT_OK) {
                                    Intent data = resultInfo.getData();
                                    String showContent = data.getStringExtra("showContent");
                                    showTv.setText(showContent);
                                }
                            }
                        });
            }
        });

        findViewById(R.id.androidX_obser_arouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Postcard postcard = ARouter.getInstance()
                        .build("/aaa/bbb")
                        .withInt("type", 1);

                RxResult.in(MainActivity.this)
                        .start(postcard)
                        .subscribe(new Observer<RxResultInfo>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RxResultInfo resultInfo) {
                                if (resultInfo.getResultCode() == RESULT_OK) {
                                    Intent data = resultInfo.getData();
                                    String showContent = data.getStringExtra("showContent");
                                    showTv.setText(showContent);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }
}
