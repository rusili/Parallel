package com.rooksoto.parallel.network.objects;

public class Questions {
    String question;
    boolean answer;
    int leftResID = 0;
    int rightResID = 0;

    public Questions (String questionParam) {
        this.question = questionParam;
    }

    public Questions (String questionParam, int leftResIDParam, int rightResIDParam) {
        this.question = questionParam;
        this.leftResID = leftResIDParam;
        this.rightResID = rightResIDParam;
    }

    public void setAnswer (boolean answer) {
        this.answer = answer;
    }

    public boolean isAnswer () {
        return answer;
    }

    public String getQuestion () {
        return question;
    }

    public int getLeftResID () {
        return leftResID;
    }

    public int getRightResID () {
        return rightResID;
    }
}
