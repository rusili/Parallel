package com.rooksoto.parallel.objects;

import android.graphics.PointF;

/**
 * Created by huilin on 3/19/17.
 */

public class Pin {
    private PointF coordinates;
    private String uid;

    public Pin(String uid, PointF coordinates) {
        this.uid = uid;
        this.coordinates = coordinates;
    }

    public PointF getCoordinates() {
        return coordinates;
    }

    public String getUid() {
        return uid;
    }
}
