package com.rygalang.androidexam.person.list.presenter;

import com.rygalang.androidexam.base.BasePresenter;
import com.rygalang.androidexam.person.list.view.PersonListView;
import com.rygalang.androidexam.person.service.PersonService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Computer3 on 12/28/2017.
 */

public class PersonListPresenter extends BasePresenter<PersonListView> implements PersonListAction {

    private PersonService personService;

    @Inject
    public PersonListPresenter(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void fetchPerson() {
        getView().showLoading();
        setDisposable(personService.getPersonList()
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
}
