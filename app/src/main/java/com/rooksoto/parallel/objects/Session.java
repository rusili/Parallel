package com.rooksoto.parallel.objects;

import java.util.Date;

public class Session {
    String name;
    String tagline;
    String location;
    String startTime;
    Date endTime;

    public Session(String nameP, String locationP, String startTimeP, Date endTimeP){
        this.name = nameP;
        this.location = locationP;
        this.startTime = startTimeP;
        this.endTime = endTimeP;
    }

    public Session(String name, String location, String startTime) {
        this.name = name;
        this.location = location;
        this.startTime = startTime;
    }

    public String getName () {
        return name;
    }

    public String getTagline () {
        return tagline;
    }

    public String getLocation () {
        return location;
    }

    public String getStartTime () {
        return startTime;
    }

    public Date getEndTime () {
        return endTime;
    }
}
