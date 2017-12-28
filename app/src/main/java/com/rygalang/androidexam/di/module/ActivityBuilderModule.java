package com.rygalang.androidexam.di.module;

import com.rygalang.androidexam.person.list.PersonListModule;
import com.rygalang.androidexam.person.list.view.PersonListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Computer3 on 12/7/2017.
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {PersonListModule.class})
    abstract PersonListActivity bindMainActivity();
}
