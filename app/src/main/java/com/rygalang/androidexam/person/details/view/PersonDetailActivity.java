package com.rygalang.androidexam.person.details.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.rygalang.androidexam.R;
import com.rygalang.androidexam.databinding.ActivityPersonDetailBinding;
import com.rygalang.androidexam.model.Person;
import com.rygalang.androidexam.person.list.view.PersonListActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

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
        personDetailBinding.tvBirthday.setText(convertDate(person.birthday));
        personDetailBinding.tvEmail.setText(person.email);
        personDetailBinding.tvMobileNumber.setText(person.mobileNumber);
        personDetailBinding.tvAddress.setText(person.address);
        personDetailBinding.tvContactPersonNumber.setText(person.contactPerson.phoneNumber);
        personDetailBinding.tvContactPersonName.setText(person.contactPerson.name);
    }

    private String convertDate(String date) {
        DateFormat from = new SimpleDateFormat("mm/dd/yyyy, hh:mm:ss a"); // wanted format
        DateFormat to = new SimpleDateFormat("MMMM dd, yyyy"); // current format
        try {
            return to.format(from.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
