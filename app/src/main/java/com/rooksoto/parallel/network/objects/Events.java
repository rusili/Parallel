package com.rooksoto.parallel.network.objects;

import java.util.Date;

public class Events {
    String name;
    String venue;
    String address;
    String city;
    int zipcode;
    Date date;
    Date time;

    public String getName () {
        return name;
    }

    public String getVenue () {
        return venue;
    }

    public String getAddress () {
        return address;
    }

    public String getCity () {
        return city;
    }

    public int getZipcode () {
        return zipcode;
    }

    public Date getDate () {
        return date;
    }

    public Date getTime () {
        return time;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setVenue (String venue) {
        this.venue = venue;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public void setZipcode (int zipcode) {
        this.zipcode = zipcode;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public void setTime (Date time) {
        this.time = time;
    }
}
