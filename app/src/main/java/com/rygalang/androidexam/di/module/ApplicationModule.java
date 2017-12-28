package com.rygalang.androidexam.di.module;

import android.content.Context;

import com.rygalang.androidexam.App;
import com.rygalang.androidexam.util.sharedpreference.SharedPrefUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Computer3 on 12/28/2017.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Context context(App app) {
        return app.getApplicationContext();
    }


    @Provides
    @Singleton
    SharedPrefUtil sharedPrefUtil(Context context) {
        return new SharedPrefUtil(context);
    }
}
