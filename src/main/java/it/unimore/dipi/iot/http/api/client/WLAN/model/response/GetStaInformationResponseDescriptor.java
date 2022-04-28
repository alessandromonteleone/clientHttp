package it.unimore.dipi.iot.http.api.client.WLAN.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.WLAN.model.ApId;
import it.unimore.dipi.iot.http.api.client.WLAN.model.Rssi;
import it.unimore.dipi.iot.http.api.client.WLAN.model.StaId;

public class GetStaInformationResponseDescriptor {

    @SerializedName("apAssociated")
    @Expose
    private ApId apAssociated;

    @SerializedName("rssi")
    @Expose
    private Rssi rssi;

    @SerializedName("staId")
    @Expose
    private StaId staId;

    public ApId getApAssociated() {
        return apAssociated;
    }

    public void setApAssociated(ApId apAssociated) {
        this.apAssociated = apAssociated;
    }

    public Rssi getRssi() {
        return rssi;
    }

    public void setRssi(Rssi rssi) {
        this.rssi = rssi;
    }

    public StaId getStaId() {
        return staId;
    }

    public void setStaId(StaId staId) {
        this.staId = staId;
    }
}
