package com.test;

import java.util.Date;

import org.apache.johnzon.mapper.internal.ConverterAdapter;

public class DateTimeAdapter extends ConverterAdapter<Date> {

    public DateTimeAdapter() {
        super(new DateTimeConverter());
    }

    public DateTimeAdapter(final String pattern) {
        super(new DateTimeConverter(pattern));
    }
}
