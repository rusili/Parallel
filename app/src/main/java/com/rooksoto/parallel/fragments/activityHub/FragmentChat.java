package com.rooksoto.parallel.fragments.activityHub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.network.objects.Chat;
import com.squareup.picasso.Picasso;

/**
 * Created by huilin on 3/2/17.
 */

public class FragmentChat extends Fragment {

    private ProgressBar progressBar;
    private EditText messageEditText;
    private Button sendButton;
    private ListView messageListView;
    private FirebaseListAdapter<Chat> messageListAdapter;
    private ImageView picImageView;

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
        // FIXME LILY: will need to expand and grab person name from FirebaseAuth and eventID/chatId for latter child method
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("chatIds").child("001");
        createFirebaseListAdapter(ref);
        messageListView.setAdapter(messageListAdapter);
        setupTextChangedListenerForMessage();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.push().setValue(new Chat("Lily", messageEditText.getText().toString()));
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
        messageListAdapter = new FirebaseListAdapter<Chat>(getActivity(), Chat.class, R.layout.item_message, ref) {
            @Override
            protected void populateView(View view, Chat chatMessage, int position) {
                progressBar.setVisibility(View.INVISIBLE);
                picImageView = (ImageView) view.findViewById(R.id.picImageView);
                Picasso.with(getContext()).load(R.drawable.bruttino_large).fit().into(picImageView);
                ((TextView) view.findViewById(R.id.messageTextView)).setText(chatMessage.getText());
                ((TextView) view.findViewById(R.id.nameTextView)).setText(chatMessage.getName());

            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        messageListAdapter.cleanup();
    }
}
