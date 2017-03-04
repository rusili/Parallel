package com.rooksoto.parallel.pojos;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by huilin on 3/2/17.
 */
@IgnoreExtraProperties
public class Chat {
    private String name;
    private String text;

    public Chat() {
    }

    public Chat(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
