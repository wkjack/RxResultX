package com.wkjack.rxresultx;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import io.reactivex.Observable;

public final class RxResult {

    private static final String TAG = "RxResultFragment";
    private RxResultFragment fragment;

    private RxResult(FragmentActivity activity) {
        fragment = getFragment(activity);
    }

    private RxResultFragment getFragment(FragmentActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("activity is null");
        }

        RxResultFragment activityResultFragment = (RxResultFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
        if (activityResultFragment == null) {
            activityResultFragment = new RxResultFragment();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(activityResultFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return activityResultFragment;
    }


    /**
     * 调用
     *
     * @param intent   意图
     * @param callback 回调
     */
    public final void start(Intent intent, RxResultCallback callback) {
        fragment.startResult(intent, callback);
    }

    /**
     * 调用
     *
     * @param intent 回调
     * @return 返回被观察对象
     */
    public final Observable<RxResultInfo> start(Intent intent) {
        return fragment.startResult(intent);
    }


    /**
     * 创建RxResult对象
     *
     * @param activity 上下文
     * @return
     */
    public static RxResult in(FragmentActivity activity) {
        return new RxResult(activity);
    }
}