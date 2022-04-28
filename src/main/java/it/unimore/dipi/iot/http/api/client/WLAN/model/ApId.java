package it.unimore.dipi.iot.http.api.client.WLAN.model;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ApId {
    @SerializedName("bssid")
    @Expose
    private String bssid;

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

}
