package com.rygalang.androidexam.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.rygalang.androidexam.database.dao.PersonDao;
import com.rygalang.androidexam.model.Person;

/**
 * Created by Computer3 on 12/29/2017.
 */
@Database(version = 1, entities = {Person.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract PersonDao personDao();

}
