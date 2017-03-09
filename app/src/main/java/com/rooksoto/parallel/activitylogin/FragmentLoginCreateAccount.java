package com.rooksoto.parallel.activitylogin;

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

public class FragmentLoginCreateAccount extends Fragment implements BaseView{
    private FragmentLoginPresenter fragmentLoginPresenter;
    private View mView;
    private String email;
    private String username;
    private String password;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_createaccount, container, false);
        initialize();
        return mView;
    }

    @Override
    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        EditText editTextEmail = (EditText) mView.findViewById(R.id.fragment_login_createaccount_edittext_email);
        EditText editTextUsername = (EditText) mView.findViewById(R.id.fragment_login_createaccount_edittext_username);
        EditText editTextPassword = (EditText) mView.findViewById(R.id.fragment_login_createaccount_edittext_password);
        Button buttonCreateAccount = (Button) mView.findViewById(R.id.fragment_login_createaccount_button_createaccount);

        email = editTextEmail.getText().toString();
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                fragmentLoginPresenter.createNewAccount(email, username, password);
            }
        });
    }
}
