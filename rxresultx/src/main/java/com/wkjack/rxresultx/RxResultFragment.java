package com.wkjack.rxresultx;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.fragment.app.Fragment;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class RxResultFragment extends Fragment {
    private SparseArray<PublishSubject<RxResultInfo>> mSubjects = new SparseArray<>();
    private SparseArray<RxResultCallback> mCallbacks = new SparseArray<>();


    public RxResultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PublishSubject<RxResultInfo> subject = mSubjects.get(requestCode);
        if (subject != null) {
            mSubjects.delete(requestCode);
            subject.onNext(new RxResultInfo(resultCode, data));
            subject.onComplete();
        }

        RxResultCallback callback = mCallbacks.get(requestCode);
        if (callback != null) {
            mCallbacks.delete(requestCode);
            callback.onResult(new RxResultInfo(resultCode, data));
        }
    }


    public Observable<RxResultInfo> startResult(final Intent intent) {
        final PublishSubject<RxResultInfo> subject = PublishSubject.create();
        return subject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) {
                int requestCode = generateRequestCode();
                mSubjects.put(requestCode, subject);
                startActivityForResult(intent, requestCode);
            }
        });
    }

    public void startResult(Intent intent, RxResultCallback callback) {
        int requestCode = generateRequestCode();
        mCallbacks.put(requestCode, callback);
        startActivityForResult(intent, requestCode);
    }


    private int generateRequestCode() {
        Random random = new Random();
        for (; ; ) {
            int code = random.nextInt(65535);
            if (mSubjects.get(code) == null && mCallbacks.get(code) == null) {
                return code;
            }
        }
    }
}