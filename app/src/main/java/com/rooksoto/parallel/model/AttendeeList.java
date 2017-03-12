
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttendeeList {

    @SerializedName("attendee_id")
    @Expose
    private List<AttendeeId> attendeeIds;

    public List<AttendeeId> getAttendeeIds() {
        return attendeeIds;
    }

    public void setAttendeeIds(List<AttendeeId> attendeeIds) {
        this.attendeeIds = attendeeIds;
    }
}
