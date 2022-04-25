package it.unimore.dipi.iot.http.api.client.WLAN.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaId {
    @SerializedName("macId")
    @Expose
    private String macId;

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }
}
