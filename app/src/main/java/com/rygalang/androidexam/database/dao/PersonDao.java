package com.rygalang.androidexam.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rygalang.androidexam.model.Person;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Computer3 on 12/29/2017.
 */
@Dao
public interface PersonDao {

    @Query("select * from persons")
    Single<List<Person>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> batchInsert(List<Person> persons);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Person person);

    @Query("select count(_id) from persons")
    int count();

    @Update
    int updateTransactions(Person item);

}
