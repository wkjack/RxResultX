package com.wkjack.rxresultx.demo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

/**
 * 全局路由丢失的降级处理
 */
@Route(path = "/unsupport/support")
public class GlobalDegradeService implements DegradeService {
    @Override
    public void onLost(Context context, Postcard postcard) {
        String group = postcard.getGroup();
        Log.e("全局路由", postcard.getPath() + "未找到");
    }

    @Override
    public void init(Context context) {
    }
}