package com.rygalang.androidexam.person.list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rygalang.androidexam.databinding.PersonItemLayoutBinding;
import com.rygalang.androidexam.model.Person;

import java.util.ArrayList;

/**
 * Created by Computer3 on 12/28/2017.
 */

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonListViewHolder> {

    private ArrayList<Person> personArrayList = new ArrayList<>();

    public PersonListAdapter() {
    }

    public void setPersonArrayList(ArrayList<Person> personArrayList) {
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
        holder.itemLayoutBinding.tvFullName.setText(person.getFullName());
        holder.itemLayoutBinding.tvEmail.setText(person.getEmail());
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
