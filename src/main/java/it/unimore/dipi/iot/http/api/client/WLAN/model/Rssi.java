package it.unimore.dipi.iot.http.api.client.WLAN.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rssi {

    @SerializedName("rssi")
    @Expose
    private int rssi;

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }
}
