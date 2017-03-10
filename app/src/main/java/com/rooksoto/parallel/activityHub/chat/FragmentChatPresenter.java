package com.rooksoto.parallel.activityHub.chat;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.google.firebase.database.FirebaseDatabase;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.ChatMessage;
import com.rooksoto.parallel.utility.AppContext;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FragmentChatPresenter implements BasePresenter {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseRef;

    private View view;
    private ListView chatroomListView;
    private Button buttonSend;
    private ListView messageListView;
    private EditText editTextMessage;
    private ProgressBar progressBar;
    private ImageView imageViewPic;

    private String userName;
    private String profilePic;
    private FirebaseListAdapter messageListAdapter;

    @Override
    public void start () {
    }

    public void getProfileInfo () {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged (@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    userName = user.getDisplayName();
                    profilePic = user.getPhotoUrl().toString();
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        // FIXME need to use eventID/chatId for latter child method
        databaseRef = FirebaseDatabase.getInstance().getReference().child("chatIds").child("001");
    }

    public void getViews (View viewP, ListView chatroomListView, ProgressBar progressBarP, EditText messageEditTextP, Button sendButtonP, ListView listViewP, ImageView picImageViewP) {
        this.view = viewP;
        this.chatroomListView = listViewP;
        this.progressBar = progressBarP;
        this.editTextMessage = messageEditTextP;
        this.buttonSend = sendButtonP;
        this.messageListView = listViewP;
        this.imageViewPic = picImageViewP;
    }

    @Override
    public void onBackPressedOverride (View viewP) {
    }

    @Override
    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {

    }

    public void onStartOverride () {
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public void onStopOverride () {
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void onDestroyOverride () {
        messageListAdapter.cleanup();
    }

    public void setUpChatRoomsOnViewCreated (Bundle argsP, FirebaseListAdapter firebaseListAdapterP) {
        this.messageListAdapter = firebaseListAdapterP;

        int position = FragmentPagerItem.getPosition(argsP);

        createFirebaseListAdapter(databaseRef);
        messageListView.setAdapter(messageListAdapter);
        setupTextChangedListenerForMessage();
        // FIXME: pass in the uri into the database
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                databaseRef.push().setValue(new ChatMessage(userName, editTextMessage.getText().toString(), profilePic));
                editTextMessage.setText("");
            }
        });

        List <String> chatroomArray = new ArrayList <>();
        chatroomArray.add("Main");
        chatroomArray.add("IOS");
        chatroomArray.add("Android");

        chatroomListView.setTextFilterEnabled(true);
        chatroomListView.setAdapter(new ArrayAdapter <String>(view.getContext(), R.layout.chat_rooms, R.id.fragment_hub_chat_room_view_text, chatroomArray));
    }

    private void createFirebaseListAdapter (final DatabaseReference ref) {
        messageListAdapter = new FirebaseListAdapter <ChatMessage>(((Activity) view.getContext()), ChatMessage.class, R.layout.chat_message, ref) {
            @Override
            protected void populateView (View view, ChatMessage chatMessage, int position) {
                progressBar.setVisibility(View.INVISIBLE);
                // TODO: must get profilepic link from database
                if (chatMessage.getProfilePic() == null) {
                    Picasso.with(AppContext.getAppContext()).load(R.drawable.bruttino_large).fit().into(imageViewPic);
                } else {
                    Picasso.with(AppContext.getAppContext()).load(Uri.parse(chatMessage.getProfilePic())).fit().into(imageViewPic);
                }
                ((TextView) view.findViewById(R.id.messageTextView)).setText(chatMessage.getText());
                ((TextView) view.findViewById(R.id.nameTextView)).setText(chatMessage.getName());

            }
        };
    }

    private void setupTextChangedListenerForMessage () {
        editTextMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged (CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() == 0) {
                    buttonSend.setEnabled(false);
                } else {
                    buttonSend.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged (Editable editable) {

            }
        });
    }

}
