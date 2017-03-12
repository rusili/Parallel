
package com.rooksoto.parallel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin {

    @SerializedName("authenticated")
    @Expose
    public Boolean authenticated;
    @SerializedName("admin_name")
    @Expose
    public String adminName;

}
