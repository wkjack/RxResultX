package com.wkjack.rxresultx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.alibaba.android.arouter.facade.Postcard;

import io.reactivex.Observable;

public final class RxResult {

    private static final String TAG = "RxResultFragment";
    private RxResultFragment fragment;

    private RxResult(FragmentActivity activity) {
        fragment = getFragment(activity);
    }

    private RxResultFragment getFragment(FragmentActivity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return null;
        }
        RxResultFragment activityResultFragment = (RxResultFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
        if (activityResultFragment == null) {
            activityResultFragment = new RxResultFragment();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            if (fragmentManager.isDestroyed()) {
                return null;
            }
            try {
                fragmentManager.beginTransaction()
                        .add(activityResultFragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
                return activityResultFragment;
            } catch (IllegalStateException ignored) {
            }
        }
        return null;
    }


    /**
     * 调用
     *
     * @param intent   意图
     * @param callback 回调
     */
    public void start(Intent intent, RxResultCallback callback) {
        Log.e("RxResult", "start-1-->" + (fragment == null));
        if (fragment != null) {
            fragment.startResult(intent, callback);
        }
    }

    /**
     * 调用
     *
     * @param intent 回调
     * @return 返回被观察对象
     */
    public Observable<RxResultInfo> start(Intent intent) {
        Log.e("RxResult", "start-2-->" + (fragment == null));
        if (fragment != null) {
            return fragment.startResult(intent);
        }
        RxResultInfo rxResultInfo = new RxResultInfo(Activity.RESULT_CANCELED, null);
        return Observable.just(rxResultInfo);
    }

    /**
     * 调用
     *
     * @param postcard 路由信息
     * @return 返回被观察对象
     */
    public Observable<RxResultInfo> start(Postcard postcard) {
        Log.e("RxResult", "start-3-->" + (fragment == null));
        if (fragment != null) {
            return fragment.startResult(postcard);
        }
        RxResultInfo rxResultInfo = new RxResultInfo(Activity.RESULT_CANCELED, null);
        return Observable.just(rxResultInfo);
    }

    /**
     * 调用
     *
     * @param postcard 路由信息
     * @param callback 回调
     */
    public void start(Postcard postcard, RxResultCallback callback) {
        Log.e("RxResult", "start-4-->" + (fragment == null));
        if (fragment != null) {
            fragment.startResult(postcard, callback);
        }
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