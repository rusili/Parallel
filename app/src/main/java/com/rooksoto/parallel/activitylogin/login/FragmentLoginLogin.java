package com.rooksoto.parallel.activitylogin.login;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.ActivityHub;
import com.rooksoto.parallel.activitylogin.ActivityLoginPresenter;
import com.rooksoto.parallel.activitylogin.createaccount.FragmentLoginCreateAccount;
import com.rooksoto.parallel.activitylogin.wait.FragmentLoginWait;

public class FragmentLoginLogin extends Fragment implements FragmentLoginLoginPresenter.Listener, GoogleApiClient.OnConnectionFailedListener {
    public static final String WAIT = "Wait";
    public static final String CREATE_ACCOUNT = "CreateAccount";
    private View view;
    private String username;
    private String password;
    private final String TAG = getClass().toString();
    private int containerID = R.id.activity_login_fragment_container;
    private ImageView signInButton;
    private static final int RC_SIGN_IN = 9001;
    private boolean isNew = true;
    private FragmentLoginLoginPresenter loginFragmentPresenter;
    private ActivityLoginPresenter activityLoginPresenter;

    public static FragmentLoginLogin newInstance(boolean isNewParam) {
        Bundle args = new Bundle();

        FragmentLoginLogin fragment = new FragmentLoginLogin();
        fragment.isNew = isNewParam;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginPresenter = new ActivityLoginPresenter();
        loginFragmentPresenter = new FragmentLoginLoginPresenter(this);
        loginFragmentPresenter.onCreate();

        // FIXME Why is this here? When does this change? - Erick
        if (isNew) {
            // TODO What Lily thinks the point is of isNew
            loginFragmentPresenter.returnFromCreateAccount();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_login, container, false);
        signInButton = (ImageView) view.findViewById(R.id.fragment_login_login_button_googlesignin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFragmentPresenter.OnSignInClicked();
            }
        });
        initialize();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        loginFragmentPresenter.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        loginFragmentPresenter.onStop();
    }

    private void initialize() {
        EditText editTextUsername = (EditText) view.findViewById(R.id.fragment_login_login_edittext_username);
        EditText editTextPassword = (EditText) view.findViewById(R.id.fragment_login_login_edittext_password);

        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        final Button signInButton = (Button) view.findViewById(R.id.fragment_login_login_button_login);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fragmentLoginLoginPresenter.checkLoginInfo(username, password);
//                loginFragmentPresenter.onGeneralLoginClicked(new FragmentLoginWait(), signInButton, containerID, "Wait");
                loginFragmentPresenter.onGeneralLoginClicked(WAIT);
            }
        });
        final TextView textViewCreateAccount = (TextView) view.findViewById(R.id.fragment_login_login_button_createaccount);
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragmentPresenter.onGeneralLoginClicked(CREATE_ACCOUNT);
            }
        });
        final ImageView imageViewGoogleSignIn = (ImageView) view.findViewById(R.id.fragment_login_login_button_googlesignin);
        imageViewGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragmentPresenter.OnSignInClicked();
//                fragmentLoginLoginPresenter.checkGoogleSignIn(view, getActivity());
//                fragmentLoginLoginPresenter.setOnClickReplace(new FragmentLoginWait(), imageViewGoogleSignIn, containerID, "Wait");
            }
        });

        activityLoginPresenter.checkLogoVisibility(view);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            loginFragmentPresenter.handleSignInResult(result);
        }
    }

    @Override
    public void startActivityAfterAuthenticated() {
        Intent intent = new Intent(getActivity(), ActivityHub.class);
        startActivity(intent);
    }

    @Override
    public void sendResultToActivity(GoogleApiClient googleApiClient) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public GoogleApiClient buildGoogleApiClient(GoogleSignInOptions gso) {
        return new GoogleApiClient.Builder(getActivity())
                .enableAutoManage((FragmentActivity) getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @Override
    public void addAuthStateListener(FirebaseAuth firebaseAuth, FirebaseAuth.AuthStateListener authListener) {
        firebaseAuth.addAuthStateListener(authListener);
    }

    @Override
    public void removeAuthStateListener(FirebaseAuth firebaseAuth, FirebaseAuth.AuthStateListener authListener) {
        firebaseAuth.removeAuthStateListener(authListener);
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct, FirebaseAuth firebaseAuth) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void startFragmentReplacement(String id) {
        switch (id) {

            case WAIT:
                replaceFragment(new FragmentLoginWait(), id);
                break;
            case CREATE_ACCOUNT:
                replaceFragment(new FragmentLoginCreateAccount(), id);
        }

    }

    @Override
    public void demolishGoogleClient(GoogleApiClient googleApiClient) {
        googleApiClient.stopAutoManage((FragmentActivity) getActivity());
        googleApiClient.disconnect();

    }

    private void replaceFragment(Fragment fragment, String id) {
        getActivity().getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.animator_fade_in_right, R.animator.animator_fade_out_right)
                .replace(containerID, fragment, id)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginFragmentPresenter.onDestroy();

    }
}






