package com.vjimbei.backbase_hackathon_android.ui.utils;

import android.util.Log;

import com.google.inject.Inject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by vjimbei on 6/23/2016.
 */
public class DateUtils {
    private static final String dateFormat = "dd/MM/yyyy";

    @Inject
    public DateUtils() {
    }

    public Calendar getCalendarInstance() {
        return Calendar.getInstance(Locale.UK);
    }

    public String getDateAsString(Date date) {
        return new SimpleDateFormat(dateFormat, Locale.ENGLISH).format(date);
    }

    public Date getStringAsDate(String date) {
        try {
            return new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            Log.e("DateUtils", e.getLocalizedMessage());
        }
        return null;
    }
}
