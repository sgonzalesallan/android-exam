package com.rygalang.androidexam.person.list.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.rygalang.androidexam.R;
import com.rygalang.androidexam.base.AppConstant;
import com.rygalang.androidexam.base.BaseActivity;
import com.rygalang.androidexam.databinding.ActivityPersonListBinding;
import com.rygalang.androidexam.model.Person;
import com.rygalang.androidexam.person.details.view.PersonDetailActivity;
import com.rygalang.androidexam.person.list.presenter.PersonListAction;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.subjects.BehaviorSubject;


public class PersonListActivity extends BaseActivity<PersonListView, PersonListAction>
        implements PersonListView, SwipeRefreshLayout.OnRefreshListener {

    private ActivityPersonListBinding personListBinding;
    private PersonListAdapter personListAdapter;
    public static BehaviorSubject<Person> personBehaviorSubject = BehaviorSubject.create();

    @Inject
    PersonListAction personListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personListBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_person_list);

        personListBinding.srlPerson.setOnRefreshListener(this);

        initPersonListRecycler();
        if (hasActiveInternetConnection()) {
            presenter.fetchPerson();
        } else {
            displaySnackBar(personListBinding.getRoot(),
                    AppConstant.NO_CONNECTION_ERROR_TEXT);
            presenter.fetchPersonFromDb();
        }
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
    public void showPersonList(List<Person> personList) {
        personListAdapter.setPersonArrayList(personList);
        personListBinding.tvEmptyState.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        if (personListAdapter.getItemCount() == 0) {
            personListBinding.tvEmptyState.setVisibility(View.VISIBLE);
        }
        displaySnackBar(personListBinding.getRoot(), errorMessage);
    }

    @Override
    public void onRefresh() {
        if (hasActiveInternetConnection()) {
            presenter.fetchPerson();
        } else {
            displaySnackBar(personListBinding.getRoot(),
                    AppConstant.NO_CONNECTION_ERROR_TEXT);
            presenter.fetchPersonFromDb();
        }
    }

    private void initPersonListRecycler() {
        personListAdapter = new PersonListAdapter(this::selectedPerson);
        personListBinding.rvPersonList.setAdapter(personListAdapter);
    }

    private void selectedPerson(Person person) {
        personBehaviorSubject.onNext(person);
        startActivity(new Intent(this, PersonDetailActivity.class));
    }
}
