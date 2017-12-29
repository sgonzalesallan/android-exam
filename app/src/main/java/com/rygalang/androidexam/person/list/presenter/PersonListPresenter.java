package com.rygalang.androidexam.person.list.presenter;

import com.rygalang.androidexam.base.BasePresenter;
import com.rygalang.androidexam.database.AppDatabase;
import com.rygalang.androidexam.model.Person;
import com.rygalang.androidexam.person.list.view.PersonListView;
import com.rygalang.androidexam.person.service.PersonService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Computer3 on 12/28/2017.
 */

public class PersonListPresenter extends BasePresenter<PersonListView> implements PersonListAction {

    private PersonService personService;
    private AppDatabase appDatabase;
    private List<Person> personList = new ArrayList<>();

    @Inject
    public PersonListPresenter(PersonService personService, AppDatabase appDatabase) {
        this.personService = personService;
        this.appDatabase = appDatabase;
    }

    @Override
    public void fetchPerson() {
        getView().showLoading();
        setDisposable(personService.getPersonList()
                .flatMap(this::insertAll)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(ids -> {
                    getView().hideLoading();
                    getView().showPersonList(personList);
                }, throwable -> {
                    getView().hideLoading();
                    getView().showError(throwable.getMessage());
                }));
    }

    @Override
    public void fetchPersonFromDb() {
        getView().showLoading();
        setDisposable(appDatabase.personDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(persons -> {
                    getView().hideLoading();
                    getView().showPersonList(persons);
                }, throwable -> {
                    getView().hideLoading();
                    getView().showError(throwable.getMessage());
                }));
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        dispose();
    }

    private Single<List<Long>> insertAll(List<Person> persons) {
        this.personList = persons;
        return Single.fromCallable(() -> appDatabase.personDao().batchInsert(persons));
    }
}
