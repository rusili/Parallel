package com.rooksoto.parallel.view.activityhub;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rooksoto.parallel.network.objects.Chat;

/**
 * Created by huilin on 3/7/17.
 */

public class FragmentChatPresenter implements FragmentChatContract.Presenter {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference ref;
    private String userName;
    private String profilePic;

    @Override
    public void start() {
        firebaseAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("chatIds").child("001");
    }


    @Override
    public void getUserInformation(FirebaseUser user) {
        userName = user.getDisplayName();
        profilePic = user.getPhotoUrl().toString();
    }

    @Override
    public DatabaseReference getRef() {
        return ref;
    }

    @Override
    public void sendTextToDb(String text) {
        getRef().push().setValue(new Chat(userName, text, profilePic));
    }

    @Override
    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

}
