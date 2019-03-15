package com.test;

import java.text.ParseException;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.johnzon.mapper.Converter;

@ApplicationScoped
public class DateTimeConverter implements Converter<Date> {

    private final FastDateFormat df;

    public DateTimeConverter(final String pattern) {
        df = FastDateFormat.getInstance(pattern);
    }

    public DateTimeConverter() {
        this("dd/MM/yyyy HH:mm:ss");
    }

    @Override
    public String toString(final Date instance) {
        return df.format(instance);
    }

    @Override
    public Date fromString(final String text) {
        try {
            return df.parse(text);

        } catch (final ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
