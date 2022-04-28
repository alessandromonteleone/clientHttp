package it.unimore.dipi.iot.http.api.client.location.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.unimore.dipi.iot.http.api.client.location.model.AccessPointInfo;

public class GetZoneAccessPointResponseDescriptor {

    @SerializedName("accessPointInfo")
    @Expose
    private AccessPointInfo accessPointInfo;

    public AccessPointInfo getAccessPointInfo() {
        return accessPointInfo;
    }

    public void setAccessPointInfo(AccessPointInfo accessPointInfo) {
        this.accessPointInfo = accessPointInfo;
    }
}
