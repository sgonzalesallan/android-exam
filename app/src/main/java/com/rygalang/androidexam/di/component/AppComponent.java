package com.rygalang.androidexam.di.component;

import com.rygalang.androidexam.App;
import com.rygalang.androidexam.di.module.ActivityBuilderModule;
import com.rygalang.androidexam.di.module.ApplicationModule;
import com.rygalang.androidexam.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Computer3 on 12/28/2017.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        ApplicationModule.class,
        NetworkModule.class,
        ActivityBuilderModule.class
})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
