package com.rooksoto.parallel.objects;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ChatMessage {
    private String name;
    private String text;
    private String profilePic;

    public ChatMessage () {
    }

    public ChatMessage (String name, String text, String profilePic) {
        this.name = name;
        this.text = text;
        this.profilePic = profilePic;
    }

    public ChatMessage (String name, String text) {
        this.name = name;
        this.text = text;
    }


    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getText () {
        return text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public String getProfilePic () {
        return profilePic;
    }
}
