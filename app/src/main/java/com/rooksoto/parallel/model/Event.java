
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("admin")
    @Expose
    public Admin admin;
    @SerializedName("rooms")
    @Expose
    public Rooms rooms;
    @SerializedName("event_description")
    @Expose
    public String eventDescription;
    @SerializedName("event_name")
    @Expose
    public String eventName;
    @SerializedName("event_id")
    @Expose
    public String eventId;
    @SerializedName("event_location")
    @Expose
    public EventLocation eventLocation;
    @SerializedName("attendees")
    @Expose
    public Attendees attendees;

}
