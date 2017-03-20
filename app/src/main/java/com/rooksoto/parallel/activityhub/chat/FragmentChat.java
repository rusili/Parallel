package com.rooksoto.parallel.activityhub.chat;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.ChatMessage;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentChat extends Fragment implements FragmentChatPresenter.Listener{

    private ProgressBar progressBar;
    private EditText messageEditText;
    private Button sendButton;
    private ListView messageListView;
    private CircleImageView picImageViewLeft;
    private CircleImageView picImageViewRight;
    private FragmentChatPresenter fragmentChatPresenter;
    private ImageButton imageButtonExit;
    private FirebaseUser firebaseUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentChatPresenter = new FragmentChatPresenter(this);
        fragmentChatPresenter.onCreate();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hub_chatroom, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        messageEditText = (EditText) view.findViewById(R.id.fragment_hub_chatroom_edittext_message);
        sendButton = (Button) view.findViewById(R.id.sendButton);
        messageListView = (ListView) view.findViewById(R.id.fragment_hub_chat_main_messageListView);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        int position = FragmentPagerItem.getPosition(getArguments());
        fragmentChatPresenter.onViewCreated();
        setupTextChangedListenerForMessage();
        setOnCickListeners();
        setOnEditorActionListeners();
    }

    private void setOnCickListeners(){
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentChatPresenter.onSendButtonClick(messageEditText.getText().toString());
                messageEditText.setText("");
            }
        });
    }

    private void setOnEditorActionListeners(){
        messageEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction (TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND){
                    fragmentChatPresenter.onSendButtonClick(messageEditText.getText().toString());
                    messageEditText.setText("");
                    handled = true;
                }
                return handled;
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

    @Override
    public void onStart() {
        super.onStart();
        fragmentChatPresenter.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        fragmentChatPresenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentChatPresenter.onDestroy();
    }


    @Override
    public FirebaseListAdapter<ChatMessage> createFirebaseListAdapter(DatabaseReference ref) {
        final FirebaseListAdapter<ChatMessage> messageListAdapter = new FirebaseListAdapter<ChatMessage>(getActivity(), ChatMessage.class, R.layout.chat_message, ref) {
            @Override
            protected void populateView(View view, ChatMessage chatMessage, int position) {
                progressBar.setVisibility(View.INVISIBLE);
                picImageViewLeft = (CircleImageView) view.findViewById(R.id.picImageViewLeft);
                picImageViewRight = (CircleImageView) view.findViewById(R.id.picImageViewRight);
                if (chatMessage.getProfilePic() == null) {
                    Picasso.with(getActivity()).load(R.drawable.bruttino_large).fit().into(picImageViewLeft);
                    Picasso.with(getActivity()).load(R.drawable.bruttino_large).fit().into(picImageViewRight);
                } else {
                    Picasso.with(getActivity()).load(Uri.parse(chatMessage.getProfilePic())).fit().into(picImageViewLeft);
                    Picasso.with(getActivity()).load(Uri.parse(chatMessage.getProfilePic())).fit().into(picImageViewRight);
                }
                TextView textViewMessage = (TextView) view.findViewById(R.id.messageTextView);
                textViewMessage.setText(chatMessage.getText());
                TextView textViewName = (TextView)  view.findViewById(R.id.nameTextView);
                textViewName.setText(chatMessage.getName());
                if (firebaseUser.getDisplayName().equals(chatMessage.getName())){
                    picImageViewLeft.setVisibility(View.INVISIBLE);
                    textViewName.setTextColor(getResources().getColor(R.color.teal));
                    textViewName.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                    textViewMessage.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                } else {
                    picImageViewRight.setVisibility(View.INVISIBLE);
                }
            }
        };

        messageListView.setAdapter(messageListAdapter);
        return messageListAdapter;
    }
}