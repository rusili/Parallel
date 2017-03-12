
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attendee {

    @SerializedName("attendee_name")
    @Expose
    public String attendeeName;
    @SerializedName("vip_status")
    @Expose
    public Boolean vipStatus;
    @SerializedName("answer_string")
    @Expose
    public String answerString;
    @SerializedName("answer_list")
    @Expose
    public AnswerList answerList;

}
