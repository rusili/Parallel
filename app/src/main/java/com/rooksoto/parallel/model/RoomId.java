
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomId {

    @SerializedName("message_id")
    @Expose
    private List<MessageId> messageIds;

    public List<MessageId> getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(List<MessageId> messageIds) {
        this.messageIds = messageIds;
    }
}
