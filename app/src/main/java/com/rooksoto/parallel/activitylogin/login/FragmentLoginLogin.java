package com.rooksoto.parallel.activitylogin.login;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;

public class FragmentLoginLogin extends Fragment implements BaseView {
    private FragmentLoginLoginPresenter fragmentLoginLoginPresenter = new FragmentLoginLoginPresenter();

    private View mView;
    private int containerID = R.id.activity_login_fragment_container;
    private String username;
    private String password;

    private final String TAG = getClass().toString();
    private static final int RC_SIGN_IN = 9001;

    private boolean isNew = true;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_login, container, false);
        initialize();
        return mView;
    }

    public static FragmentLoginLogin newInstance (boolean isNewParam) {
        Bundle args = new Bundle();

        FragmentLoginLogin fragment = new FragmentLoginLogin();
        fragment.isNew = isNewParam;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void checkFirebaseAuth () {
        fragmentLoginLoginPresenter.checkFirebaseAuth();
    }

    private void checkGoogleSignIn () {
        fragmentLoginLoginPresenter.checkGoogleSignIn(mView, getActivity());
    }

    @Override
    public void onStart () {
        super.onStart();
        fragmentLoginLoginPresenter.addAuthStateListener();
    }

    @Override
    public void onStop () {
        super.onStop();
        fragmentLoginLoginPresenter.removeAuthStateListener();
        fragmentLoginLoginPresenter.disconnectGoogleAPI();

    }

    public void initialize () {
        checkFirebaseAuth();

        if (isNew == true) {
            checkGoogleSignIn();
        }

        setViews();
    }

    @Override
    public void setViews () {
        EditText editTextUsername = (EditText) mView.findViewById(R.id.fragment_login_login_edittext_username);
        EditText editTextPassword = (EditText) mView.findViewById(R.id.fragment_login_login_edittext_password);
        SignInButton signInButton = (SignInButton) mView.findViewById(R.id.fragment_login_login_button_googlesignin);

        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(fragmentLoginLoginPresenter.getGoogleAPI());
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentLoginLoginPresenter.checkLoginID(requestCode, resultCode, data);
    }

    private void handleSignInResult (GoogleSignInResult result) {
        fragmentLoginLoginPresenter.handleSignInResult(result);
    }

    private void firebaseAuthWithGoogle (GoogleSignInAccount acct) {
        fragmentLoginLoginPresenter.firebaseAuthWithGoogle(acct);
    }

}
