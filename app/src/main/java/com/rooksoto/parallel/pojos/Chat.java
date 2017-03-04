package com.rooksoto.parallel.pojos;

/**
 * Created by huilin on 3/2/17.
 */

public class Chat {
    private String mName;
    private String mText;

    public Chat() {
    }

    public Chat(String name, String text) {
        this.mName = name;
        this.mText = text;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }
}
