package com.rooksoto.parallel.objects;

import java.util.Date;

public class Session {
    String name;
    String tagline;
    String location;
    Date startTime;
    Date endTime;

    public Session(String nameP, String locationP, Date startTimeP, Date endTimeP){
        this.name = nameP;
        this.location = locationP;
        this.startTime = startTimeP;
        this.endTime = endTimeP;
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

    public Date getStartTime () {
        return startTime;
    }

    public Date getEndTime () {
        return endTime;
    }
}
