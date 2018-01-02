package com.rygalang.androidexam.person.list.view;

import com.rygalang.androidexam.base.BaseView;
import com.rygalang.androidexam.model.Person;

import java.util.List;

/**
 * Created by Computer3 on 12/28/2017.
 */

public interface PersonListView extends BaseView {
    void showLoading();

    void hideLoading();

    void showPersonList(List<Person> personList);

    void showError(String errorMessage);

    void showNoConnectionError();

    void showConnectionTimeout();

    void displayEmptyView();

    void showResourceNotFoundError();

    void showServerError();
}
