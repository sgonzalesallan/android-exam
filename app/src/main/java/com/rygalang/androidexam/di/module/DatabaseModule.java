package com.rygalang.androidexam.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.rygalang.androidexam.base.AppConstant;
import com.rygalang.androidexam.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Computer3 on 12/29/2017.
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase appDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, AppConstant.DB_NAME).build();
    }
}
