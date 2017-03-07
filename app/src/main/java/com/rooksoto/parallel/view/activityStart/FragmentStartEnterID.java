package com.rooksoto.parallel.view.activityStart;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rooksoto.parallel.R;

public class FragmentStartEnterID extends Fragment {
    private View mView;
    private TextView textViewEventID;
    private Button button;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_start_enterid, container, false);
        button = (Button) mView.findViewById(R.id.enter_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOnline()) {
                    Toast.makeText(getActivity(), "Cannot Connect - Please Check Internet Connection", Toast.LENGTH_LONG).show();
                } else {
                    FragmentStartWelcome mFragmentStartWelcome = new FragmentStartWelcome();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_start_fragment_container, mFragmentStartWelcome)
                            .commit();
                }
            }
        });
        initialize();
        return mView;
    }

    private void initialize () {
        textViewEventID = (TextView) mView.findViewById(R.id.fragment_start_enterid_eventid);
    }

    public boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }
    }
}
