package it.unimore.dipi.iot.http.api.client.applicationSupport.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCurrentTimeResponseDescriptor {

    @SerializedName("seconds")
    @Expose
    private Double seconds;

    @SerializedName("nanoSeconds")
    @Expose
    private Double nanoSeconds;

    @SerializedName("timeSourceStatus")
    @Expose
    private String timeSourceStatus;

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

    public String getTimeSourceStatus() {
        return timeSourceStatus;
    }

    public void setTimeSourceStatus(String timeSourceStatus) {
        this.timeSourceStatus = timeSourceStatus;
    }
}
