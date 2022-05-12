package it.unimore.dipi.iot.http.api.client.applicationSupport.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeStamp {
    @SerializedName("seconds")
    @Expose
    private Double seconds;

    @SerializedName("nanoSeconds")
    @Expose
    private Double nanoSeconds;

    public Double getSeconds() {
        return seconds;
    }

    public void setSeconds(Double seconds) {
        this.seconds = seconds;
    }

    public Double getNanoSeconds() {
        return nanoSeconds;
    }

    public void setNanoSeconds(Double nanoSeconds) {
        this.nanoSeconds = nanoSeconds;
    }
}
