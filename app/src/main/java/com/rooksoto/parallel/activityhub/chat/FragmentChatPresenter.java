package com.rooksoto.parallel.activityhub.chat;

import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rooksoto.parallel.objects.ChatMessage;

class FragmentChatPresenter {

    private static final String TAG = FragmentChatPresenter.class.getClass().toString();
    public static final String CHATIDS = "chatids";
    private Listener listener;
    private String userName;
    private String profilePic;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference ref;
    private FirebaseListAdapter<ChatMessage> messageListAdapter;

    public FragmentChatPresenter(Listener listener) {
        this.listener = listener;
    }

    void onCreate() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
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
        setChatroomReference("001");

    }

    void onViewCreated() {
        messageListAdapter = listener.createFirebaseListAdapter(ref);
    }

    void onSendButtonClick(String message) {
        ref.push().setValue(new ChatMessage(userName, message, profilePic));
    }

    void onStart() {
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    void onStop() {
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    void onDestroy() {
        messageListAdapter.cleanup();
    }

    void setChatroomReference(String chatId) {
        ref = FirebaseDatabase.getInstance().getReference().child(CHATIDS).child(chatId);
    }


    interface Listener {

        FirebaseListAdapter<ChatMessage> createFirebaseListAdapter(DatabaseReference ref);

    }

}