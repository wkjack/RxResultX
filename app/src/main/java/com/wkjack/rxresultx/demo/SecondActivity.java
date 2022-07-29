package com.wkjack.rxresultx.demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wkjack.rxresultx.RxResult;
import com.wkjack.rxresultx.RxResultCallback;
import com.wkjack.rxresultx.RxResultInfo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@SuppressLint("HandlerLeak")
public class SecondActivity extends AppCompatActivity {

    private AppCompatTextView showTv;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            int what = msg.what;
            if (what == 1) {
                Intent intent = new Intent(SecondActivity.this, AndroidXActivity.class);
                intent.putExtra("type", 0);

                RxResult.in(SecondActivity.this)
                        .start(intent, new RxResultCallback() {
                            @Override
                            public void onResult(RxResultInfo resultInfo) {
                                Intent data = resultInfo.getData();
                                String showContent = data.getStringExtra("showContent");
                                showTv.setText(showContent);
                            }
                        });
                return;
            }
            if (what == 2) {
                Intent intent = new Intent(SecondActivity.this, AndroidXActivity.class);
                intent.putExtra("type", 1);

                RxResult.in(SecondActivity.this)
                        .start(intent)
                        .subscribe(new Observer<RxResultInfo>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RxResultInfo resultInfo) {
                                Intent data = resultInfo.getData();
                                if (data !=null) {
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
                return;
            }

            if (what == 3) {
                Postcard postcard = ARouter.getInstance()
                        .build("/aaa/bbb")
                        .withInt("type", 0)
                        .withTransition(R.anim.bottom_in, R.anim.bottom_silent);

                RxResult.in(SecondActivity.this)
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
                return;
            }
            if (what == 4) {
                Postcard postcard = ARouter.getInstance()
                        .build("/aaa/bbb")
                        .withInt("type", 1);

                RxResult.in(SecondActivity.this)
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
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showTv = findViewById(R.id.showTv);

        findViewById(R.id.androidX_callback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(1, 100);
                SecondActivity.this.finish();
            }
        });

        findViewById(R.id.androidX_obser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(2, 200);
                SecondActivity.this.finish();
            }
        });

        findViewById(R.id.androidX_callback_arouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(3, 300);
                SecondActivity.this.finish();
            }
        });

        findViewById(R.id.androidX_obser_arouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(4, 400);
                SecondActivity.this.finish();
            }
        });
    }
}