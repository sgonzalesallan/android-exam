package com.rygalang.androidexam.person.details.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.rygalang.androidexam.R;
import com.rygalang.androidexam.databinding.ActivityPersonDetailBinding;
import com.rygalang.androidexam.model.Person;
import com.rygalang.androidexam.person.list.view.PersonListActivity;
import com.rygalang.androidexam.util.DateTimeUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class PersonDetailActivity extends AppCompatActivity {

    private ActivityPersonDetailBinding personDetailBinding;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personDetailBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_person_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        PersonListActivity.personBehaviorSubject.subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadPersonDetails);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void loadPersonDetails(Person person) {
        this.person = person;
        personDetailBinding.tvFullName.setText(String.format("%s, %s",
                person.firstName, person.lastName));
        final String parseFormat = "mm/dd/yyyy, hh:mm:ss a";
        final String birthdayText = DateTimeUtils.toString(parseFormat,
                "MMMM dd, yyyy", person.birthday);
        final String ageText = String.format("%d years old",
                DateTimeUtils.computeAge(DateTimeUtils.toDate(parseFormat, person.birthday)));
        personDetailBinding.tvBirthday.setText(birthdayText);
        personDetailBinding.tvAge.setText(ageText);
        personDetailBinding.tvEmail.setText(person.email);
        personDetailBinding.tvMobileNumber.setText(person.mobileNumber);
        personDetailBinding.tvAddress.setText(person.address);
        personDetailBinding.tvContactPersonNumber.setText(person.contactPerson.phoneNumber);
        personDetailBinding.tvContactPersonName.setText(person.contactPerson.name);
    }
}
