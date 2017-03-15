package com.rooksoto.parallel.activitylogin.createaccount;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.User;

public class FragmentLoginCreateAccountPresenter implements BasePresenter {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    public void start () {
    }


    public void createNewAccount (String email, final String username, String password) {
        // TODO: 3/8/17 Creates new Account
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // create account successful
                    String userId = auth.getCurrentUser().getUid();
                    rootRef.child("users").child(userId).setValue(new User(username, auth.getCurrentUser().getEmail()));
                } else {
                    // create account failed
                }
            }
        });
    }

    public void setOnClickReplace (Fragment fragment, View viewP, int containerID, String id) {
        ((Activity) viewP.getContext()).getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.animator_fade_in_right, R.animator.animator_fade_out_right)
                .replace(containerID, fragment, id)
                .commit();
    }
}