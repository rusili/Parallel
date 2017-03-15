
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventLocation {

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("radius_meters")
    @Expose
    private Float radiusMeters;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Float getRadiusMeters() {
        return radiusMeters;
    }

    public void setRadiusMeters(Float radiusMeters) {
        this.radiusMeters = radiusMeters;
    }
}
