package com.rygalang.androidexam.person.list.presenter;

import com.rygalang.androidexam.base.AppConstant;
import com.rygalang.androidexam.base.BasePresenter;
import com.rygalang.androidexam.database.AppDatabase;
import com.rygalang.androidexam.model.Person;
import com.rygalang.androidexam.person.list.view.PersonListView;
import com.rygalang.androidexam.person.service.PersonService;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.http.HTTP;

/**
 * Created by Computer3 on 12/28/2017.
 */

public class PersonListPresenter extends BasePresenter<PersonListView> implements PersonListAction {

    private PersonService personService;
    private AppDatabase appDatabase;

    @Inject
    public PersonListPresenter(PersonService personService, AppDatabase appDatabase) {
        this.personService = personService;
        this.appDatabase = appDatabase;
    }

    @Override
    public void fetchPersonFromRemote() {
        getView().showLoading();
        setDisposable(personService.getPersonList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(personList -> {
                    insertAllPerson(personList);
                    getView().hideLoading();
                    getView().showPersonList(personList);
                }, throwable -> {
                    throwable.printStackTrace();
                    getView().hideLoading();
                    parseError(throwable);
                }));
    }

    @Override
    public void fetchPersonFromDb() {
        getView().showLoading();
        setDisposable(appDatabase.personDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(personList -> {
                    getView().hideLoading();
                    if (personList.isEmpty()) {
                        getView().displayEmptyView();
                    } else {
                        getView().showPersonList(personList);
                    }
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

    private void insertAllPerson(List<Person> persons) {
        setDisposable(Single.fromCallable(() -> appDatabase.personDao().batchInsert(persons))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe());
    }

    private void parseError(Throwable throwable) {
        if (throwable instanceof SocketTimeoutException) {
            getView().showConnectionTimeout();
        } else if (throwable instanceof HttpException) {
            final HttpException httpException = (HttpException) throwable;
            if (httpException.code() == 404) {
                getView().showResourceNotFoundError();
            } else if (httpException.code() >= 500) {
                getView().showServerError();
            } else {
                getView().showError(throwable.getMessage());
            }
        } else {
            getView().showError(throwable.getMessage());
        }
    }
}
