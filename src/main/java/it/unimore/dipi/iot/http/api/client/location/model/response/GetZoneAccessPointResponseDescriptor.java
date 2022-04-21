package it.unimore.dipi.iot.http.api.client.location.model.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetZoneAccessPointResponseDescriptor {

    @SerializedName("zoneList")
    @Expose
    private AccessPointInfo accessPointInfo;

    public AccessPointInfo getAccessPointInfo() {
        return accessPointInfo;
    }

    public void setAccessPointInfo(AccessPointInfo accessPointInfo) {
        this.accessPointInfo = accessPointInfo;
    }
}
