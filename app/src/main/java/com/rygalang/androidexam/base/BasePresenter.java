package com.rygalang.androidexam.base;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.disposables.Disposable;

/**
 * Created by Computer3 on 12/28/2017.
 */

public abstract class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {
    private Disposable disposable;

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }

    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
    }
}
