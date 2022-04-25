package it.unimore.dipi.iot.http.api.client.WLAN.model.response;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApInfoList {

    @SerializedName("apId")
    @Expose
    private ApId apId;
    @SerializedName("apLocation")
    @Expose
    private ApLocation apLocation;
    @SerializedName("bssLoad")
    @Expose
    private BssLoad bssLoad;

    public ApId getApId() {
        return apId;
    }

    public void setApId(ApId apId) {
        this.apId = apId;
    }

    public ApLocation getApLocation() {
        return apLocation;
    }

    public void setApLocation(ApLocation apLocation) {
        this.apLocation = apLocation;
    }

    public BssLoad getBssLoad() {
        return bssLoad;
    }

    public void setBssLoad(BssLoad bssLoad) {
        this.bssLoad = bssLoad;
    }

}