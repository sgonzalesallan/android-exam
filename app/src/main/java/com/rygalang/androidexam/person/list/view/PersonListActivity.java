package com.rygalang.androidexam.person.list.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.rygalang.androidexam.R;
import com.rygalang.androidexam.base.BaseActivity;
import com.rygalang.androidexam.databinding.ActivityPersonListBinding;
import com.rygalang.androidexam.model.Person;
import com.rygalang.androidexam.person.list.presenter.PersonListAction;

import java.util.ArrayList;

import javax.inject.Inject;


public class PersonListActivity extends BaseActivity<PersonListView, PersonListAction>
        implements PersonListView, SwipeRefreshLayout.OnRefreshListener {

    private ActivityPersonListBinding personListBinding;
    private PersonListAdapter personListAdapter;

    @Inject
    PersonListAction personListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personListBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_person_list);

        personListBinding.srlPerson.setOnRefreshListener(this);

        initPersonListRecycler();

        presenter.fetchPerson();
    }

    @NonNull
    @Override
    public PersonListAction createPresenter() {
        return personListPresenter;
    }


    @Override
    public void showLoading() {
        personListBinding.srlPerson.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        personListBinding.srlPerson.setRefreshing(false);
    }

    @Override
    public void showPersonList(ArrayList<Person> personList) {
        log(">>> " + personList.size());
        personListAdapter.setPersonArrayList(personList);
    }

    @Override
    public void showError(String errorMessage) {
        displaySnackBar(personListBinding.getRoot(), errorMessage);
    }

    @Override
    public void onRefresh() {
        presenter.fetchPerson();
    }

    private void initPersonListRecycler() {
        personListAdapter = new PersonListAdapter();
        personListBinding.rvPersonList.setAdapter(personListAdapter);
    }
}
