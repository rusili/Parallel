package com.rooksoto.parallel.activityHub;

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
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.ChatMessage;
import com.rooksoto.parallel.utility.AppContext;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by huilin on 3/2/17.
 */

public class FragmentChat extends Fragment {
    private View mView;
    private ProgressBar progressBar;
    private EditText messageEditText;
    private Button sendButton;
    private ListView messageListView;
    private ListView chatroomListView;
    private FirebaseListAdapter <ChatMessage> messageListAdapter;
    private ImageView picImageView;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String userName;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference ref;
    private String profilePic;
    private List<String> chatroomArray = new ArrayList<>();

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        ref = FirebaseDatabase.getInstance().getReference().child("chatIds").child("001");
    }

    private void setUpChatRooms(){
        chatroomArray.add("Main");
        chatroomArray.add("IOS");
        chatroomArray.add("Android");
        chatroomListView.setTextFilterEnabled(true);
        chatroomListView.setAdapter(new ArrayAdapter<String>(mView.getContext(), R.layout.chat_rooms, R.id.fragment_hub_chat_room_view_text, chatroomArray));
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hub_chatroom, container, false);
        initialize();
        return mView;
    }

    private void initialize () {
        progressBar = (ProgressBar) mView.findViewById(R.id.progressBar);
        messageEditText = (EditText) mView.findViewById(R.id.messageEditText);
        sendButton = (Button) mView.findViewById(R.id.sendButton);
        messageListView = (ListView) mView.findViewById(R.id.fragment_hub_chat_main_messageListView);
        chatroomListView = (ListView) mView.findViewById(R.id.fragment_hub_chat_roomlistview);
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
        createFirebaseListAdapter(ref);
        messageListView.setAdapter(messageListAdapter);
        setupTextChangedListenerForMessage();
        // FIXME: pass in the uri into the database
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                ref.push().setValue(new ChatMessage(userName, messageEditText.getText().toString(), profilePic));
                messageEditText.setText("");
            }
        });
        setUpChatRooms();
    }

    private void setupTextChangedListenerForMessage () {
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged (CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() == 0) {
                    sendButton.setEnabled(false);
                } else {
                    sendButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged (Editable editable) {

            }
        });
    }


    private void createFirebaseListAdapter (final DatabaseReference ref) {
        messageListAdapter = new FirebaseListAdapter <ChatMessage>(getActivity(), ChatMessage.class, R.layout.chat_message, ref) {
            @Override
            protected void populateView (View view, ChatMessage chatMessage, int position) {
                progressBar.setVisibility(View.INVISIBLE);
                picImageView = (ImageView) view.findViewById(R.id.picImageView);
                // TODO: must get profilepic link from database
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
    public void onStart () {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop () {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        messageListAdapter.cleanup();
    }
}
