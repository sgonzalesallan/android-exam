package com.rygalang.androidexam.base;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Computer3 on 12/28/2017.
 */

public abstract class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void setDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
            this.compositeDisposable.clear();
    }
}
