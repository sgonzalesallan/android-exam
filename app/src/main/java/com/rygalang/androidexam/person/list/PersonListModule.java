package com.rygalang.androidexam.person.list;

import com.rygalang.androidexam.database.AppDatabase;
import com.rygalang.androidexam.person.list.presenter.PersonListAction;
import com.rygalang.androidexam.person.list.presenter.PersonListPresenter;
import com.rygalang.androidexam.person.service.PersonService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Computer3 on 12/28/2017.
 */
@Module
public class PersonListModule {

    @Provides
    PersonListAction personListPresenter(PersonService personService, AppDatabase appDatabase) {
        return new PersonListPresenter(personService, appDatabase);
    }
}
