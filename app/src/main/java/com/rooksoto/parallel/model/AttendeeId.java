
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendeeId {

    @SerializedName("attendee_name")
    @Expose
    private String attendeeName;
    @SerializedName("vip_status")
    @Expose
    private Boolean vipStatus;
    @SerializedName("answer_string")
    @Expose
    private String answerString;
    @SerializedName("answer_list")
    @Expose
    private AnswerList answerList;

    public String getAttendeeName() {
        return attendeeName;
    }

    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }

    public Boolean getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(Boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    public String getAnswerString() {
        return answerString;
    }

    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    public AnswerList getAnswerList() {
        return answerList;
    }

    public void setAnswerList(AnswerList answerList) {
        this.answerList = answerList;
    }
}
