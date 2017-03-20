package com.rooksoto.parallel.utility.widgets.recyclerview.ExpandedRecyclerView;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.rooksoto.parallel.objects.Answers;

import java.util.ArrayList;
import java.util.List;

public class UserExtend implements Parent<Answers> {
    String name;
    String email;
    String pictureLink;
    String uid;
    List<Answers> listofAnswers = new ArrayList<>();

    public UserExtend (String name, String email, String pictureLink, String uid, List <Answers> listofAnswers) {
        this.name = name;
        this.email = email;
        this.pictureLink = pictureLink;
        this.uid = uid;
        this.listofAnswers = listofAnswers;
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

    @Override
    public List<Answers> getChildList () {
        return listofAnswers;
    }

    @Override
    public boolean isInitiallyExpanded () {
        return false;
    }

    public String getUid () {
        return uid;
    }
}
