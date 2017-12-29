package com.rygalang.androidexam.base;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.rygalang.androidexam.BuildConfig;
import com.rygalang.androidexam.model.Person;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Computer3 on 12/28/2017.
 */

public abstract class BaseActivity<V extends BaseView, P extends BaseAction<V>>
        extends MvpActivity<V, P> implements HasSupportFragmentInjector, HasFragmentInjector {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentAndroidInjector;
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return supportFragmentAndroidInjector;
    }

    public void displaySnackBar(View rootView, String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }

    public void log(String message) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, message);
    }

    public boolean hasActiveInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
