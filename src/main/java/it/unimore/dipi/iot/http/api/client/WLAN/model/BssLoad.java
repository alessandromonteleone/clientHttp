package it.unimore.dipi.iot.http.api.client.WLAN.model.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BssLoad {
    @SerializedName("availAdmCap")
    @Expose
    private Integer availAdmCap;
    @SerializedName("channelUtilization")
    @Expose
    private Integer channelUtilization;
    @SerializedName("staCount")
    @Expose
    private Integer staCount;

    public Integer getAvailAdmCap() {
        return availAdmCap;
    }

    public void setAvailAdmCap(Integer availAdmCap) {
        this.availAdmCap = availAdmCap;
    }

    public Integer getChannelUtilization() {
        return channelUtilization;
    }

    public void setChannelUtilization(Integer channelUtilization) {
        this.channelUtilization = channelUtilization;
    }

    public Integer getStaCount() {
        return staCount;
    }

    public void setStaCount(Integer staCount) {
        this.staCount = staCount;
    }

}
