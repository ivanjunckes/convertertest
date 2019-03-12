package com.test;

import org.apache.johnzon.mapper.JohnzonConverter;

import java.util.Date;

public class User {

    private Long id;

    @JohnzonConverter(DateTimeConverter.class)
    private Date date;

    public User(){

    }

    public User(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
