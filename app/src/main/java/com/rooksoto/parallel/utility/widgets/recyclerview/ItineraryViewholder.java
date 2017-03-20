package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Session;

public class ItineraryViewholder extends RecyclerView.ViewHolder {
    private TextView textViewHour1;
    private TextView textViewHour2;
    private TextView textViewMinute1;
    private TextView textViewMinute2;
    private TextView textViewAMPM1;
    private TextView textViewAMPM2;
    private TextView textViewName;
    private TextView textViewTagline;
    private TextView textViewLocation;

    private static final String TAG = "ItineraryViewholder";

    public ItineraryViewholder (View itemView) {
        super(itemView);
        textViewHour1 = (TextView) itemView.findViewById(R.id.fragment_hub_eventinfo_sessions_time1_hour);
        textViewHour2 = (TextView) itemView.findViewById(R.id.fragment_hub_eventinfo_sessions_time2_hour);
        textViewMinute1 = (TextView) itemView.findViewById(R.id.fragment_hub_eventinfo_sessions_time1_minute);
        textViewMinute2 = (TextView) itemView.findViewById(R.id.fragment_hub_eventinfo_sessions_time2_minute);
        textViewAMPM1 = (TextView) itemView.findViewById(R.id.fragment_hub_eventinfo_sessions_time1_ampm);
        textViewAMPM2 = (TextView) itemView.findViewById(R.id.fragment_hub_eventinfo_sessions_time2_ampm);
        textViewName = (TextView) itemView.findViewById(R.id.fragment_hub_eventinfo_sessions_viewholder_name);
        textViewTagline = (TextView) itemView.findViewById(R.id.fragment_hub_eventinfo_sessions_viewholder_tag);
        textViewLocation = (TextView) itemView.findViewById(R.id.fragment_hub_eventinfo_sessions_viewholder_location);
    }

    public void bind (int position, Session sessionP) {
        textViewName.setText(sessionP.getName());
        textViewLocation.setText(sessionP.getLocation());
        String[] timeArray = sessionP.getStartTime().split(":");
        String[] timeArray2 = timeArray[1].split(" ");
        textViewHour1.setText(timeArray[0]);
        textViewMinute1.setText(timeArray2[0]);
        textViewAMPM1.setText(timeArray2[1]);

//        int temp = sessionP.getStartTime().getHours();
//        if (temp > 12){
//            temp = temp - 12;
//        }
//        textViewHour1.setText("" + temp);
//
//        String tempString = null;
//        temp = sessionP.getStartTime().getMinutes();
//        if (temp == 0){
//            tempString = "00";
//            textViewMinute1.setText(tempString);
//        } else {
//            textViewMinute1.setText("" + temp);
//        }
    }
}
