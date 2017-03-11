package com.rooksoto.parallel.activitylogin.login;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activitylogin.ActivityLoginPresenter;
import com.rooksoto.parallel.activitylogin.createaccount.FragmentLoginCreateAccount;
import com.rooksoto.parallel.activitylogin.wait.FragmentLoginWait;

public class FragmentLoginLogin extends Fragment implements BaseView {
    private FragmentLoginLoginPresenter fragmentLoginLoginPresenter = new FragmentLoginLoginPresenter();
    private ActivityLoginPresenter activityLoginPresenter = new ActivityLoginPresenter();

    private View view;
    private int containerID = R.id.activity_login_fragment_container;
    private String username;
    private String password;

    private final String TAG = getClass().toString();
    private static final int RC_SIGN_IN = 9001;

    private boolean isNew = true;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_login, container, false);
        initialize();
        return view;
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

    private void checkFirebaseAuth(View view) {
        fragmentLoginLoginPresenter.checkFirebaseAuth(view);
    }

    private void buildGoogleSignInAndClient(Activity activity) {
        fragmentLoginLoginPresenter.buildGoogleSignInAndClient(activity);
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

        checkFirebaseAuth(view);

        if (isNew) {
            buildGoogleSignInAndClient(getActivity());
        }

        setViews();
    }

    @Override
    public void setViews () {
        final EditText editTextUsername = (EditText) view.findViewById(R.id.fragment_login_login_edittext_username);
        final EditText editTextPassword = (EditText) view.findViewById(R.id.fragment_login_login_edittext_password);
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        final Button signInButton = (Button) view.findViewById(R.id.fragment_login_login_button_login);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                //fragmentLoginLoginPresenter.checkLoginInfo(username, password);
                fragmentLoginLoginPresenter.setOnClickReplace(new FragmentLoginWait(), signInButton, containerID, "Wait");
            }
        });
        final TextView textViewCreateAccount = (TextView) view.findViewById(R.id.fragment_login_login_button_createaccount);
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                fragmentLoginLoginPresenter.setOnClickReplace(new FragmentLoginCreateAccount(), textViewCreateAccount, containerID, "CreateAccount");
            }
        });
        final ImageView imageViewGoogleSignIn = (ImageView) view.findViewById(R.id.fragment_login_login_button_googlesignin);
        imageViewGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
//                fragmentLoginLoginPresenter.buildGoogleSignInAndClient(view, getActivity());
//                fragmentLoginLoginPresenter.setOnClickReplace(new FragmentLoginWait(), imageViewGoogleSignIn, containerID, "Wait");
                fragmentLoginLoginPresenter.onGoogleSignInClicked(getActivity());

            }
        });

        activityLoginPresenter.checkLogoVisibility(view);
    }

    @Override
    public void onBackPressed () {
    }



    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
        fragmentLoginLoginPresenter.checkLoginID(requestCode, resultCode, data);
    }

}
