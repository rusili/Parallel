package com.rooksoto.parallel.activityHub.chat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseListAdapter;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.ChatMessage;

public class FragmentChat extends Fragment implements FragmentChatPresenter.Listener{
    private FragmentChatPresenter fragmentChatPresenter;

    private View view;
    private ProgressBar progressBar;
    private EditText messageEditText;
    private Button sendButton;
    private ListView messageListView;
    private ListView chatroomListView;
    private ImageView picImageView;

    private FirebaseListAdapter <ChatMessage> messageListAdapter;

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentChatPresenter = new FragmentChatPresenter(this);
        fragmentChatPresenter.onCreate();
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_chatroom, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
        fragmentChatPresenter.getViews(view, chatroomListView, progressBar, messageEditText, sendButton, messageListView, picImageView);
    }

    @Override
    public void setViews () {
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        messageEditText = (EditText) view.findViewById(R.id.messageEditText);
        sendButton = (Button) view.findViewById(R.id.sendButton);
        messageListView = (ListView) view.findViewById(R.id.fragment_hub_chat_main_messageListView);
        chatroomListView = (ListView) view.findViewById(R.id.fragment_hub_chat_roomlistview);
        picImageView = (ImageView) view.findViewById(R.id.picImageView);
    }

    @Override
    public void onBackPressed () {
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentChatPresenter.setUpChatRoomsOnViewCreated(getArguments(), messageListAdapter);
    }

    @Override
    public void onStart () {
        super.onStart();
        fragmentChatPresenter.onStartOverride();
    }

    @Override
    public void onStop () {
        super.onStop();
        fragmentChatPresenter.onStopOverride();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        fragmentChatPresenter.onDestroyOverride();
    }
}
