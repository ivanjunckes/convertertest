package com.test;

import org.apache.johnzon.mapper.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter implements Converter<Date> {
    private final ThreadLocal<DateFormat> format;

    public DateTimeConverter() {
        format = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            }
        };
    }

    @Override
    public String toString(final Date instance) {
        return format.get().format(instance);
    }

    @Override
    public Date fromString(final String text) {
        try {
            return format.get().parse(text);
        } catch (final ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
