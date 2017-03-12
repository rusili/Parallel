package com.rooksoto.parallel.fragments.activityLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activities.ActivityStart;

public class FragmentLoginLogin extends Fragment implements LoginFragmentPresenter.Listener, GoogleApiClient.OnConnectionFailedListener {
    private View mView;
    private String username;
    private String password;
    private final String TAG = getClass().toString();
    private SignInButton signInButton;
    private static final int RC_SIGN_IN = 9001;
    private boolean isNew = true;
    private LoginFragmentPresenter loginFragmentPresenter;

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
        loginFragmentPresenter = new LoginFragmentPresenter(this);
        loginFragmentPresenter.onCreate();

        // FIXME Why is this here? When does this change? - Erick
        if (isNew) {
            // TODO What Lily thinks the point is of isNew
            loginFragmentPresenter.ReturnFromCreateAccount();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_login, container, false);
        signInButton = (SignInButton) mView.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFragmentPresenter.OnSignInClicked();
            }
        });
        initialize();
        return mView;
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
        EditText editTextUsername = (EditText) mView.findViewById(R.id.fragment_login_login_edittext_username);
        EditText editTextPassword = (EditText) mView.findViewById(R.id.fragment_login_login_edittext_password);

        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();
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
        Intent intent = new Intent(getActivity(), ActivityStart.class);
        startActivity(intent);
    }

    @Override
    public void sendResultToActivity(GoogleApiClient googleApiClient) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public GoogleApiClient buildGoogleApiClient(GoogleSignInOptions gso) {
        return new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), this)
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
}

