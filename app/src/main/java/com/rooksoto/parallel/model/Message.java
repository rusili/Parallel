
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("message_user_name")
    @Expose
    public String messageUserName;
    @SerializedName("message_text")
    @Expose
    public String messageText;
    @SerializedName("message_timestamp")
    @Expose
    public String messageTimestamp;
    @SerializedName("profile_pic")
    @Expose
    public String profilePic;

}
