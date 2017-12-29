package com.rygalang.androidexam.person.list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rygalang.androidexam.databinding.PersonItemLayoutBinding;
import com.rygalang.androidexam.model.Person;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Computer3 on 12/28/2017.
 */

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonListViewHolder> {

    private List<Person> personArrayList = new ArrayList<>();
    private PublishSubject<Person> personPublishSubject;

    public PersonListAdapter(Consumer<Person> onSelectPerson) {
        personPublishSubject = PublishSubject.create();
        personPublishSubject.subscribe(onSelectPerson);
    }

    public void setPersonArrayList(List<Person> personArrayList) {
        this.personArrayList.clear();
        this.personArrayList.addAll(personArrayList);
        this.notifyDataSetChanged();
    }

    @Override
    public PersonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final PersonItemLayoutBinding itemLayoutBinding = PersonItemLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PersonListViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(PersonListViewHolder holder, int position) {
        final Person person = personArrayList.get(position);
        holder.itemLayoutBinding.tvFullName.setText(String.format("%s %s", person.firstName, person.lastName));
        holder.itemLayoutBinding.tvEmail.setText(person.email);
        holder.itemLayoutBinding.getRoot().setOnClickListener((v) -> personPublishSubject.onNext(person));

    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    public class PersonListViewHolder extends RecyclerView.ViewHolder {
        private PersonItemLayoutBinding itemLayoutBinding;

        public PersonListViewHolder(PersonItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }
    }
}
