package com.rooksoto.parallel.network.objects;

public class Questions {
    String question;
    String leftAnswer;
    String rightAnswer;

    public void setQuestion (String question) {
        this.question = question;
    }

    public void setLeftAnswer (String leftAnswer) {
        this.leftAnswer = leftAnswer;
    }

    public void setRightAnswer (String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion () {
        return question;
    }

    public String getLeftAnswer () {
        return leftAnswer;
    }

    public String getRightAnswer () {
        return rightAnswer;
    }
}
