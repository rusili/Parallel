package com.rooksoto.parallel.network.objects;

import java.util.Date;

public class User {
    String email;
    String password;
    String username;
    String firstname;
    String lastname;
    Date birthday;

    public void setEmail (String email) {
        this.email = email;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public void setFirstname (String firstname) {
        this.firstname = firstname;
    }

    public void setLastname (String lastname) {
        this.lastname = lastname;
    }

    public void setBirthday (Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail () {
        return email;
    }

    public String getPassword () {
        return password;
    }

    public String getUsername () {
        return username;
    }

    public String getFirstname () {
        return firstname;
    }

    public String getLastname () {
        return lastname;
    }

    public Date getBirthday () {
        return birthday;
    }
}
