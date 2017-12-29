package com.rygalang.androidexam.person.service;

import com.rygalang.androidexam.model.Person;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Computer3 on 12/28/2017.
 */

public interface PersonService {

    @GET("person")
    Single<List<Person>> getPersonList();
}
