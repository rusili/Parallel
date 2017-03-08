package com.rooksoto.parallel.view.activityhub;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.network.objects.Chat;
import com.rooksoto.parallel.utility.AppContext;
import com.squareup.picasso.Picasso;

import static android.content.ContentValues.TAG;

/**
 * Created by huilin on 3/2/17.
 */

public class FragmentChat extends Fragment implements FragmentChatContract.View {

    private ProgressBar progressBar;
    private EditText messageEditText;
    private Button sendButton;
    private ListView messageListView;
    private FirebaseListAdapter<Chat> messageListAdapter;
    private ImageView picImageView;
    private FirebaseAuth.AuthStateListener authStateListener;

    private FragmentChatContract.Presenter chatPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatPresenter.start();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    chatPresenter.getUserInformation(user);
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatroom, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        messageEditText = (EditText) view.findViewById(R.id.messageEditText);
        sendButton = (Button) view.findViewById(R.id.sendButton);
        messageListView = (ListView) view.findViewById(R.id.messageListView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
        createFirebaseListAdapter(chatPresenter.getRef());
        messageListView.setAdapter(messageListAdapter);
        setupTextChangedListenerForMessage();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatPresenter.sendTextToDb(messageEditText.getText().toString());
                messageEditText.setText("");
            }
        });


    }

    private void setupTextChangedListenerForMessage() {
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() == 0) {
                    sendButton.setEnabled(false);
                } else {
                    sendButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void createFirebaseListAdapter(final DatabaseReference ref) {
        messageListAdapter = new FirebaseListAdapter<Chat>(getActivity(), Chat.class, R.layout.chat_message, ref) {
            @Override
            protected void populateView(View view, Chat chatMessage, int position) {
                progressBar.setVisibility(View.INVISIBLE);
                picImageView = (ImageView) view.findViewById(R.id.picImageView);
                if (chatMessage.getProfilePic() == null) {
                    Picasso.with(AppContext.getAppContext()).load(R.drawable.bruttino_large).fit().into(picImageView);
                } else {
                    Picasso.with(AppContext.getAppContext()).load(Uri.parse(chatMessage.getProfilePic())).fit().into(picImageView);
                }
                ((TextView) view.findViewById(R.id.messageTextView)).setText(chatMessage.getText());
                ((TextView) view.findViewById(R.id.nameTextView)).setText(chatMessage.getName());

            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        chatPresenter.getFirebaseAuth().addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            chatPresenter.getFirebaseAuth().removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        messageListAdapter.cleanup();
    }

    @Override
    public void setPresenter(FragmentChatContract.Presenter presenter) {
        chatPresenter = presenter;
    }
}
