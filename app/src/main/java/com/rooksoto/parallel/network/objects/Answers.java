package com.rooksoto.parallel.network.objects;

public class Answers {
    String question;
    String answer;

    public Answers (String questionParam, String answerParam) {
        this.question = questionParam;
        this.answer = answerParam;
    }

    public String getAnswer () {
        return answer;
    }

    public String getQuestion () {
        return question;
    }
}
