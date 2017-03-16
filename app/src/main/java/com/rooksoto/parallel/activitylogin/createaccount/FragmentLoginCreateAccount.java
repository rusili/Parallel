package com.rooksoto.parallel.activitylogin.createaccount;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activitylogin.login.FragmentLoginLogin;

public class FragmentLoginCreateAccount extends Fragment implements BaseView {
    private FragmentLoginCreateAccountPresenter fragmentLoginCreateAccountPresenter = new FragmentLoginCreateAccountPresenter();

    private View view;
    private int containerID = R.id.activity_login_fragment_container;

    private String email;
    private String username;
    private String password;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_createaccount, container, false);
        initialize();
        return view;
    }

    @Override
    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        EditText editTextEmail = (EditText) view.findViewById(R.id.fragment_login_createaccount_edittext_email);
        EditText editTextUsername = (EditText) view.findViewById(R.id.fragment_login_createaccount_edittext_username);
        EditText editTextPassword = (EditText) view.findViewById(R.id.fragment_login_createaccount_edittext_password);
        Button buttonCreateAccount = (Button) view.findViewById(R.id.fragment_login_createaccount_button_createaccount);

        email = editTextEmail.getText().toString();
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                fragmentLoginCreateAccountPresenter.createNewAccount(email, username, password);
                fragmentLoginCreateAccountPresenter.setOnClickReplace(new FragmentLoginLogin(), view, containerID, "Login");
            }
        });
    }

}
