
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("admin")
    @Expose
    private Admin admin;
    @SerializedName("room_list")
    @Expose
    private RoomList roomList;
    @SerializedName("event_description")
    @Expose
    private String eventDescription;
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("event_location")
    @Expose
    private EventLocation eventLocation;
    @SerializedName("attendee_list")
    @Expose
    private AttendeeList attendeeList;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public RoomList getRoomList() {
        return roomList;
    }

    public void setRoomList(RoomList roomList) {
        this.roomList = roomList;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public EventLocation getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocation eventLocation) {
        this.eventLocation = eventLocation;
    }

    public AttendeeList getAttendeeList() {
        return attendeeList;
    }

    public void setAttendeeList(AttendeeList attendeeList) {
        this.attendeeList = attendeeList;
    }
}
