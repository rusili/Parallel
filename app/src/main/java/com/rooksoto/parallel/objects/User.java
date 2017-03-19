package com.rooksoto.parallel.objects;

import java.util.ArrayList;
import java.util.List;

public class User {

    String uid;
    String name;
    String email;
    String pictureLink;
    List<Answers> listofAnswers = new ArrayList <>();

    public User(String name, String email, String pictureLink) {
        this.name = name;
        this.email = email;
        this.pictureLink = pictureLink;
    }

    public User(String name, String email, String uid, String pictureLink) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.pictureLink = pictureLink;
    }

    public User(String name, String email, String pictureLink, List<Answers> listofAnswers) {
        this.name = name;
        this.email = email;
        this.pictureLink = pictureLink;
        this.listofAnswers = listofAnswers;
    }

    public User (String nameParam, String emailParam) {
        this.name = nameParam;
        this.email = emailParam;
    }

    public String getName () {
        return name;
    }

    public String getEmail () {
        return email;
    }

    public String getPictureLink () {
        return pictureLink;
    }

    public void setPictureLink (String pictureLinkParam) {
        this.pictureLink = pictureLinkParam;
    }

    public List<Answers> getListofAnswers () {
        return listofAnswers;
    }

    public void setListofAnswers (List <Answers> listofAnswers) {
        this.listofAnswers = listofAnswers;
    }

    public String getUid() {
        return uid;
    }

}
