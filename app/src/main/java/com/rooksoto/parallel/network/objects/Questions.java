package com.rooksoto.parallel.network.objects;

public class Questions {
    String question;
    String leftAnswer;
    String rightAnswer;

    public Questions(String questionParam){
        this.question = questionParam;
    }

    public Questions(String questionParam, String leftAnswerParam, String rightAnswerParam){
        this.question = questionParam;
        this.leftAnswer = leftAnswerParam;
        this.rightAnswer = rightAnswerParam;
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
