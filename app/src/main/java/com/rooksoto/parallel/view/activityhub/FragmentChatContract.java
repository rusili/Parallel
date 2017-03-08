package com.rooksoto.parallel.view.activityhub;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.BaseView;

/**
 * Created by huilin on 3/7/17.
 */

public class FragmentChatContract {
    interface View extends BaseView<Presenter> {}
    interface Presenter extends BasePresenter {

        void getUserInformation(FirebaseUser user);

        DatabaseReference getRef();

        void sendTextToDb(String text);

        FirebaseAuth getFirebaseAuth();
    }
}
