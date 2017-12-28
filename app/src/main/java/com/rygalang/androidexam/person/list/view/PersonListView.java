package com.rygalang.androidexam.person.list.view;

import com.rygalang.androidexam.base.BaseView;
import com.rygalang.androidexam.model.Person;

import java.util.ArrayList;

/**
 * Created by Computer3 on 12/28/2017.
 */

public interface PersonListView extends BaseView {
    void showLoading();

    void hideLoading();

    void showPersonList(ArrayList<Person> personList);

    void showError(String errorMessage);
}
