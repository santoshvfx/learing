package com.att.lms.bis.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.Random;

public enum User {
    MISTER_NOBODY("mnobody", "Mister", "Nobody", "doesntmatter"),
    M49476("m49476@lms.att.com", "lmsMechId", "lmsMechId", "Middleware$123");

    @JsonIgnore
    private String name;

    @JsonIgnore
    private String firstName;

    @JsonIgnore
    private String lastName;

    @JsonIgnore
    private String password;


    private User(final String name, final String firstName, final String lastName, final String password) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    @JsonProperty("name")
    public String getUserId() {
        return name;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    public static User fromValue(final String value) {
        return Arrays.stream(values()).filter(enumm -> enumm.getUserId().equals(value)).findFirst().get();
    }

    public static User fromNumber(final int value) {
        return Arrays.asList(values()).get(value);
    }

    public static int getLength() {
        return values().length;
    }

    public static User getRandomType() {

        Random rand = new Random();

        return fromNumber(Math.abs(rand.nextInt()) % (getLength() - 1));

    }
}