package com.wkjack.rxresultx;

/**
 * 回调接口
 */
public interface RxResultCallback {

    /**
     * 回调
     *
     * @param resultInfo 回调信息
     */
    void onResult(RxResultInfo resultInfo);
}