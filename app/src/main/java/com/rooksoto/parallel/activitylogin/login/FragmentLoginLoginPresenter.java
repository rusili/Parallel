package com.rooksoto.parallel.activitylogin.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rooksoto.parallel.BuildConfig;

import static com.google.android.gms.internal.zzt.TAG;

/**
 * Created by huilin on 3/11/17.
 */

class FragmentLoginLoginPresenter {
    private final String CLIENTID = BuildConfig.OAUTHCLIENTID;
    private Listener listener;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authListener;
    private GoogleApiClient googleApiClient;

    FragmentLoginLoginPresenter(Listener listener) {
        this.listener = listener;
    }

    void onCreate() {
        firebaseAuth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    listener.startActivityAfterAuthenticated();
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    void returnFromCreateAccount() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(CLIENTID)
                .requestEmail()
                .build();

        googleApiClient = listener.buildGoogleApiClient(gso);
    }

    public void OnSignInClicked() {
        listener.sendResultToActivity(googleApiClient);
    }

    public void onStart() {
        listener.addAuthStateListener(firebaseAuth, authListener);
    }

    public void onStop() {
        if (authListener != null) {
            listener.removeAuthStateListener(firebaseAuth, authListener);
        }

    }

    public void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            listener.firebaseAuthWithGoogle(acct, firebaseAuth);
        } else {
            Log.d(TAG, "Did not sign in correctly :/");
        }
    }

    public void onGeneralLoginClicked(String id) {
        listener.startFragmentReplacement(id);
    }

    public void onDestroy() {
        listener.demolishGoogleClient(googleApiClient);
    }


    interface Listener {
        void startActivityAfterAuthenticated();

        void sendResultToActivity(GoogleApiClient googleApiClient);

        GoogleApiClient buildGoogleApiClient(GoogleSignInOptions gso);

        void addAuthStateListener(FirebaseAuth firebaseAuth, FirebaseAuth.AuthStateListener authListener);

        void removeAuthStateListener(FirebaseAuth firebaseAuth, FirebaseAuth.AuthStateListener authListener);

        void firebaseAuthWithGoogle(GoogleSignInAccount acct, FirebaseAuth firebaseAuth);

        void startFragmentReplacement(String id);

        void demolishGoogleClient(GoogleApiClient googleApiClient);
    }
}