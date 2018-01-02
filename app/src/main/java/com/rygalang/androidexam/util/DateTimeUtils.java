package com.rygalang.androidexam.util;

import com.rygalang.androidexam.BuildConfig;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Computer3 on 1/2/2018.
 */

public class DateTimeUtils {
    private static final Locale LOCALE = Locale.getDefault();

    public static String toString(String parseFormat, String displayFormat, String date) {
        DateFormat from = new SimpleDateFormat(parseFormat, LOCALE); // current format
        DateFormat to = new SimpleDateFormat(displayFormat, LOCALE); // wanted format
        try {
            return to.format(from.parse(date));
        } catch (ParseException e) {
            if (BuildConfig.DEBUG) e.printStackTrace();
        }
        return "";
    }

    public static Date toDate(String parseFormat, String toParse) {
        try {
            return new SimpleDateFormat(parseFormat, LOCALE).parse(toParse);
        } catch (ParseException e) {
            if (BuildConfig.DEBUG) e.printStackTrace();
        }
        return null;
    }

    public static int computeAge(Date birthDate) {
        final Calendar birthDay = Calendar.getInstance(LOCALE);
        birthDay.setTime(birthDate);
        final Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        int diffYear = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currentMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;
        int diffMonth = currentMonth - birthMonth;

        if (diffMonth < 0) {
            diffYear--;
        } else if (diffMonth == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            diffYear--;
        }
        return diffYear;
    }
}
