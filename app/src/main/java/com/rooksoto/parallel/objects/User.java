package com.rooksoto.parallel.objects;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String email;
    String pictureLink;
    List <Questions> listofAnswers = new ArrayList <>();

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

    public List <Questions> getListofAnswers () {
        return listofAnswers;
    }

    public void setListofAnswers (List <Questions> listofAnswers) {
        this.listofAnswers = listofAnswers;
    }
}
