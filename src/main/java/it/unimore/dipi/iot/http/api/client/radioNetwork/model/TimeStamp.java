package it.unimore.dipi.iot.http.api.client.radioNetwork.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class TimeStamp {

    @SerializedName("nanoSeconds")
    @Expose
    private Integer nanoSeconds;
    @SerializedName("seconds")
    @Expose
    private Integer seconds;

    public Integer getNanoSeconds() {
        return nanoSeconds;
    }

    public void setNanoSeconds(Integer nanoSeconds) {
        this.nanoSeconds = nanoSeconds;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

}