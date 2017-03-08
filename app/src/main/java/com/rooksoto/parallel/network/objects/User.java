package com.rooksoto.parallel.network.objects;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String email;
    String pictureLink;
    List<Questions> listofQuestions = new ArrayList<>();

    public User(String nameParam, String emailParam){
        this.name = nameParam;
        this.email = emailParam;
    }

    public void setListofQuestions (List <Questions> listofQuestions) {
        this.listofQuestions = listofQuestions;
    }

    public void setPictureLink (String pictureLinkParam) {
        this.pictureLink = pictureLinkParam;
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

    public List <Questions> getListofQuestions () {
        return listofQuestions;
    }
}
