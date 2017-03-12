
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomList {

    @SerializedName("room_id")
    @Expose
    private List<RoomId> roomIds;

    public List<RoomId> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<RoomId> roomIds) {
        this.roomIds = roomIds;
    }
}
